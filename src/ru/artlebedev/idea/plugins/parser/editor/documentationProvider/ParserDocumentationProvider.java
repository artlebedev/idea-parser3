package ru.artlebedev.idea.plugins.parser.editor.documentationProvider;

import com.intellij.lang.documentation.QuickDocumentationProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import ru.artlebedev.idea.plugins.parser.documentation.provider.DocumentationProvider;

import java.util.List;

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

public class ParserDocumentationProvider extends QuickDocumentationProvider {
  private static final Logger LOG = Logger.getInstance("#ParserDocumentationProvider");

  @Override
  public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
    LOG.info("getQuickNavigateInfo " + element);

    return null;
  }

  @Override
  public List<String> getUrlFor(PsiElement element, PsiElement originalElement) {
    LOG.info("getUrlFor " + element);

    return null;
  }

  /**
   * Generates the documentation for a given PsiElement. The original
   * element is the token the caret was on at the time the documentation
   * was called.
   *
   * @param element         The element for which the documentation has been requested.
   * @param originalElement The element the caret is on
   * @return The HTML formatted String which contains the documentation.
   */
  public String generateDoc(PsiElement element, PsiElement originalElement) {
    LOG.info("generateDoc() for " + element + " and " + originalElement);

    return DocumentationProvider.documentation(element, originalElement);
  }

  public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
    LOG.info("getDocumentationElementForLookupItem: element: " + element);

    return element;
  }

  public PsiElement getDocumentationElementForLink(PsiManager psiManager, String link, PsiElement context) {
    LOG.info("getDocumentationElementForLink: element: " + context);

    return context;
  }
}