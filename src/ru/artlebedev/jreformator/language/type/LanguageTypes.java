package ru.artlebedev.jreformator.language.type;

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

public interface LanguageTypes {
  public static final LanguageType CYR = new LanguageType("АБВГДЕЖЗКЛМНОПРСТУФХЧШ", "абвгдежзклмнопрстуфхчш");
  public static final LanguageType LAT = new LanguageType("ABDEGHILMNOPRSTU", "abdeghilmnoprstu");

  public static final LanguageType[] allLanguageTypes = new LanguageType[]{CYR, LAT};
}
