package ru.artlebedev.idea.plugins.parser.util;

import com.intellij.psi.tree.IElementType;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;

/**
 * idea-parser3: the most advanced parser3 ide.
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

/**
 * Various utilities for Parsers
 *
 * @see ru.artlebedev.idea.plugins.parser.lang.parser.parsers
 */
public class ParserParserUtil {
  /**
   * Checks if token got from builder belongs to tokens allowed as identifiers
   *
   * @see ru.artlebedev.idea.plugins.parser.lang.parser.parsers.MethodReferenceParser#parseToken(com.intellij.lang.PsiBuilder)
   * @see ru.artlebedev.idea.plugins.parser.lang.parser.parsers.ObjectParser#parseToken(com.intellij.lang.PsiBuilder)
   * @see com.intellij.lang.PsiBuilder
   *
   * @param tokenType token came from builder (PsiBuilder)
   * @return true if token could be identifier
   */
  public static boolean isIdentifierToken(IElementType tokenType) {
    if(tokenType == ParserTokenTypes.IDENTIFIER ||
       tokenType == ParserTokenTypes.RESULT_KEYWORD ||
       tokenType == ParserTokenTypes.SELF_KEYWORD ||
       tokenType == ParserTokenTypes.CALLER_KEYWORD ||
       tokenType == ParserTokenTypes.CLASS_STATIC_KEYWORD) {
      return true;
    } else {
      return false;
    }
  }
}
