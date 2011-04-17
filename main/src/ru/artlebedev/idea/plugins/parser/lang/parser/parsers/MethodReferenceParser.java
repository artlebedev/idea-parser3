package ru.artlebedev.idea.plugins.parser.lang.parser.parsers;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import ru.artlebedev.idea.plugins.parser.ParserBundle;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.util.ParserParserUtil;

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

public class MethodReferenceParser extends BaseTokenParser {

  private static final Logger LOG = Logger.getInstance("#ru.artlebedev.idea.plugins.parser.parsers.MethodReferenceParser");
  private boolean errorShown = false;
  private boolean objectDone = false;
  private boolean classDone = false;

  /**
   * @param builder builder before the ^
   */
  public void parseToken(PsiBuilder builder) {

    LOG.assertTrue(builder.getTokenType() == ParserTokenTypes.HAT_SIGN);
    errorShown = false;
    objectDone = false;
    classDone = false;
    PsiBuilder.Marker reference = builder.mark();

    builder.advanceLexer();

    while (!ParserTokenTypes.OPENING_BRACES.contains(builder.getTokenType())) {
      if (ParserParserUtil.isIdentifierToken(builder.getTokenType())) {
        parseReference(builder);
      } else if (ParserTokenTypes.KEYWORDS.contains(builder.getTokenType()) || builder.getTokenType() == ParserTokenTypes.DOLLAR) {
        builder.advanceLexer();
      } else {
        if (!errorShown)
          builder.error(ParserBundle.message("parser.parse.expected.methodName"));
        break;
      }
    }


    parseParameters(builder);


    reference.done(ParserElementTypes.CALLING_REFERENCE);
  }

  /**
   * parses parameters which follow the method name
   *
   * @param builder before the opening brace of the first parameter
   */
  private void parseParameters(PsiBuilder builder) {
    while (ParserTokenTypes.OPENING_BRACES.contains(builder.getTokenType())) {
      parseParameter(builder);
    }
  }

  /**
   * parses one parameter
   * Asks {@link TokenParserFactory} for other parsers
   *
   * @param builder before the opening brace of a parameter
   */
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
   * @param builder before the identifier
   */
  private void parseReference(PsiBuilder builder) {
    LOG.assertTrue(
            builder.getTokenType() == ParserTokenTypes.IDENTIFIER ||
            builder.getTokenType() == ParserTokenTypes.RESULT_KEYWORD ||
            builder.getTokenType() == ParserTokenTypes.SELF_KEYWORD ||
            builder.getTokenType() == ParserTokenTypes.CLASS_STATIC_KEYWORD
    );

    PsiBuilder.Marker someRef = builder.mark();
    builder.advanceLexer();

    if (ParserTokenTypes.OPENING_BRACES.contains(builder.getTokenType())) {
      someRef.done(ParserElementTypes.METHOD_REFERENCE);

    } else if (builder.getTokenType() == ParserTokenTypes.DOT) {
      objectDone = true;
      someRef.done(ParserElementTypes.OBJECT_REFERENCE);

    } else if (builder.getTokenType() == ParserTokenTypes.COLON) {
      classDone = true;
      someRef.done(ParserElementTypes.CLASS_REFERENCE);
      PsiBuilder.Marker secondColon = builder.mark();
      builder.advanceLexer();
      if (builder.getTokenType() != ParserTokenTypes.COLON) {
        secondColon.rollbackTo();
      } else {
        secondColon.drop();
      }
    } else {
      if (objectDone) {
        someRef.done(ParserElementTypes.OBJECT_AND_METHOD_REFERENCE);
      } else if (classDone) {
        someRef.done(ParserElementTypes.METHOD_REFERENCE);
      } else {
        someRef.done(ParserElementTypes.UNIVERSAL_REFERENCE);
      }
      builder.error(ParserBundle.message("parser.parse.expected.methodBracket"));
      errorShown = true;
    }
    if (builder.getTokenType() == ParserTokenTypes.COLON || builder.getTokenType() == ParserTokenTypes.DOT)
      builder.advanceLexer();
  }
}
