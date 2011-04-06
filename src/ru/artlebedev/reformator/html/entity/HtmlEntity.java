package ru.artlebedev.reformator.html.entity;

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

public class HtmlEntity {
  private String variant1;
  private String variant2;
  private String variant3;
  private String variant4;

  public HtmlEntity(String variant1, String variant2, String variant3, String variant4) {
    this.variant1 = variant1;
    this.variant2 = variant2;
    this.variant3 = variant3;
    this.variant4 = variant4;
  }

  public String getVariant1() {
    return variant1;
  }

  public void setVariant1(String variant1) {
    this.variant1 = variant1;
  }

  public String getVariant2() {
    return variant2;
  }

  public void setVariant2(String variant2) {
    this.variant2 = variant2;
  }

  public String getVariant3() {
    return variant3;
  }

  public void setVariant3(String variant3) {
    this.variant3 = variant3;
  }

  public String getVariant4() {
    return variant4;
  }

  public void setVariant4(String variant1) {
    this.variant4 = variant4;
  }
}
