package ru.artlebedev.idea.plugins.parser.lang.parser.parsers;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import ru.artlebedev.idea.plugins.parser.ParserBundle;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.util.ParserParserUtil;

/**
 * idea-parser3: slightly useful plugin.
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

public class ObjectParser extends BaseTokenParser {
  private static final Logger LOG = Logger.getInstance("#ru.artlebedev.idea.plugins.parser.parsers.ObjectParser");
  private boolean identifierMet;
  private boolean noIdentifierAfterPunctuation;

  /**
   * @param builder builder of the current file
   */
  public void parseToken(PsiBuilder builder) {
    LOG.assertTrue(builder.getTokenType() == ParserTokenTypes.DOLLAR);

    PsiBuilder.Marker expr = builder.mark();
    builder.advanceLexer();
    if (builder.getTokenType() == ParserTokenTypes.DOT) {
      parseHash(builder);
      expr.drop();
      return;
    }
    identifierMet = false;
    noIdentifierAfterPunctuation = false;
    while (true) {
      if (builder.getTokenType() == ParserTokenTypes.DOLLAR) {
        builder.advanceLexer();
        continue;
      }
      if (ParserParserUtil.isIdentifierToken(builder.getTokenType())) {
        identifierMet = true;
        parseReference(builder);
      } else if (builder.getTokenType() == ParserTokenTypes.WHITE_SPACE || builder.getTokenType() == ParserTokenTypes.NEW_LINE || builder.eof()) {
        expr.done(ParserElementTypes.CALLING_REFERENCE);
        break;
      } else if (ParserTokenTypes.OPENING_BRACES.contains(builder.getTokenType())) {
        if (noIdentifierAfterPunctuation) {
          if (builder.getTokenType() == ParserTokenTypes.LBRACKET) {
            parseParameter(builder);
          } else {
            builder.error(ParserBundle.message("parser.parse.expected.parameterBracketOrObjectName"));
          }
          expr.done(ParserElementTypes.CALLING_REFERENCE);
        } else {
          parseParameter(builder);
          expr.done(ParserElementTypes.OBJECT);
        }
        break;
      } else {
        if (!identifierMet) {
          expr.drop();
          builder.error(ParserBundle.message("parser.parse.expected.objectName"));
        } else {
          expr.done(ParserElementTypes.CALLING_REFERENCE);
        }
        break;
      }
    }
  }

  private void parseHash(PsiBuilder builder) {
    builder.advanceLexer();
    PsiBuilder.Marker marker = builder.mark();
    if (builder.getTokenType() == ParserTokenTypes.DOLLAR) {
      builder.advanceLexer();
    }
    if (builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
      builder.advanceLexer();
      if (ParserTokenTypes.OPENING_BRACES.contains(builder.getTokenType())) {
        parseParameter(builder);
      } else {
        builder.error(ParserBundle.message("parser.parse.expected.methodBracket"));
      }
    } else {
      if (builder.getTokenType() == ParserTokenTypes.LBRACKET) {
        parseParameter(builder);
      } else {
        builder.error(ParserBundle.message("parser.parse.expected.objectName"));
      }
    }
    marker.done(ParserElementTypes.HASH_KEY);
  }

  /**
   * Parses parameters inside brackets
   * Asks {@link TokenParserFactory}
   *
   * @param builder builder in the position before one of the opening braces
   */
  //todo: make it parser ";" as a delimiter between parameters
  private void parseParameter(PsiBuilder builder) {
    String openedBrace = "";

    if (builder.getTokenType() == ParserTokenTypes.LPAR) {
      openedBrace = "(";
    }
    if (builder.getTokenType() == ParserTokenTypes.LBRACE) {
      openedBrace = "{";
    }
    if (builder.getTokenType() == ParserTokenTypes.LBRACKET) {
      openedBrace = "[";
    }
    builder.advanceLexer();
    PsiBuilder.Marker marker = builder.mark();
    while (true) {
      if (builder.getTokenType() == ParserTokenTypes.KEY_AT_SIGN || builder.eof()) {
        marker.drop();
        builder.error(ParserBundle.message("parser.parse.expected.closingBracket"));
        return;
      }

      /* if(builder.getTokenType() == ParserTokenTypes.SEMICOLON) {
        marker.done(ParserElementTypes.PASSED_PARAMETER);
      } else */

      //System.out.println("=== " + builder.getTokenText());

      if (openedBrace.equals("(") && builder.getTokenType() == ParserTokenTypes.RPAR) {
        marker.done(ParserElementTypes.PASSED_PARAMETER);
        break;
      } else if (openedBrace.equals("{") && builder.getTokenType() == ParserTokenTypes.RBRACE) {
        marker.done(ParserElementTypes.PASSED_PARAMETER);
        break;
      } else if (openedBrace.equals("[") && builder.getTokenType() == ParserTokenTypes.RBRACKET) {
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

  /**
   * Parses identifier as a class reference if it is followed by COLON
   * parses identifier as an object reference if it is followed by DOT
   * parser identifier as an object name if it is followed by one of the opening braces
   *
   * @param builder builder in the position in front of an identifier
   */
  private void parseReference(PsiBuilder builder) {
    PsiBuilder.Marker someRef = builder.mark();
    builder.advanceLexer();
    if (builder.getTokenType() == ParserTokenTypes.COLON) {
      someRef.done(ParserElementTypes.CLASS_REFERENCE);
      builder.advanceLexer();
      if (builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
        parseReference(builder);
      } else {
        noIdentifierAfterPunctuation = true;
      }
      return;
    }
    if (builder.getTokenType() == ParserTokenTypes.DOT) {
      someRef.done(ParserElementTypes.OBJECT_REFERENCE);
      builder.advanceLexer();
      if (builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
        parseReference(builder);
      } else {
        noIdentifierAfterPunctuation = true;
      }
      return;
    }
    if (ParserTokenTypes.OPENING_BRACES.contains(builder.getTokenType())) {
//			someRef.done(ParserElementTypes.OBJECT_NAME);
      someRef.drop();
      return;
    }
    someRef.done(ParserElementTypes.OBJECT_REFERENCE);
  }
}
