package net.dankito.utils;

/**
 * Created by ganymed on 20/10/16.
 */
public interface ConsumerListener<T> {

  void consumeItem(T item);

}
