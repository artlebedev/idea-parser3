package ru.artlebedev.jreformator.language.abc;

import ru.artlebedev.jreformator.language.type.LanguageType;

import java.util.regex.Pattern;

/**
 * jreformator
 * <p/>
 * Based on code originally written by Vladimir Tokmakov <vlalek@design.ru>
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2011 ArtLebedev Studio
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
