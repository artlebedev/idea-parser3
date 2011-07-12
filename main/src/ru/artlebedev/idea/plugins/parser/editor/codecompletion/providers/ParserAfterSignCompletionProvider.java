package ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserClassAttributeLookupElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserLookupElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserMethodLookupElement;

/**
 * idea-parser3: the most advanced parser3 ide.
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

public class ParserAfterSignCompletionProvider
        extends CompletionProvider<CompletionParameters> {
  public static String[] lookupElements = new String[]{
          "unhandled_exception",
          "main",
          "static:",
          "GET_DEFAULT",
          "SET_DEFAULT",
          "GET_",
          "SET_"
  };

  public static String[] methodLookupElements = new String[]{
          "auto",
          "conf"
  };

  public static String[] classAttributeLookupElements = new String[]{
          "CLASS",
          "USE",
          "BASE",
          "OPTIONS"
  };

  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters,
                                ProcessingContext context,
                                @NotNull CompletionResultSet result) {
    for(String lookupElement : lookupElements) {
      result.addElement(new ParserLookupElement(lookupElement));
    }

    for(String methodLookupElement : methodLookupElements) {
      result.addElement(new ParserMethodLookupElement(methodLookupElement));
    }

    for(String classAttributeLookupElement : classAttributeLookupElements) {
      result.addElement(
              new ParserClassAttributeLookupElement(classAttributeLookupElement));
    }
  }
}
