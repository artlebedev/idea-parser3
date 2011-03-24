package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethodReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObjectReference;

import java.util.ArrayList;

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

public class ParserCallingReferenceImpl extends ParserElementImpl implements ParserCallingReference {
  public ParserCallingReferenceImpl(ASTNode astNode) {
    super(astNode);
  }

  public boolean isMethodCalled() {
    ParserMethodReference method = getReferenceMethod();
    return method != null;
  }

  public String toString() {
    if (!isMethodCalled()) {
      return toStringAsObject();
    }
    return toStringAsMethod();
  }

  public String toStringAsMethod() {
    StringBuilder str = new StringBuilder("call of ");

    ParserMethodReference referenceMethod = getReferenceMethod();
    if (referenceMethod != null) {
      str.append("method ").append(referenceMethod.getName());
    }

    ParserObjectReference[] referenceObjects = getReferenceObjects();
    for (int i = referenceObjects.length - 1; i >= 0; i--) {
      ParserObjectReference referenceObject = referenceObjects[i];
      str.append(" in object ").append(referenceObject.getName());
    }

    ParserClassReference referenceClass = getReferenceClass();
    if (referenceClass != null) {
      str.append(" @ class ").append(referenceClass.getName());
    }

    return str.toString();
  }

  public String toStringAsObject() {
    StringBuilder str = new StringBuilder("reference to ");

    ParserObjectReference[] referenceObjects = getReferenceObjects();
    for (int i = referenceObjects.length - 1; i >= 0; i--) {
      ParserObjectReference referenceObject = referenceObjects[i];
      if (i == referenceObjects.length - 1) {
        str.append("object ").append(referenceObject.getName());
      } else {
        str.append(" in object ").append(referenceObject.getName());
      }
    }

    ParserClassReference referenceClass = getReferenceClass();
    if (referenceClass != null) {
      str.append(" @ class ").append(referenceClass.getName());
    }

    return str.toString();
  }

  public ParserClassReference getReferenceClass() {
    PsiElement[] children = getChildren();
    for (PsiElement element : children) {
      if (element instanceof ParserClassReference) {
        return (ParserClassReference) element;
      }
    }
    return null;
  }

  public ParserObjectReference[] getReferenceObjects() {
    ArrayList<ParserObjectReference> result = new ArrayList<ParserObjectReference>();
    PsiElement[] children = getChildren();
    for (PsiElement element : children) {
      if (element instanceof ParserObjectReference) {
        result.add((ParserObjectReference) element);
      }
    }
    if (result.size() == 0) {
      return new ParserObjectReference[0];
    }
    return result.toArray(new ParserObjectReference[0]);
  }

  public ParserMethodReference getReferenceMethod() {
    PsiElement[] children = getChildren();
    for (PsiElement element : children) {
      if (element instanceof ParserMethodReference) {
        return (ParserMethodReference) element;
      }
    }
    return null;
  }

  public boolean isConstructorInvoked() {
    ASTNode[] colons = getNode().getChildren(TokenSet.create(ParserTokenTypes.COLON));
    return colons.length == 2;
  }
}
