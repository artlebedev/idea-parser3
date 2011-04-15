package ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.collections.ParserKeywordLookupElements;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2011 ArtLebedev Studio
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

public class ParserDefaultCompletionProvider extends CompletionProvider<CompletionParameters> {
  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
    result.addElement(ParserKeywordLookupElements.TRUE);
    result.addElement(ParserKeywordLookupElements.FALSE);
    result.addElement(ParserKeywordLookupElements.DEF);
    result.addElement(ParserKeywordLookupElements.IN);
    result.addElement(ParserKeywordLookupElements.SLASH_F);
    result.addElement(ParserKeywordLookupElements.SLASH_D);
    result.addElement(ParserKeywordLookupElements.IS);
    result.addElement(ParserKeywordLookupElements.EQ);
    result.addElement(ParserKeywordLookupElements.NE);
    result.addElement(ParserKeywordLookupElements.LT);
    result.addElement(ParserKeywordLookupElements.GT);
    result.addElement(ParserKeywordLookupElements.LE);
    result.addElement(ParserKeywordLookupElements.GE);
  }
}