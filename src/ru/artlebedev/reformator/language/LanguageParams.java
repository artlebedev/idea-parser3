package ru.artlebedev.reformator.language;

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

public class LanguageParams {
  private int limit = 5000;
  private int limitedParts = 5;

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public void setLimitedParts(int limitedParts) {
    this.limitedParts = limitedParts;
  }

  public int getLimit() {
    return limit;
  }

  public int getLimitedParts() {
    return limitedParts;
  }
}
