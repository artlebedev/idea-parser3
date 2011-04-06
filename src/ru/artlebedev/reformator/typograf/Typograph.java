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

  private TypographParams params = new TypographParams();
  private String text;

  public String process(String text) {
    this.text = String.valueOf((char) 0x02) + text + String.valueOf((char) 0x02);

    // typograph process will go here

    return this.text;
  }

  public String process(String text, TypographParams params) {
    this.params = params;

    return process(text);
  }

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
