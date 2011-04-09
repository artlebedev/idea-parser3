package ru.artlebedev.idea.plugins.parser.documentationProvider;

import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;

import java.util.List;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2006 Jay Bird <a4blank@yahoo.com>
 * Copyright 2006-2011 ArtLebedev Studio
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

public class ParserDocumentationProvider implements DocumentationProvider {
  @Override
  public String getQuickNavigateInfo(PsiElement psiElement, PsiElement psiElement1) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<String> getUrlFor(PsiElement psiElement, PsiElement psiElement1) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String generateDoc(PsiElement psiElement, PsiElement psiElement1) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object o, PsiElement psiElement) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public PsiElement getDocumentationElementForLink(PsiManager psiManager, String s, PsiElement psiElement) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}