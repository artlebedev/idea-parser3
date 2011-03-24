package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.ParserIcons;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.ParserStandardClasses;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserHashKey;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethodReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObjectReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.utils.ParserChangeUtil;

import javax.swing.*;

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

public class ParserObjectImpl extends ParserElementImpl implements ParserObject {
  public ParserObjectImpl(ASTNode astNode) {
    super(astNode);
  }

  public int getTextOffset() {
    final ASTNode name = findNameNode();
    return name != null ? name.getStartOffset() : super.getTextOffset();
  }

  public String getName() {
    ASTNode nameNode = findNameNode();
    if (nameNode != null)
      return nameNode.getText();
    return null;
  }

  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    ASTNode identifier = ParserChangeUtil.createNameIdentifier(getProject(), name);
    ASTNode nameNode = findNameNode();
    if (nameNode != null)
      getNode().replaceChild(nameNode, identifier);
    return this;
  }

  public ASTNode findNameNode() {
    ASTNode identifier = getNode().findChildByType(ParserTokenTypes.IDENTIFIER);
    if (identifier != null)
      return identifier;
    ASTNode result = getNode().findChildByType(ParserTokenTypes.RESULT_KEYWORD);
    if (result != null)
      return result;
    ASTNode self = getNode().findChildByType(ParserTokenTypes.SELF_KEYWORD);
    if(self != null)
      return self;
    ASTNode caller = getNode().findChildByType(ParserTokenTypes.CALLER_KEYWORD);
    if(caller != null)
      return caller;
    return null;
  }

  public String toString() {
    return "ParserObject";
  }

  public boolean isStatic() {
    PsiElement element = getParent();

    if((element != null) && (element instanceof ParserMethod)
            && ((ParserMethod) element).getName().equals(ParserLanguageConstants.AUTO_METHOD_NAME)) {
      return true;
    }

    return false;
  }

  public Icon getIcon(int flags) {
    /*
     * Here we introducing icon
     * for noticing that this object
     * is a static variable relatively
     * to the class
     */
    if(isStatic())
      return ParserIcons.PARSER_PROPERTY_ICON;

    return ParserIcons.PARSER_VARIABLE_ICON;
  }

  public ParserClass getType() {
    ParserPassedParameter value = getValue();
    //System.out.println("get type " + getName());
    if (value != null) {
      PsiElement[] children = value.getChildren();
      if (children.length == 0 && value.getText().equals("")) {
        return ParserStandardClasses.STRING;
      }
      if (children.length == 1 && children[0] instanceof ParserCallingReference) {
        ParserCallingReference reference = (ParserCallingReference) children[0];
        if (reference.isConstructorInvoked()) {
          ParserClass parserClass = (ParserClass) reference.getReferenceClass().getReference().resolve();
          if (parserClass == null)
            return null;
          return parserClass;
        }
        ParserMethodReference methodReference = reference.getReferenceMethod();
        if (methodReference != null) {
          PsiElement element = methodReference.getReference().resolve();
          if (element != null) {
            ParserMethod method = (ParserMethod) element;
            ParserClass returnValueType = method.getReturnValueType();
            if (returnValueType != null) {
              return returnValueType;
            }
          }
          return null;
        }
        ParserObjectReference[] referenceObjects = reference.getReferenceObjects();
        if (referenceObjects.length > 0) {
          ParserObjectReferenceImpl referenceObject = (ParserObjectReferenceImpl) referenceObjects[referenceObjects.length - 1];
          PsiElement element = referenceObject.resolve();
          if (element != null && element != this) {
            ParserObject parserObject = (ParserObject) element;
            return parserObject.getType();
          }
          return null;
        }
      }
      boolean hasNotHashKeyChildren = false;
      for (PsiElement child : children) {
        if (!(child instanceof ParserHashKey)) {
          hasNotHashKeyChildren = true;
        }
      }
      if (!hasNotHashKeyChildren) {
//				return ParserStandardClasses.HASH;
      }

      if (value.getNode().getTreePrev().getElementType() == ParserTokenTypes.LBRACKET) {
        return ParserStandardClasses.STRING;
      }

      if (value.getNode().getTreePrev().getElementType() == ParserTokenTypes.LPAR && value.getText().matches("(\\d+|\\d*\\.\\d+)")) {
        return ParserStandardClasses.INT;
      }
    }
    return ParserStandardClasses.VOID;
  }

  @NotNull
  public ParserPassedParameter getValue() {
    return PsiTreeUtil.getChildOfAnyType(this, ParserPassedParameter.class);
  }
}

