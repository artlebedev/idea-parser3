package ru.artlebedev.idea.plugins.parser.lang.parser.parsers;

import com.intellij.lang.PsiBuilder;
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

public class ClassParentParser extends BaseTokenParser {
  public void parseToken(PsiBuilder builder) {
    PsiBuilder.Marker classParent = builder.mark();
    builder.advanceLexer();
    if ((builder.getTokenType() == ParserTokenTypes.NEW_LINE) || builder.eof()) {
      builder.advanceLexer();
      if (builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
        PsiBuilder.Marker parentClass = builder.mark();
        builder.advanceLexer();
        parentClass.done(ParserElementTypes.CLASS_REFERENCE);
        classParent.done(ParserElementTypes.CLASS_PARENT);
      } else {
        builder.error("parser.parse.expected.className");
        classParent.done(ParserElementTypes.CLASS_PARENT);
      }
    } else {
      classParent.drop();
      builder.error(ParserBundle.message("parser.parse.expected.baseTokenEnd"));
    }
  }
}
