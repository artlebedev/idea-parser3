package ru.artlebedev.idea.plugins.parser.parser.parsers;

import com.intellij.lang.PsiBuilder;

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

public class StatementParser {
  /**
   * Asks {@link TokenParserFactory} for a parser which is assosiated with the current token
   *
   * @param builder psi builder of the file
   */
  public static void parseSourceElement(PsiBuilder builder) {
    BaseTokenParser parser = TokenParserFactory.getParser(builder);
    if (!(parser instanceof IndifferentParser)) {
      parser.parseToken(builder);
    } else {
      builder.advanceLexer();
    }
  }
}