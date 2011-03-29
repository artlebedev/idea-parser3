package ru.artlebedev.idea.plugins.parser.utils;

import com.intellij.psi.tree.IElementType;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;

/**
 * idea-parser3: slightly useful plugin.
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

/**
 * Various utilities for Parsers
 *
 * @see ru.artlebedev.idea.plugins.parser.parser.parsers
 */
public class ParserParserUtil {
  /**
   * Checks if token got from builder belongs to tokens allowed as identifiers
   *
   * @see ru.artlebedev.idea.plugins.parser.parser.parsers.MethodReferenceParser#parseToken(com.intellij.lang.PsiBuilder)
   * @see ru.artlebedev.idea.plugins.parser.parser.parsers.ObjectParser#parseToken(com.intellij.lang.PsiBuilder)
   *
   * @param tokenType
   * @return
   */
  public static boolean isIdentifierToken(IElementType tokenType) {
    if(tokenType == ParserTokenTypes.IDENTIFIER ||
       tokenType == ParserTokenTypes.RESULT_KEYWORD ||
       tokenType == ParserTokenTypes.SELF_KEYWORD ||
       tokenType == ParserTokenTypes.CALLER_KEYWORD) {
      return true;
    } else {
      return false;
    }
  }
}
