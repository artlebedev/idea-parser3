package ru.artlebedev.reformator.language;

import ru.artlebedev.reformator.language.abc.LanguageAbc;
import ru.artlebedev.reformator.language.abc.LanguageAbcMatch;
import ru.artlebedev.reformator.language.abc.LanguageAbcs;
import ru.artlebedev.reformator.language.type.LanguageType;
import ru.artlebedev.reformator.language.type.LanguageTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
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

public class Language {
  private static volatile Language instance;

  private String upperLetters = "";
  private String lowerLetters = "";

  private String letters;

  private Pattern simpleWordPattern;

  private Language() {
    for(LanguageType type : LanguageTypes.allLanguageTypes) {
      type.setUpper(type.getCommonUpper());
      upperLetters += type.getCommonUpper();

      type.setLower(type.getCommonLower());
      lowerLetters += type.getCommonLower();

      type.setCommon(type.getCommonUpper() + type.getCommonLower());
      type.setCommonPattern(Pattern.compile("[" + type.getCommon() + "]"));
    }

    for(LanguageAbc abc : LanguageAbcs.allLanguageAbcs) {
      abc.setUpper(abc.getType().getCommonUpper() + abc.getUniqueUpper());
      abc.getType().setUpper(abc.getType().getUpper() + abc.getUniqueUpper());

      upperLetters += abc.getUniqueUpper();

      abc.setLower(abc.getType().getCommonLower() + abc.getUniqueLower());
      abc.getType().setLower(abc.getType().getLower() + abc.getUniqueLower());

      lowerLetters += abc.getUniqueLower();

      abc.setUnique(abc.getUniqueUpper() + abc.getUniqueLower());

      abc.setUniquePattern(Pattern.compile("[" + abc.getUnique() + "]"));
    }

    while(upperLetters.matches("(.)(.*?)\\1")) {
      upperLetters = upperLetters.replaceAll("(.)(.*?)\\1", "$1$2");
    }

    while(lowerLetters.matches("(.)(.*?)\\1")) {
      lowerLetters = lowerLetters.replaceAll("(.)(.*?)\\1", "$1$2");
    }

    letters = upperLetters + lowerLetters;

    simpleWordPattern = Pattern.compile("[" + letters + "0-9’′']");
  }

  public String get(String text) {
    return get(text, new LanguageParams());
  }

  public String get(String text, LanguageParams params) {
    text = text.replaceAll("[^\\s]*_[^\\s]*", " ");

    if(text.length() > params.getLimit()) {
      int part_length = (int) Math.floor(Double.valueOf(params.getLimit()) / Double.valueOf(params.getLimitedParts()));
      int part_offset = (int) Math.floor((Double.valueOf(text.length()) - Double.valueOf(params.getLimit())) / Double.valueOf(params.getLimitedParts()));

      String limitedText = "";
      for(int i = 0; i < params.getLimitedParts(); i++) {
        limitedText += text.substring(i * part_offset + 1, i * part_offset + part_length);
      }
      text = limitedText;
    }

    HashMap<LanguageType, Integer> type_matches_counter = new HashMap<LanguageType, Integer>();
    int matches = 0;

    for(LanguageType type : LanguageTypes.allLanguageTypes) {
      Matcher matcher = type.getCommonPattern().matcher(text);
      while(matcher.find()) {
        matches++;
      }

      type_matches_counter.put(type, matches);
    }

    List<LanguageAbcMatch> abcList = new ArrayList<LanguageAbcMatch>();
    int matchesCount = 0;
    int i = 0;

    for(LanguageAbc abc : LanguageAbcs.allLanguageAbcs) {
      matchesCount = type_matches_counter.get(abc.getType());

      Matcher matcher = abc.getUniquePattern().matcher(text);
      while(matcher.find()) {
        matchesCount++;
      }

      abcList.add(new LanguageAbcMatch(i,  abc.getName(), matchesCount));
      i++;
    }

    Collections.sort(abcList);

    return abcList.get(0).getName();
  }

  public String getUpperLetters() {
    return upperLetters;
  }

  public String getLowerLetters() {
    return lowerLetters;
  }

  public static Language getInstance() {
    if(instance == null) {
      synchronized (Language.class) {
        if(instance == null)
          instance = new Language();
      }
    }
    return instance;
  }
}
