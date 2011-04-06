package ru.artlebedev.reformator.language.abc;

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

public class LanguageAbcMatch implements Comparable {
  private int i;
  private String name;
  private int matchesCount;

  public LanguageAbcMatch(int i, String name, int matchesCount) {
    this.i = i;
    this.name = name;
    this.matchesCount = matchesCount;
  }

  public int getI() {
    return i;
  }

  public String getName() {
    return name;
  }

  public int getMatchesCount() {
    return matchesCount;
  }

  @Override
  public int compareTo(Object o) {
    if(getMatchesCount() > ((LanguageAbcMatch) o).getMatchesCount()) {
      return 1;
    } else {
      return -1;
    }
  }
}
