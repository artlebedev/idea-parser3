package ru.artlebedev.idea.plugins.parser.lang.parser.parsers;



import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.diagnostic.Logger;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;
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

public class StringParser extends BaseTokenParser {
    private static final Logger LOG = Logger.getInstance("#ru.artlebedev.idea.plugins.parser.parsers.StringParser");

    public void parseToken(PsiBuilder builder) {
        IElementType type = builder.getTokenType();

        LOG.assertTrue(type == ParserTokenTypes.SQUOT || type == ParserTokenTypes.QUOT);

        PsiBuilder.Marker string = builder.mark();
        builder.advanceLexer();

        while (!builder.eof()) {
            if (!(builder.getTokenType() == type)) {
                if (!(builder.getTokenType() == ParserTokenTypes.RBRACKET || builder.getTokenType() == ParserTokenTypes.RPAR)) {
                    ParserParserUtil.innerParse(builder);
                } else {
                    string.done(ParserElementTypes.STRING);
                    break;
                }
            } else {
                builder.advanceLexer();
                string.done(ParserElementTypes.STRING);
                break;
            }
        }
    }
}