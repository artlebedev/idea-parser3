package ru.artlebedev.reformator.language.abc;

import ru.artlebedev.reformator.language.type.LanguageType;

import java.util.regex.Pattern;

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

public class LanguageAbc {
  private String name;
  private LanguageType type;
  private String uniqueUpper;
  private String uniqueLower;
  private String[] words;

  private String upper;
  private String lower;

  private String unique;

  private Pattern uniquePattern;

  public LanguageAbc(String name, LanguageType type, String uniqueUpper, String uniqueLower, String[] words) {
    this.name = name;
    this.type = type;
    this.uniqueUpper = uniqueUpper;
    this.uniqueLower = uniqueLower;
    this.words = words;
  }

  public String getName() {
    return name;
  }

  public LanguageType getType() {
    return type;
  }

  public String getUniqueUpper() {
    return uniqueUpper;
  }

  public String getUniqueLower() {
    return uniqueLower;
  }

  public String[] getWords() {
    return words;
  }

  public void setUpper(String upper) {
    this.upper = upper;
  }

  public void setLower(String lower) {
    this.lower = lower;
  }

  public void setUnique(String unique) {
    this.unique = unique;
  }

  public String getUnique() {
    return unique;
  }

  public void setUniquePattern(Pattern uniquePattern) {
    this.uniquePattern = uniquePattern;
  }

  public Pattern getUniquePattern() {
    return uniquePattern;
  }
}
