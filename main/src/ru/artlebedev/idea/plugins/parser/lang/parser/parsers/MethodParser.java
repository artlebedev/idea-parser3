package ru.artlebedev.idea.plugins.parser.lang.parser.parsers;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import ru.artlebedev.idea.plugins.parser.ParserBundle;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserPsiUtil;

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

public class MethodParser extends BaseTokenParser {

  private static final Logger LOG = Logger.getInstance("#ru.artlebedev.idea.plugins.parser.parsers.MethodParser");

  /**
   * @param builder before the @
   */
  public void parseToken(PsiBuilder builder) {
    boolean staticMethod = false;

    LOG.assertTrue(builder.getTokenType() == ParserTokenTypes.KEY_AT_SIGN);

    if(builder.getTokenText().contains("static:")) {
      staticMethod = true;
    }

    PsiBuilder.Marker methodToken = builder.mark();
    builder.advanceLexer();

    if (builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
      /*
       * @auto is always static
       * -- dwr
       */
      if((builder.getTokenText() != null) && ParserLanguageConstants.AUTO_METHOD_NAME.equals(builder.getTokenText()))
        staticMethod = true;

      if((builder.getTokenText() != null) && ParserLanguageConstants.CONF_METHOD_NAME.equals(builder.getTokenText()))
        staticMethod = true;

//			PsiBuilder.Marker methodName = builder.mark();
      builder.advanceLexer();
//			methodName.done(ParserElementTypes.METHOD_NAME);

      if (builder.getTokenType() == ParserTokenTypes.LBRACKET) {
        parseParameterList(builder);

      } else {
        builder.error(ParserBundle.message("parser.parse.expected.parameterBracket"));
      }

    } else {
      builder.error(ParserBundle.message("parser.parse.expected.methodName"));
    }

    while (!ParserTokenTypes.METHOD_DELIMITERS.contains(ParserPsiUtil.getNextTokenIgnoringNLandParserDoc(builder))) {
      BaseTokenParser parser = TokenParserFactory.getParser(builder);
      if (!(parser instanceof IndifferentParser)) {
        parser.parseToken(builder);
      } else {
        builder.advanceLexer();
      }
    }

    methodToken.done(staticMethod ? ParserElementTypes.STATIC_METHOD : ParserElementTypes.METHOD);
  }

  /**
   * @param builder builder before the [ bracket of the paremeter list
   */
  private void parseParameterList(PsiBuilder builder) {
    PsiBuilder.Marker parameterList = builder.mark();
    PsiBuilder.Marker parameter = null;
    boolean parameterDone = false;
    builder.advanceLexer();
    while (builder.getTokenType() != ParserTokenTypes.RBRACKET) {
      if (parameter != null && !parameterDone) {
        parameter.done(ParserElementTypes.PARAMETER);
        parameterDone = true;
      }
      if (builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
        parameter = builder.mark();
        parameterDone = false;
        builder.advanceLexer();
      } else if (builder.getTokenType() == ParserTokenTypes.SEMICOLON) {
        builder.advanceLexer();
      } else if (builder.getTokenType() == ParserTokenTypes.RBRACKET || builder.eof() || builder.getTokenType() == ParserTokenTypes.WHITE_SPACE) {
        break;
      } else {
        builder.error(ParserBundle.message("parser.parse.expected.parameter"));
        builder.advanceLexer();
      }
    }

    if (parameter != null && !parameterDone) {
      parameter.done(ParserElementTypes.PARAMETER);
    }

    builder.advanceLexer();
    parameterList.done(ParserElementTypes.PARAMETER_LIST);
  }
}
