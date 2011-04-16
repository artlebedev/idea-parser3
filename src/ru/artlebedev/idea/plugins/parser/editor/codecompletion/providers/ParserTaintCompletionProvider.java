package ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.ParserTaintLookupElement;

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

public class ParserTaintCompletionProvider extends CompletionProvider<CompletionParameters> {
  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
    result.addElement(new ParserTaintLookupElement("parser-code"));
    result.addElement(new ParserTaintLookupElement("as-is"));
    result.addElement(new ParserTaintLookupElement("file-spec"));
    result.addElement(new ParserTaintLookupElement("http-header"));
    result.addElement(new ParserTaintLookupElement("mail-header"));
    result.addElement(new ParserTaintLookupElement("uri"));
    result.addElement(new ParserTaintLookupElement("sql"));
    result.addElement(new ParserTaintLookupElement("js"));
    result.addElement(new ParserTaintLookupElement("regex"));
    result.addElement(new ParserTaintLookupElement("xml"));
    result.addElement(new ParserTaintLookupElement("html"));
    result.addElement(new ParserTaintLookupElement("optimized-as-is"));
    result.addElement(new ParserTaintLookupElement("optimized-xml"));
    result.addElement(new ParserTaintLookupElement("optimized-html"));
  }
}
