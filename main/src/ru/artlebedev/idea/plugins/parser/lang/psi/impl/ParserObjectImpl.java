package ru.artlebedev.idea.plugins.parser.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.ParserIcons;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.stdlib.ParserStandardClasses;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserHashKey;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethodReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObjectReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserStrictClass;
import ru.artlebedev.idea.plugins.parser.util.ParserChangeUtil;

import javax.swing.*;
import java.util.HashMap;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006 <a href="mailto:a4blank@yahoo.com">Jay Bird</a>
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
  protected static HashMap<ParserMethod, Integer> lastResolvedMethods = new HashMap<ParserMethod, Integer>();

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
    ASTNode class_static = getNode().findChildByType(ParserTokenTypes.CLASS_STATIC_KEYWORD);
    if(class_static != null)
      return class_static;
    return null;
  }

  public String toString() {
    return "ParserObject";
  }

  public boolean isStatic() {
    PsiElement element = getParent();

    if((element != null) && (element instanceof ParserMethod)
            && ParserLanguageConstants.AUTO_METHOD_NAME.equals(((ParserMethod) element).getName())) {
      return true;
    }

    if((element != null) && (element instanceof ParserMethod)
            && ParserLanguageConstants.CONF_METHOD_NAME.equals(((ParserMethod) element).getName())) {
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
          if(reference.getReferenceClass() != null) {
            if(reference.getReferenceClass().getReference() != null) {
              ParserClass parserClass = (ParserClass) reference.getReferenceClass().getReference().resolve();
              if (parserClass == null)
                return null;
              return parserClass;
            }
          }
        }
        ParserMethodReference methodReference = reference.getReferenceMethod();
        if (methodReference != null) {
          PsiElement element = methodReference.getReference().resolve();
          if (element != null) {
            ParserMethod method = (ParserMethod) element;

            /*
             * This should fix internal looping while resolving recursive call in result
             * -- dwr
             */
            if(method.getName() != null) {
              if(lastResolvedMethods.get(method) != null) {
                Integer count = lastResolvedMethods.get(method);
                count++;
                lastResolvedMethods.put(method, count);

                if(count > 40) {
                  lastResolvedMethods.remove(method);
                  return null;
                }
              } else {
                lastResolvedMethods.put(method, 1);
              }
            }

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
          if ((element != null) && (element != this) && (element instanceof ParserObject)) {
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

  @Nullable
  public ParserPassedParameter getValue() {
    return PsiTreeUtil.getChildOfAnyType(this, ParserPassedParameter.class);
  }

  @Override
  public boolean isGlobal() {
    ParserMethod parserMethod = PsiTreeUtil.getParentOfType(this, ParserMethod.class);

    if(parserMethod == null) {
      return true;
    }

    if(ParserLanguageConstants.AUTO_METHOD_NAME.equals(parserMethod.getName())) {
      return true;
    }

    if(ParserLanguageConstants.CONF_METHOD_NAME.equals(parserMethod.getName())) {
      return true;
    }

    ParserStrictClass parserClass = PsiTreeUtil.getParentOfType(this, ParserStrictClass.class);

    if(parserClass == null) {
      return true;
    }

    ParserObjectReference parserObjectReference = PsiTreeUtil.findChildOfType(this, ParserObjectReference.class);

    if(parserObjectReference != null) {
      if(ParserLanguageConstants.SELF_CLASS_NAME.equals(parserObjectReference.getName())) {
        return true;
      }
    }

    return false;
  }
}

