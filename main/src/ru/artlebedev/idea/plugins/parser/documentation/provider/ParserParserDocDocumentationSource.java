package ru.artlebedev.idea.plugins.parser.documentation.provider;

import com.intellij.psi.PsiElement;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
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

public class ParserParserDocDocumentationSource implements DocumentationSource {
  @Override
  public String documentation(PsiElement element, PsiElement originalElement) {
    if(element instanceof ParserMethod) {
      return ((ParserMethod) element).getParserDoc();
    }
    return null;
  }

  @Override
  public String documentationUrl(PsiElement element, PsiElement originalElement) {
    return null;
  }
}
