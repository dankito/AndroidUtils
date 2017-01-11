package net.dankito.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ganymed on 20/10/16.
 */
public class AsyncProducerConsumerQueue<T> {

  public static final int WAITING_BEFORE_CONSUMING_ITEM_DISABLED = 0;

  private static final Logger log = LoggerFactory.getLogger(AsyncProducerConsumerQueue.class);


  protected BlockingQueue<T> producedItemsQueue = new LinkedBlockingQueue<>(100); // set capacity to a value that's far above how many concurrent messages

  protected ConsumerListener<T> consumerListener;

  protected int minimumMillisecondsToWaitBeforeConsumingItem = WAITING_BEFORE_CONSUMING_ITEM_DISABLED;

  protected Timer waitBeforeConsumingItemTimer = new Timer("WaitBeforeConsumingItemTimer");

  protected List<Thread> consumerThreads = new ArrayList<>();


  public AsyncProducerConsumerQueue(ConsumerListener<T> consumerListener) {
    this(1, consumerListener);
  }

  public AsyncProducerConsumerQueue(int countThreadsToUse, ConsumerListener<T> consumerListener) {
    this(countThreadsToUse, WAITING_BEFORE_CONSUMING_ITEM_DISABLED, consumerListener);
  }

  public AsyncProducerConsumerQueue(int countThreadsToUse, int minimumMillisecondsToWaitBeforeConsumingItem, ConsumerListener<T> consumerListener) {
    this.consumerListener = consumerListener;
    this.minimumMillisecondsToWaitBeforeConsumingItem = minimumMillisecondsToWaitBeforeConsumingItem;

    startConsumerThreads(countThreadsToUse);
  }

  public void stop() {
    List<T> remainingItemsInQueue = new ArrayList<>(producedItemsQueue);
    producedItemsQueue.clear();

    for(Thread consumerThread : consumerThreads) {
      try { consumerThread.join(100); } catch(Exception ignored) { }
    }

    // TODO: really consume remaining items even though cleanUp() has already been called?
    for(T item : remainingItemsInQueue) {
      consumeItem(item);
    }
  }


  protected void startConsumerThreads(int countThreads) {
    for(int i = 0; i < countThreads; i++) {
      startConsumerThread();
    }
  }

  protected void startConsumerThread() {
    Thread consumerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        consumerThread();
      }
    }, "AsyncProducerConsumerQueue" + consumerThreads.size());

    consumerThreads.add(consumerThread);

    consumerThread.start();
  }

  protected void consumerThread() {
    while (Thread.interrupted() == false) {
      try {
        T nextItemToConsume = producedItemsQueue.take();
        consumeItem(nextItemToConsume);
      } catch (Exception e) {
        if((e instanceof InterruptedException) == false) { // it's quite usual that on stopping thread an InterruptedException will be thrown
          log.error("An error occurred in consumerThread()", e);
        }
        else // Java, i love you! After having externally called Thread.interrupt(), InterruptedException will be thrown but you have to call Thread.currentThread().interrupt() manually
          Thread.currentThread().interrupt();
      }
    }

    log.debug("consumerThread() stopped");
  }

  protected void consumeItem(final T nextItemToConsume) {
    if(minimumMillisecondsToWaitBeforeConsumingItem <= WAITING_BEFORE_CONSUMING_ITEM_DISABLED) {
      passConsumedItemOnToListener(nextItemToConsume);
    }
    else {
      waitBeforeConsumingItemTimer.schedule(new TimerTask() {
        @Override
        public void run() {
          passConsumedItemOnToListener(nextItemToConsume);
        }
      }, minimumMillisecondsToWaitBeforeConsumingItem);
    }
  }

  protected void passConsumedItemOnToListener(T nextItemToConsume) {
    try {
      consumerListener.consumeItem(nextItemToConsume);
    } catch (Exception e) { // urgently catch exceptions. otherwise if an uncaught exception occurs during handling, response loop would catch this exception and stop proceeding
      log.error("An error occurred while consuming produced item " + nextItemToConsume, e);
    }
  }


  public boolean add(T producedItem) {
    // use offer() instead of put() and take() instead of poll(int), see http://supercoderz.in/2012/02/04/using-linkedblockingqueue-for-high-throughput-java-applications/
    return producedItemsQueue.offer(producedItem);
  }

}
