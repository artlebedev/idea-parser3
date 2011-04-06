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

public class TypographParams {
  private int entityType = 0;
  private int entityTypeForNbsp = 1;
  private int entityTypeForShy = 1;
  private int entityTypeForSpecial = 1;

  private boolean preserveOriginalNbsp = false;
  private int symbolsNumberForNbsp = 2;

  private boolean collapse09 = false;
  private boolean collapse20 = true;
  private boolean collapse0A = true;

  private boolean replaceCopy = true;
  private boolean replacePlusmn = true;
  private boolean replaceReg = true;
  private boolean replaceTrade = true;
  private boolean replaceTimes = true;
  private boolean replaceHellip = false;

  private boolean noTags = false;

  private boolean preserveOriginalNobr = false;
  private int symbolsNumberForNobr = 2;
  private boolean nobr = true;
  private boolean nobrPhone = true;

  private boolean preserveOriginalMinus = false;

  private boolean preserveOriginalQuotation = false;

  public void setEntityType(int entityType) {
    this.entityType = entityType;
  }

  public int getEntityType() {
    return entityType;
  }

  public void setEntityTypeForNbsp(int entityTypeForNbsp) {
    this.entityTypeForNbsp = entityTypeForNbsp;
  }

  public int getEntityTypeForNbsp() {
    return entityTypeForNbsp;
  }

  public void setEntityTypeForShy(int entityTypeForShy) {
    this.entityTypeForShy = entityTypeForShy;
  }

  public int getEntityTypeForShy() {
    return entityTypeForShy;
  }

  public void setEntityTypeForSpecial(int entityTypeForSpecial) {
    this.entityTypeForSpecial = entityTypeForSpecial;
  }

  public int getEntityTypeForSpecial() {
    return entityTypeForSpecial;
  }

  public boolean isPreserveOriginalNbsp() {
    return preserveOriginalNbsp;
  }

  public void setPreserveOriginalNbsp(boolean preserveOriginalNbsp) {
    this.preserveOriginalNbsp = preserveOriginalNbsp;
  }

  public int getSymbolsNumberForNbsp() {
    return symbolsNumberForNbsp;
  }

  public void setSymbolsNumberForNbsp(int symbolsNumberForNbsp) {
    this.symbolsNumberForNbsp = symbolsNumberForNbsp;
  }

  public boolean isCollapse09() {
    return collapse09;
  }

  public void setCollapse09(boolean collapse09) {
    this.collapse09 = collapse09;
  }

  public boolean isCollapse20() {
    return collapse20;
  }

  public void setCollapse20(boolean collapse20) {
    this.collapse20 = collapse20;
  }

  public boolean isCollapse0A() {
    return collapse0A;
  }

  public void setCollapse0A(boolean collapse0A) {
    this.collapse0A = collapse0A;
  }

  public boolean isReplaceCopy() {
    return replaceCopy;
  }

  public void setReplaceCopy(boolean replaceCopy) {
    this.replaceCopy = replaceCopy;
  }

  public boolean isReplacePlusmn() {
    return replacePlusmn;
  }

  public void setReplacePlusmn(boolean replacePlusmn) {
    this.replacePlusmn = replacePlusmn;
  }

  public boolean isReplaceReg() {
    return replaceReg;
  }

  public void setReplaceReg(boolean replaceReg) {
    this.replaceReg = replaceReg;
  }

  public boolean isReplaceTrade() {
    return replaceTrade;
  }

  public void setReplaceTrade(boolean replaceTrade) {
    this.replaceTrade = replaceTrade;
  }

  public boolean isReplaceTimes() {
    return replaceTimes;
  }

  public void setReplaceTimes(boolean replaceTimes) {
    this.replaceTimes = replaceTimes;
  }

  public boolean isReplaceHellip() {
    return replaceHellip;
  }

  public void setReplaceHellip(boolean replaceHellip) {
    this.replaceHellip = replaceHellip;
  }

  public boolean isNoTags() {
    return noTags;
  }

  public void setNoTags(boolean noTags) {
    this.noTags = noTags;
  }

  public boolean isPreserveOriginalNobr() {
    return preserveOriginalNobr;
  }

  public void setPreserveOriginalNobr(boolean preserveOriginalNobr) {
    this.preserveOriginalNobr = preserveOriginalNobr;
  }

  public int getSymbolsNumberForNobr() {
    return symbolsNumberForNobr;
  }

  public void setSymbolsNumberForNobr(int symbolsNumberForNobr) {
    this.symbolsNumberForNobr = symbolsNumberForNobr;
  }

  public boolean isNobr() {
    return nobr;
  }

  public void setNobr(boolean nobr) {
    this.nobr = nobr;
  }

  public boolean isNobrPhone() {
    return nobrPhone;
  }

  public void setNobrPhone(boolean nobrPhone) {
    this.nobrPhone = nobrPhone;
  }

  public boolean isPreserveOriginalMinus() {
    return preserveOriginalMinus;
  }

  public void setPreserveOriginalMinus(boolean preserveOriginalMinus) {
    this.preserveOriginalMinus = preserveOriginalMinus;
  }

  public boolean isPreserveOriginalQuotation() {
    return preserveOriginalQuotation;
  }

  public void setPreserveOriginalQuotation(boolean preserveOriginalQuotation) {
    this.preserveOriginalQuotation = preserveOriginalQuotation;
  }
}
