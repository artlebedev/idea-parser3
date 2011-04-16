package ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserLookupElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserMethodLookupElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserVariableLookupElement;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.resolve.ParserResolveUtil;

import java.util.Collection;

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

public class ParserAfterDollarCompletionProvider extends CompletionProvider<CompletionParameters> {
  public final String[] lookupElements = new String[]{
          "caller.",
          "result",
          "locals",
          "self.",
          "MAIN:"
  };

  public final String[] variableLookupElements = new String[]{
          "exception.type",
          "exception.source",
          "exception.file",
          "exception.lineno",
          "exception.colno",
          "exception.comment",
          "caller.self"
  };

  public final String[] methodLookupElements = new String[]{
          "exception.handled"
  };

  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
    for(String lookupElement : lookupElements) {
      result.addElement(new ParserLookupElement(lookupElement));
    }

    for(String variableLookupElement : variableLookupElements) {
      result.addElement(new ParserVariableLookupElement(variableLookupElement));
    }

    for(String methodLookupElement : methodLookupElements) {
      result.addElement(new ParserMethodLookupElement(methodLookupElement));
    }

    Collection<ParserFile> parserFiles = parameters.getPosition().getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();
    Collection<ParserClass> parserClasses = ParserResolveUtil.getClassesFromFiles(parserFiles);
    for(ParserClass parserClass : parserClasses) {
      if(ParserResolveUtil.collectStaticObjectDeclarations(parserClass).size() > 0) {
        result.addElement(new ParserLookupElement(parserClass.getName() + ":"));
      }
    }
  }
}
