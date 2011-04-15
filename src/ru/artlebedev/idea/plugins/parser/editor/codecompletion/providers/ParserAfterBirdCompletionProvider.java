package ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.collections.ParserMethodLookupElements;

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

public class ParserAfterBirdCompletionProvider extends CompletionProvider<CompletionParameters> {
  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
    result.addElement(ParserMethodLookupElements.REM);
    result.addElement(ParserMethodLookupElements.TAINT);
    result.addElement(ParserMethodLookupElements.UNTAINT);
    result.addElement(ParserMethodLookupElements.IF);
    result.addElement(ParserMethodLookupElements.BREAK);
    result.addElement(ParserMethodLookupElements.CONTINUE);
    result.addElement(ParserMethodLookupElements.CONNECT);
    result.addElement(ParserMethodLookupElements.USE);
    result.addElement(ParserMethodLookupElements.CACHE);
    result.addElement(ParserMethodLookupElements.EVAL);
  }
}
