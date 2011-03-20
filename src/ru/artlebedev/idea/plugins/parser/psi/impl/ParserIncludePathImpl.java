package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.psi.ParserElementVisitor;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserIncludePath;
import ru.artlebedev.idea.plugins.parser.utils.ParserFilesUtil;

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

public class ParserIncludePathImpl extends ParserElementImpl implements ParserIncludePath, PsiReference {
  public ParserIncludePathImpl(ASTNode astNode) {
    super(astNode);
  }

  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    return null;
  }

  public String toString() {
    return "ParserIncludePath";
  }

  public String getName() {
    return getNode().getText();
  }

  public PsiReference getReference() {
    return this;
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ParserElementVisitor) {
      ((ParserElementVisitor) visitor).visitParserIncludePath(this);
    } else {
      visitor.visitElement(this);
    }
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
    final Project project = getProject();
    VirtualFile include = ParserFilesUtil.findInClassPaths(project, getName());
    if (include == null)
      return null;
    return PsiManager.getInstance(project).findFile(include);
  }

  public String getCanonicalText() {
    return null;
  }

  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    //todo implement
    return null;
  }

  public PsiElement bindToElement(PsiElement element) throws IncorrectOperationException {
    return null;
  }

  public boolean isReferenceTo(PsiElement element) {
    PsiElement reference = resolve();
    return (reference == element);
  }

  public Object[] getVariants() {
    return ParserFilesUtil.findVariants(getProject());
  }

  public boolean isSoft() {
    return false;
  }
}

