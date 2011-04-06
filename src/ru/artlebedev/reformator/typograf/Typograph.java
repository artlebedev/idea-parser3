package ru.artlebedev.reformator.typograf;

/**
 * reformator for java
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2011 Vladimir Tokmakov <vlalek@design.ru>
 * Copyright 2011 ArtLebedev Studio
 * <p/>
 * Commercial use is restricted. Please contact ArtLebedev Studio in order to buy
 * commercial license if you want one.
 */

public class Typograph {
  private static volatile Typograph instance;

  private Typograph() {

  }

  public static Typograph getInstance() {
    if(instance == null) {
      synchronized (Typograph.class) {
        if(instance == null)
          instance = new Typograph();
      }
    }
    return instance;
  }
}
