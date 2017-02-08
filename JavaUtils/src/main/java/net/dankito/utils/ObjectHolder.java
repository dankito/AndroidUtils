package net.dankito.utils;


public class ObjectHolder<T> {

  protected T object;


  public ObjectHolder() {

  }

  public ObjectHolder(T object) {
    this.object = object;
  }


  public boolean isObjectSet() {
    return getObject() != null;
  }

  public T getObject() {
    return object;
  }

  public void setObject(T object) {
    this.object = object;
  }


  @Override
  public String toString() {
    return "" + getObject();
  }

}
