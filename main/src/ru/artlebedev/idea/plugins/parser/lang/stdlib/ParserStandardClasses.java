package ru.artlebedev.idea.plugins.parser.lang.stdlib;

import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
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
  ParserClass BOOL = ParserStandardClassesHelper.loadStandardClassFromString("bool", ParserStdLib.parser3_bool);
  ParserClass VOID = ParserStandardClassesHelper.loadStandardClassFromString("void", ParserStdLib.parser3_void);
  ParserClass STRING = ParserStandardClassesHelper.loadStandardClassFromString("string", ParserStdLib.parser3_string);
  ParserClass INT = ParserStandardClassesHelper.loadStandardClassFromString("int", ParserStdLib.parser3_int);
  ParserClass DOUBLE = ParserStandardClassesHelper.loadStandardClassFromString("double", ParserStdLib.parser3_double);
  ParserClass CONSOLE = ParserStandardClassesHelper.loadStandardClassFromString("console", ParserStdLib.parser3_console);
  ParserClass ENV = ParserStandardClassesHelper.loadStandardClassFromString("env", ParserStdLib.parser3_env);
  ParserClass HASH = ParserStandardClassesHelper.loadStandardClassFromString("hash", ParserStdLib.parser3_hash);
  ParserClass TABLE = ParserStandardClassesHelper.loadStandardClassFromString("table", ParserStdLib.parser3_table);
  ParserClass FORM = ParserStandardClassesHelper.loadStandardClassFromString("form", ParserStdLib.parser3_form);
  ParserClass COOKIE = ParserStandardClassesHelper.loadStandardClassFromString("cookie", ParserStdLib.parser3_cookie);
  ParserClass CURL = ParserStandardClassesHelper.loadStandardClassFromString("curl", ParserStdLib.parser3_curl);
  ParserClass DATE = ParserStandardClassesHelper.loadStandardClassFromString("date", ParserStdLib.parser3_date);
  ParserClass FILE = ParserStandardClassesHelper.loadStandardClassFromString("file", ParserStdLib.parser3_file);
  ParserClass HASHFILE = ParserStandardClassesHelper.loadStandardClassFromString("hashfile", ParserStdLib.parser3_hashfile);
  ParserClass IMAGE = ParserStandardClassesHelper.loadStandardClassFromString("image", ParserStdLib.parser3_image);
  ParserClass INET = ParserStandardClassesHelper.loadStandardClassFromString("inet", ParserStdLib.parser3_inet);
  ParserClass JUNCTION = ParserStandardClassesHelper.loadStandardClassFromString("junction", ParserStdLib.parser3_junction);
  ParserClass JSON = ParserStandardClassesHelper.loadStandardClassFromString("json", ParserStdLib.parser3_json);
  ParserClass MAIL = ParserStandardClassesHelper.loadStandardClassFromString("mail", ParserStdLib.parser3_mail);
  ParserClass MATH = ParserStandardClassesHelper.loadStandardClassFromString("math", ParserStdLib.parser3_math);
  ParserClass MEMORY = ParserStandardClassesHelper.loadStandardClassFromString("memory", ParserStdLib.parser3_memory);
  ParserClass REFLECTION = ParserStandardClassesHelper.loadStandardClassFromString("reflection", ParserStdLib.parser3_reflection);
  ParserClass REGEX = ParserStandardClassesHelper.loadStandardClassFromString("regex", ParserStdLib.parser3_regex);
  ParserClass REQUEST = ParserStandardClassesHelper.loadStandardClassFromString("request", ParserStdLib.parser3_request);
  ParserClass RESPONSE = ParserStandardClassesHelper.loadStandardClassFromString("response", ParserStdLib.parser3_response);
  ParserClass STATUS = ParserStandardClassesHelper.loadStandardClassFromString("status", ParserStdLib.parser3_status);
  ParserClass XDOC = ParserStandardClassesHelper.loadStandardClassFromString("xdoc", ParserStdLib.parser3_xdoc);
  ParserClass XNODE = ParserStandardClassesHelper.loadStandardClassFromString("xnode", ParserStdLib.parser3_xnode);
}
