package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Icons;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.ParserPsiUtil;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClassParent;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.utils.ParserChangeUtil;

import javax.swing.*;

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

public class ParserClassImpl extends ParserElementImpl implements ParserClass {

  public ParserClassImpl(ASTNode astNode) {
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

  public String toString() {
    return "ParserClass";
  }

  public Icon getIcon() {
    return Icons.CLASS_ICON;
  }

  public Icon getIcon(int i) {
    return Icons.CLASS_ICON;
  }

  public ParserMethod[] getMethods() {
    PsiElement[] psiElements = ParserPsiUtil.collectElementsAsList(this, ParserMethod.class).toArray(new PsiElement[0]);

    ParserClass parentClass = getParentClass();
    ParserMethod[] parentMethods = new ParserMethod[0];
    if (parentClass != null) {
      parentMethods = parentClass.getMethods();
    }
    ParserMethod[] result = new ParserMethod[psiElements.length + parentMethods.length];
    for (int i = 0; i < psiElements.length; i++) {
      result[i] = (ParserMethod) psiElements[i];
    }
    for (int i = 0; i < parentMethods.length; i++) {
      result[psiElements.length + i] = parentMethods[i];
    }
    return result;
  }

  public ParserClass getParentClass() {
    ParserClassParent parent = PsiTreeUtil.getChildOfType(this, ParserClassParent.class);
    if (parent != null) {
      ParserClassReferenceImpl reference = PsiTreeUtil.getChildOfType(parent, ParserClassReferenceImpl.class);
      if (reference != null) {
        ParserClass parserClass = (ParserClass) reference.resolve();
        if (parserClass != null)
          return parserClass;
      }
    }

    return null;
  }
}
