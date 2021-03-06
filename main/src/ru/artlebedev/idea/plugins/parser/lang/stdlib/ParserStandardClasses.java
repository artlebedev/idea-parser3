package ru.artlebedev.idea.plugins.parser.lang.stdlib;

import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006 <a href="mailto:a4blank@yahoo.com">Jay Bird</a>
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
  ParserClass DOUBLE = ParserStandardClassesHelper.loadStandardClass("double.p");
  ParserClass CONSOLE = ParserStandardClassesHelper.loadStandardClass("console.p");
  ParserClass ENV = ParserStandardClassesHelper.loadStandardClass("env.p");
  ParserClass HASH = ParserStandardClassesHelper.loadStandardClass("hash.p");
  ParserClass TABLE = ParserStandardClassesHelper.loadStandardClass("table.p");
  ParserClass FORM = ParserStandardClassesHelper.loadStandardClass("form.p");
  ParserClass COOKIE = ParserStandardClassesHelper.loadStandardClass("cookie.p");
  ParserClass CURL = ParserStandardClassesHelper.loadStandardClass("curl.p");
  ParserClass DATE = ParserStandardClassesHelper.loadStandardClass("date.p");
  ParserClass FILE = ParserStandardClassesHelper.loadStandardClass("file.p");
  ParserClass HASHFILE = ParserStandardClassesHelper.loadStandardClass("hashfile.p");
  ParserClass IMAGE = ParserStandardClassesHelper.loadStandardClass("image.p");
  ParserClass INET = ParserStandardClassesHelper.loadStandardClass("inet.p");
  ParserClass JUNCTION = ParserStandardClassesHelper.loadStandardClass("junction.p");
  ParserClass JSON = ParserStandardClassesHelper.loadStandardClass("json.p");
  ParserClass MAIL = ParserStandardClassesHelper.loadStandardClass("mail.p");
  ParserClass MATH = ParserStandardClassesHelper.loadStandardClass("math.p");
  ParserClass MEMORY = ParserStandardClassesHelper.loadStandardClass("memory.p");
  ParserClass REFLECTION = ParserStandardClassesHelper.loadStandardClass("reflection.p");
  ParserClass REGEX = ParserStandardClassesHelper.loadStandardClass("regex.p");
  ParserClass REQUEST = ParserStandardClassesHelper.loadStandardClass("request.p");
  ParserClass RESPONSE = ParserStandardClassesHelper.loadStandardClass("response.p");
  ParserClass STATUS = ParserStandardClassesHelper.loadStandardClass("status.p");
  ParserClass XDOC = ParserStandardClassesHelper.loadStandardClass("xdoc.p");
  ParserClass XNODE = ParserStandardClassesHelper.loadStandardClass("xnode.p");
}
