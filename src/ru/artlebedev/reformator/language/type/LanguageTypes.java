package ru.artlebedev.reformator.language.type;

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

public interface LanguageTypes {
  public static final LanguageType CYR = new LanguageType("АБВГДЕЖЗКЛМНОПРСТУФХЧШ", "абвгдежзклмнопрстуфхчш");
  public static final LanguageType LAT = new LanguageType("ABDEGHILMNOPRSTU", "abdeghilmnoprstu");

  public static final LanguageType[] allLanguageTypes = new LanguageType[]{CYR, LAT};
}
