package ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.builders.ParserClassAttributeLookupElementBuilder;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.builders.ParserLookupElementBuilder;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.builders.ParserMethodLookupElementBuilder;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexandr Pozdeev</a>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2011-2020 ArtLebedev Studio
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
public class ParserAfterSignCompletionProvider
  extends CompletionProvider<CompletionParameters> {
  private static String[] lookupElements = new String[]{
    "unhandled_exception",
    "main",
    "static",
    "GET_DEFAULT",
    "SET_DEFAULT",
    "GET_",
    "SET_"
  };

  private static String[] methodLookupElements = new String[]{
    "auto",
    "conf"
  };

  private static String[] classAttributeLookupElements = new String[]{
    "CLASS",
    "USE",
    "BASE",
    "OPTIONS"
  };

  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters,
                                ProcessingContext context,
                                @NotNull CompletionResultSet result) {
    for (String lookupElement : lookupElements) {
      result.addElement(ParserLookupElementBuilder.create(lookupElement));
    }

    for (String methodLookupElement : methodLookupElements) {
      result.addElement(ParserMethodLookupElementBuilder.create(methodLookupElement));
    }

    for (String classAttributeLookupElement : classAttributeLookupElements) {
      result.addElement(ParserClassAttributeLookupElementBuilder.create(classAttributeLookupElement));
    }
  }
}
