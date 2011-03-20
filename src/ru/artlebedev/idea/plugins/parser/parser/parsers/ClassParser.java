package ru.artlebedev.idea.plugins.parser.parser.parsers;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.ParserBundle;

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

public class ClassParser extends BaseTokenParser {
  private static final Logger LOG = Logger.getInstance("#net.jay.parser.Parser.Parsers.IncludeParser");

  /**
   * @param builder builder before the @CLASS keyword
   */
  public void parseToken(PsiBuilder builder) {
    LOG.assertTrue(builder.getTokenType() == ParserTokenTypes.CLASS_KEYWORD);
    PsiBuilder.Marker classToken = builder.mark();
    builder.advanceLexer();
    if (builder.getTokenType() == ParserTokenTypes.NEW_LINE) {
      builder.advanceLexer();
    } else {
      classToken.drop();
      return;
    }

    IElementType className = builder.getTokenType();
    if (className == ParserTokenTypes.IDENTIFIER) {
//			PsiBuilder.Marker classNameToken = builder.mark();
      builder.advanceLexer();

      if (builder.getTokenType() != ParserTokenTypes.NEW_LINE) {
        builder.error(ParserBundle.message("parser.parse.expected.classNameEnd"));
//				classNameToken.drop();
      } else {
//				classNameToken.done(ParserElementTypes.CLASS_NAME);
      }

      while (!builder.eof() || builder.getTokenType() == ParserTokenTypes.CLASS_KEYWORD) {
        BaseTokenParser parser = TokenParserFactory.getParser(builder);
        if (!(parser instanceof IndifferentParser)) {
          parser.parseToken(builder);
        } else {
          builder.advanceLexer();
        }
      }
      classToken.done(ParserElementTypes.CLASS);
    } else {
      classToken.drop();
      builder.error(ParserBundle.message("parser.parse.expected.className"));
    }
  }
}
