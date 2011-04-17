package ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.elements.ParserExceptionTypeLookupElement;

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

public class ParserExceptionTypeCompletionProvider extends CompletionProvider<CompletionParameters> {
  public final static String[] exceptionTypeLookupElements = new String[]{
          "parser.compile",
          "parser.runtime",
          "parser.interrupted",
          "number.zerodivision",
          "number.format",
          "file.missing",
          "file.access",
          "file.read",
          "file.execute",
          "date.range",
          "pcre.execute",
          "image.format",
          "sql.connect",
          "sql.execute",
          "xml",
          "smtp.connect",
          "smtp.execute",
          "email.format",
          "email.send",
          "http.host",
          "http.connect",
          "http.response",
          "http.status",
          "http.timeout",
          "curl.host",
          "curl.connect",
          "curl.status",
          "curl.ssl",
          "curl.timeout",
          "curl.fail"
  };

  @Override
  protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
    for(String exceptionTypeLookupElement : exceptionTypeLookupElements) {
      result.addElement(new ParserExceptionTypeLookupElement(exceptionTypeLookupElement));
    }
  }
}