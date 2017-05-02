package ru.artlebedev.idea.plugins.parser.lang.parser;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguage;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserElementType;

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

public interface ParserElementTypes {
  IFileElementType FILE = new IFileElementType(Language.findInstance(ParserLanguage.class));

  IElementType INCLUDE = new ParserElementType("INCLUDE");
  IElementType INCLUDE_PATH = new ParserElementType("INCLUDE_PATH");
  IElementType PATH_SEGMENT = new ParserElementType("PATH_SEGMENT");

  IElementType CLASS = new ParserElementType("CLASS");
  IElementType STATIC_CLASS = new ParserElementType("STATIC_CLASS");
  IElementType DYNAMIC_CLASS = new ParserElementType("DYNAMIC_CLASS");

  IElementType STRICT_CLASS = new ParserElementType("STRICT_CLASS");
  IElementType DYNAMIC_STRICT_CLASS = new ParserElementType("DYNAMIC_STRICT_CLASS");

  IElementType WHITE_SPACE = new ParserElementType("WHITE_SPACE");
  IElementType METHOD = new ParserElementType("METHOD");
  IElementType STATIC_METHOD = new ParserElementType("STATIC_METHOD");
  IElementType OBJECT = new ParserElementType("OBJECT");

  IElementType CALLING_REFERENCE = new ParserElementType("CALLING_REFERENCE");
  IElementType METHOD_REFERENCE = new ParserElementType("METHOD_REFERENCE");
  IElementType CLASS_REFERENCE = new ParserElementType("CLASS_REFERENCE");
  IElementType OBJECT_REFERENCE = new ParserElementType("OBJECT_REFERENCE");
  IElementType PARAMETER_LIST = new ParserElementType("PARAMETER_LIST");
  IElementType PARAMETER = new ParserElementType("PARAMETER");
  IElementType UNIVERSAL_REFERENCE = new ParserElementType("UNIVERSAL_REFERENCE");
  IElementType OBJECT_AND_METHOD_REFERENCE = new ParserElementType("OBJECT_AND_METHOD_REFERENCE");
  IElementType CONSTRUCTOR_CALL = new ParserElementType("CONSTRUCTOR_CALL");
  IElementType PASSED_PARAMETER = new ParserElementType("PASSED_PARAMETER");
  IElementType CLASS_PARENT = new ParserElementType("CLASS_PARENT");
  IElementType HASH_KEY = new ParserElementType("HASH_KEY");

  IElementType STRING = new ParserElementType("STRING");

  /*
   * Well, here I am introducing ParserDoc v2 parsering.
   * This is a proprietary format of myself based on the work of Jay Bird.
   *
   * Probably, I will rewrite all this stuff on JavaDoc.
   *
   * -- dwr
   */
  IElementType PARSERDOC = new ParserElementType("PARSERDOC");
  IElementType PARSERDOC_CONSTRUCTOR_INFO = new ParserElementType("PARSERDOC_CONSTRUCTOR_INFO");
  IElementType PARSERDOC_DYNAMIC_INFO = new ParserElementType("PARSERDOC_DYNAMIC_INFO");
  IElementType PARSERDOC_PARAM_INFO = new ParserElementType("PARSERDOC_PARAM_INFO");
  IElementType PARSERDOC_RESULT_INFO = new ParserElementType("PARSERDOC_RESULT_INFO");
  IElementType PARSERDOC_TYPE_INFO = new ParserElementType("PARSERDOC_TYPE_INFO");
}

