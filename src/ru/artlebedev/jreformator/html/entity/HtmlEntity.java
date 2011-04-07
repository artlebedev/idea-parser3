package ru.artlebedev.jreformator.html.entity;

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

public class HtmlEntity {
  private String name;
  private String variant1;
  private String variant2;
  private String variant3;
  private String variant4;

  public HtmlEntity(String name, String variant1, String variant2, String variant3, String variant4) {
    this.name = name;
    this.variant1 = variant1;
    this.variant2 = variant2;
    this.variant3 = variant3;
    this.variant4 = variant4;
  }

  public String getName() {
    return name;
  }

  public String getVariant1() {
    return variant1;
  }

  public String getVariant2() {
    return variant2;
  }

  public String getVariant3() {
    return variant3;
  }

  public String getVariant4() {
    return variant4;
  }
}
