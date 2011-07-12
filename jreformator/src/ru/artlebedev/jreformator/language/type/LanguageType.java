package ru.artlebedev.jreformator.language.type;

import java.util.regex.Pattern;

/**
 * jreformator
 * <p/>
 * Based on code originally written by Vladimir Tokmakov <vlalek@design.ru>
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
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
