package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.psi.ParserElementVisitor;
import ru.artlebedev.idea.plugins.parser.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.util.ParserChangeUtil;
import ru.artlebedev.idea.plugins.parser.util.ParserResolveUtil;

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

public class ParserClassReferenceImpl extends ParserElementImpl implements ParserClassReference, PsiReference {
  public ParserClassReferenceImpl(ASTNode astNode) {
    super(astNode);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ParserElementVisitor) {
      ((ParserElementVisitor) visitor).visitParserClassReference(this);
    } else {
      visitor.visitElement(this);
    }
  }

  public String toString() {
    return "ParserClassReference [" + getName() + "]";
  }

  public PsiReference getReference() {
    return this;
  }


  public String getName() {
    return getNode().getText();
  }

  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    final ASTNode nameElement = ParserChangeUtil.createNameIdentifier(getProject(), name);
    getNode().replaceChild(getNode().findChildByType(ParserTokenTypes.IDENTIFIER), nameElement);
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

    if (getName().equals(ParserLanguageConstants.BASE_CLASS_NAME)) {
//			ParserClass parentClass = PsiTreeUtil.getParentOfType(this, ParserClass.class, true, true);
      ParserClass parentClass = PsiTreeUtil.getParentOfType(this, ParserClass.class, true);
      if (parentClass != null) {
        return parentClass.getParentClass();
      }
    }

    Collection<ParserFile> parserFiles = getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();

    for (ParserFile parserFile : parserFiles) {
      ParserClass parserClass = PsiTreeUtil.getChildOfType(parserFile, ParserClass.class);
      if (parserClass != null && parserClass.getName().equals(getName())) {
        return parserClass;
      }
    }

    List<PsiElement> psiElements = ParserResolveUtil.collectClassIncludes(getContainingFile());
    for (PsiElement element : psiElements) {
      ParserClass parserClass = (ParserClass) element;
      getProject().getComponent(ParserFileIndex.class).contributeClass(parserClass);
      if (parserClass.getName().equals(getName())) {
        return parserClass;
      }
    }

    return null;
  }

  public String getCanonicalText() {
    return null;
  }

  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    return setName(newElementName);
  }

  public PsiElement bindToElement(PsiElement element) throws IncorrectOperationException {
    return null;
  }

  public boolean isReferenceTo(PsiElement element) {
    return element == resolve();
  }

  public Object[] getVariants() {
    return new Object[0];
  }

  public boolean isSoft() {
    return false;
  }
}
