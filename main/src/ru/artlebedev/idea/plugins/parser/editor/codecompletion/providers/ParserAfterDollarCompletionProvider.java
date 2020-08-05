package ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserClassLookupElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserMethodLookupElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserStaticVariableLookupElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserVariableLookupElement;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexander Pozdeev</a>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006-2020 ArtLebedev Studio
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

/**
 * @author <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * @version 1.0
 */
public class ParserAfterDollarCompletionProvider
  extends CompletionProvider<CompletionParameters> {

  private final static String[] variableLookupElements = new String[]{
    "exception.type",
    "exception.source",
    "exception.file",
    "exception.lineno",
    "exception.colno",
    "exception.comment",
    "ignored",
    "caller",
    "result",
    "locals",
    "self"
  };

  private final static String[] staticVariableLookupElements = new String[]{
    "MAIN:SQL.connect-string"
  };

  private final static String[] methodLookupElements = new String[]{
    "exception.handled"
  };

  private final static String[] classLookupElements = new String[]{
    "MAIN"
  };

  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters,
                                ProcessingContext context,
                                @NotNull CompletionResultSet result) {
    for (String variableLookupElement : variableLookupElements) {
      result.addElement(ParserVariableLookupElement.create(variableLookupElement));
    }

    for (String staticVariableLookupElement : staticVariableLookupElements) {
      result.addElement(ParserStaticVariableLookupElement.create(staticVariableLookupElement));
    }

    for (String methodLookupElement : methodLookupElements) {
      result.addElement(ParserMethodLookupElement.create(methodLookupElement));
    }

    for (String classLookupElement : classLookupElements) {
      result.addElement(ParserClassLookupElement.create(classLookupElement));
    }
  }
}
