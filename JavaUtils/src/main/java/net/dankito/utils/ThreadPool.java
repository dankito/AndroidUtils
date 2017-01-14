package net.dankito.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Named;


@Named
public class ThreadPool implements IThreadPool {

  protected ExecutorService executorService = Executors.newCachedThreadPool();


  public void runAsync(Runnable task) {
    executorService.submit(task);
  }

}
