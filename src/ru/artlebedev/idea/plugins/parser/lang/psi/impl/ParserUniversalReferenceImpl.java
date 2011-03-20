package ru.artlebedev.idea.plugins.parser.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserUniversalReference;
import ru.artlebedev.idea.plugins.parser.util.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.util.lookup.ParserLookupUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
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

public class ParserUniversalReferenceImpl extends ParserElementImpl implements ParserUniversalReference, PsiReference {
  public ParserUniversalReferenceImpl(ASTNode astNode) {
    super(astNode);
  }

  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    return null;
  }

  public PsiReference getReference() {
    return this;
  }

  public PsiElement getElement() {
    return this;
  }

  public TextRange getRangeInElement() {
    final PsiElement firstChild = getFirstChild();
    final int startOffsetInParent = firstChild.getStartOffsetInParent();
    return new TextRange(startOffsetInParent, startOffsetInParent + getNode().getTextLength());
  }

  @Nullable
  public PsiElement resolve() {
    return null;
  }

  public String getCanonicalText() {
    return null;
  }

  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    return null;
  }

  public PsiElement bindToElement(PsiElement element) throws IncorrectOperationException {
    return null;
  }

  public boolean isReferenceTo(PsiElement element) {
    return false;
  }

  public Object[] getVariants() {
    List<PsiElement> result = new ArrayList<PsiElement>();

    Collection<ParserFile> parserFiles = getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();

    result.addAll(ParserResolveUtil.collectParameters(this));
    result.addAll(ParserResolveUtil.collectObjectDeclarations(this));
    result.addAll(ParserResolveUtil.getClassesFromFiles(parserFiles));
    PsiFile file = getContainingFile();
    ParserClass parserClass = PsiTreeUtil.getChildOfType(file, ParserClass.class);
    if (parserClass != null) {
      result.addAll(Arrays.asList(parserClass.getMethods()));
    } else
      result.addAll(ParserResolveUtil.collectMethods(this.getContainingFile()));

    return ParserLookupUtil.createSmartLookupItems(result);
  }

  public boolean isSoft() {
    return false;
  }
}

