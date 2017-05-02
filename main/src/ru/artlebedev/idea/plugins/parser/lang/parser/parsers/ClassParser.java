package ru.artlebedev.idea.plugins.parser.lang.parser.parsers;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Contract;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.ParserBundle;
import ru.artlebedev.idea.plugins.parser.util.ParserParserUtil;

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

public class ClassParser extends BaseTokenParser {
  private static final Logger LOG = Logger.getInstance("#ru.artlebedev.idea.plugins.parser.parsers.ClassParser");

  /**
   * @param builder builder before the @CLASS keyword
   */
  public void parseToken(PsiBuilder builder) {
    LOG.assertTrue(builder.getTokenType() == ParserTokenTypes.CLASS_KEYWORD);

    PsiBuilder.Marker classToken = builder.mark();
    builder.advanceLexer();
    if (builder.getTokenType() == ParserTokenTypes.WHITE_SPACE) {
      builder.advanceLexer();
    } else {
      classToken.drop();
      return;
    }

    IElementType className = builder.getTokenType();
    if (className == ParserTokenTypes.IDENTIFIER) {
      builder.advanceLexer();

      if (!(builder.getTokenType() == ParserTokenTypes.WHITE_SPACE))
        builder.error(ParserBundle.message("parser.parse.expected.classNameEnd"));

      boolean staticClassDecl = false;
      boolean dynamicClassDecl = false;
      boolean strictClassDecl = false;
      boolean partialClassDecl = false;

      while (!builder.eof() || builder.getTokenType() == ParserTokenTypes.CLASS_KEYWORD) {
        IElementType tokenType = builder.getTokenType();

        switch(tokenType.toString()){
          case "STATIC_KEYWORD":
            staticClassDecl = verifyClassType(builder, staticClassDecl, dynamicClassDecl);
            break;
          case "DYNAMIC_KEYWORD":
            dynamicClassDecl = verifyClassType(builder, dynamicClassDecl, staticClassDecl);
            break;
          case "LOCALS_KEYWORD":
            strictClassDecl = verifyClassType(builder, strictClassDecl, false);
            break;
          case "PARTIAL_KEYWORD":
            partialClassDecl = verifyClassType(builder, partialClassDecl, false);
            break;
          default:
            break;
        }

        ParserParserUtil.innerParse(builder);
      }

      if(staticClassDecl) {
        classToken.done(ParserElementTypes.STATIC_CLASS);
      } else if(dynamicClassDecl) {
        if(strictClassDecl) {
          classToken.done(ParserElementTypes.DYNAMIC_STRICT_CLASS);
        } else {
          classToken.done(ParserElementTypes.DYNAMIC_CLASS);
        }
      } else {
        if(strictClassDecl) {
          classToken.done(ParserElementTypes.STRICT_CLASS);
        } else {
          classToken.done(ParserElementTypes.CLASS);
        }
      }
    } else {
      classToken.drop();
      builder.error(ParserBundle.message("parser.parse.expected.className"));
    }
  }

  private boolean verifyClassType(PsiBuilder builder, boolean verifiableClassDecl, boolean alternativeClassDecl) {
    if(!alternativeClassDecl) {
      if(!verifiableClassDecl) {
        return true;
      } else {
        builder.error(ParserBundle.message("parser.parse.error.classDuplicateOption"));
      }
    } else {
      builder.error(ParserBundle.message("parser.parse.error.collisionStaticDynamic"));
    }
    return false;
  }
}
