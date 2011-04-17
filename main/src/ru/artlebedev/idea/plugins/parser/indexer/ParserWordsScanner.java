package ru.artlebedev.idea.plugins.parser.indexer;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.psi.tree.TokenSet;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserLexer;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;

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

public class ParserWordsScanner extends DefaultWordsScanner {
  public ParserWordsScanner() {
    super(new ParserLexer(), TokenSet.create(ParserTokenTypes.IDENTIFIER, ParserTokenTypes.SELF_KEYWORD, ParserTokenTypes.CALLER_KEYWORD, ParserTokenTypes.RESULT_KEYWORD, ParserTokenTypes.CLASS_STATIC_KEYWORD), ParserTokenTypes.COMMENTS, TokenSet.EMPTY);
  }
}