package ru.artlebedev.idea.plugins.parser.lang.parser.parsers;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import ru.artlebedev.idea.plugins.parser.ParserBundle;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;

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

public class IncludeParser extends BaseTokenParser {
  //todo parse as directories and filenames
  private static final Logger LOG = Logger.getInstance("#ru.artlebedev.idea.plugins.parser.parsers.IncludeParser");
  private boolean newLineDropped = false;

  /**
   * @param builder builder before the @USE keyword
   */
  public void parseToken(PsiBuilder builder) {
    LOG.assertTrue(builder.getTokenType() == ParserTokenTypes.USE_KEYWORD);
    PsiBuilder.Marker include = builder.mark();
    builder.advanceLexer();
    if ((builder.getTokenType() == ParserTokenTypes.NEW_LINE) || builder.eof()) {
      builder.advanceLexer();
    } else {
      include.drop();
      return;
    }
    if (builder.getTokenType() != ParserTokenTypes.IDENTIFIER && !ParserTokenTypes.PATH_CHARACTERS.contains(builder.getTokenType())) {
      builder.error(ParserBundle.message("parser.parse.expected.includePath"));
      include.drop();
      return;
    }

    PsiBuilder.Marker newLineMarker = null;
    newLineDropped = false;
    while (builder.getTokenType() == ParserTokenTypes.IDENTIFIER || ParserTokenTypes.PATH_CHARACTERS.contains(builder.getTokenType())) {
      if (newLineMarker != null && !newLineDropped) {
        newLineMarker.drop();
        newLineDropped = true;
      }
      parsePath(builder);
      if ((builder.getTokenType() == ParserTokenTypes.NEW_LINE) || builder.eof()) {
        builder.advanceLexer();
        newLineMarker = builder.mark();
        newLineDropped = false;
      }
    }

    if (newLineMarker != null && !newLineDropped)
      newLineMarker.rollbackTo();
    include.done(ParserElementTypes.INCLUDE);
  }

  private void parsePath(PsiBuilder builder) {
    LOG.assertTrue(builder.getTokenType() == ParserTokenTypes.IDENTIFIER || ParserTokenTypes.PATH_CHARACTERS.contains(builder.getTokenType()));
    PsiBuilder.Marker path = builder.mark();

    if (ParserTokenTypes.PATH_CHARACTERS.contains(builder.getTokenType())) {
      builder.advanceLexer();
    }

    boolean identifier = true;
    while ((builder.getTokenType() != ParserTokenTypes.NEW_LINE) && !builder.eof()) {
      if (identifier && builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
        PsiBuilder.Marker pathSegment = builder.mark();
        builder.advanceLexer();
        pathSegment.drop();
      } else if (!identifier && ParserTokenTypes.PATH_CHARACTERS.contains(builder.getTokenType())) {
        builder.advanceLexer();
      } else {
        builder.error(ParserBundle.message("parser.parse.expected.pathEnd"));
        break;
      }
      identifier = !identifier;
    }

    path.done(ParserElementTypes.INCLUDE_PATH);
  }
}
