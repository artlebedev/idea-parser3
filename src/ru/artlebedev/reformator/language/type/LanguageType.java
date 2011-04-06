package ru.artlebedev.reformator.language.type;

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

public class LanguageType {
  private String commonUpper;
  private String commonLower;

  private String upper;
  private String lower;
  private String common;

  private Pattern commonPattern;

  public LanguageType(String commonUpper, String commonLower) {
    this.commonUpper = commonUpper;
    this.commonLower = commonLower;
  }

  public String getCommonUpper() {
    return commonUpper;
  }

  public String getCommonLower() {
    return commonLower;
  }

  public void setUpper(String upper) {
    this.upper = upper;
  }

  public String getUpper() {
    return upper;
  }

  public void setLower(String lower) {
    this.lower = lower;
  }

  public String getLower() {
    return lower;
  }

  public void setCommon(String common) {
    this.common = common;
  }

  public String getCommon() {
    return common;
  }

  public void setCommonPattern(Pattern commonPattern) {
    this.commonPattern = commonPattern;
  }

  public Pattern getCommonPattern() {
    return commonPattern;
  }
}
