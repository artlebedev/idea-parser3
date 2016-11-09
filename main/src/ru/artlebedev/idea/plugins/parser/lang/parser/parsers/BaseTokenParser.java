package ru.artlebedev.idea.plugins.parser.lang.parser.parsers;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import ru.artlebedev.idea.plugins.parser.ParserBundle;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;

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

public abstract class BaseTokenParser {
  public abstract void parseToken(PsiBuilder builder);

  /**
   * Parses parameters inside brackets
   * Asks {@link TokenParserFactory}
   *
   * @param builder builder in the position before one of the opening braces
   */
  //todo: make it parser ";" as a delimiter between parameters
  protected void parseParameter(PsiBuilder builder) {
    IElementType openedElementType = builder.getTokenType();

    builder.advanceLexer();
    PsiBuilder.Marker marker = builder.mark();
    while (true) {
      if (builder.getTokenType() == ParserTokenTypes.KEY_AT_SIGN || builder.eof()) {
        marker.drop();
        builder.error(ParserBundle.message("parser.parse.expected.closingBracket"));
        return;
      }

      if (
        (openedElementType == ParserTokenTypes.LPAR && builder.getTokenType() == ParserTokenTypes.RPAR) ||
        (openedElementType == ParserTokenTypes.LBRACE && builder.getTokenType() == ParserTokenTypes.RBRACE) ||
        (openedElementType == ParserTokenTypes.LBRACKET && builder.getTokenType() == ParserTokenTypes.RBRACKET)
      ) {
        marker.done(ParserElementTypes.PASSED_PARAMETER);
        break;
      }

      BaseTokenParser parser = TokenParserFactory.getParser(builder);
      if (parser instanceof IndifferentParser) {
        builder.advanceLexer();
      } else {
        parser.parseToken(builder);
      }
    }

    builder.advanceLexer();
  }
}
