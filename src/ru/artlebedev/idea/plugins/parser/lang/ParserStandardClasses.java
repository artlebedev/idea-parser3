package ru.artlebedev.idea.plugins.parser.lang;

import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;

/**
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2006 Jay Bird <a4blank@yahoo.com>
 * Copyright 2006-2011 ArtLebedev Studio
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

public interface ParserStandardClasses {
  ParserClass BOOL = ParserStandardClassesHelper.loadStandardClass("bool.p");
  ParserClass VOID = ParserStandardClassesHelper.loadStandardClass("void.p");
  ParserClass STRING = ParserStandardClassesHelper.loadStandardClass("string.p");
  ParserClass INT = ParserStandardClassesHelper.loadStandardClass("int.p");
  ParserClass HASH = ParserStandardClassesHelper.loadStandardClass("hash.p");
  ParserClass TABLE = ParserStandardClassesHelper.loadStandardClass("table.p");
  ParserClass COOKIE = ParserStandardClassesHelper.loadStandardClass("cookie.p");
  ParserClass CURL = ParserStandardClassesHelper.loadStandardClass("curl.p");
  ParserClass DATE = ParserStandardClassesHelper.loadStandardClass("date.p");
}
