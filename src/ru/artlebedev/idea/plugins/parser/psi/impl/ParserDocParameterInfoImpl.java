package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserDocParameterInfo;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserDocTypeInfo;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.utils.ParserChangeUtil;
import ru.artlebedev.idea.plugins.parser.psi.lookup.ParserLookupUtil;

import java.util.ArrayList;
import java.util.Arrays;
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

public class ParserDocParameterInfoImpl extends ParserElementImpl implements ParserDocParameterInfo, PsiReference {
  public ParserDocParameterInfoImpl(ASTNode astNode) {
    super(astNode);
  }

  public String getName() {
    ASTNode nameNode = findNameNode();
    if (nameNode != null)
      return nameNode.getText();
    return null;
  }

  public int getTextOffset() {
    ASTNode name = findNameNode();
    return name != null ? name.getStartOffset() : super.getTextOffset();
  }


  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    ASTNode identifier = ParserChangeUtil.createNameIdentifier(getProject(), name);
    ASTNode nameNode = findNameNode();
    if (nameNode != null)
      getNode().replaceChild(nameNode, identifier);
    return this;
  }

  public ASTNode findNameNode() {
    return getNode().findChildByType(ParserTokenTypes.IDENTIFIER);
  }

  public PsiElement getElement() {
    return this;
  }

  public PsiReference getReference() {
    return this;
  }

  public TextRange getRangeInElement() {
    final PsiElement firstChild = getFirstChild();
    final int startOffsetInParent = firstChild.getStartOffsetInParent();
    return new TextRange(startOffsetInParent, startOffsetInParent + getNode().getTextLength());
  }

  @Nullable
  public PsiElement resolve() {
    ParserMethod method = PsiTreeUtil.getNextSiblingOfType(this.getParent(), ParserMethod.class);
    if (method != null) {
      PsiElement[] parameters = method.getParameterList().getChildren();
      for (PsiElement parameter : parameters) {
        if (((ParserParameterImpl) parameter).getName().equals(getName())) {
          return parameter;
        }
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
    return element instanceof ParserParameterImpl && element == resolve();
  }

  public Object[] getVariants() {
    ParserMethod method = PsiTreeUtil.getNextSiblingOfType(this.getParent(), ParserMethod.class);
    if (method != null) {
      return ParserLookupUtil.createSmartLookupItems(Arrays.asList(method.getParameterList().getChildren()));
    }
    return new Object[0];
  }

  public boolean isSoft() {
    return false;
  }

  public String toString() {
    return "ParserDocParameterInfo";
  }

  public ParserClass[] getType() {
    List<ParserClass> result = new ArrayList<ParserClass>();
    PsiElement[] psiElements = this.getChildren();
    for (PsiElement psiElement : psiElements) {
      if (psiElement instanceof ParserDocTypeInfo) {
        PsiElement element = psiElement.getReference().resolve();
        if (element != null) {
          result.add((ParserClass) element);
        }
      }
    }
    return result.toArray(new ParserClass[0]);
  }
}