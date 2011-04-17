package ru.artlebedev.idea.plugins.parser.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.HasMethods;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.IsTyped;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethodReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObjectReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.lookup.ParserLookupUtil;
import ru.artlebedev.idea.plugins.parser.lang.psi.resolve.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.util.ParserChangeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * idea-parser3: the most advanced parser3 ide.
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

public class ParserMethodReferenceImpl extends ParserElementImpl implements ParserMethodReference, PsiReference {

  public ParserMethodReferenceImpl(ASTNode astNode) {
    super(astNode);
  }

  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    final ASTNode nameElement = ParserChangeUtil.createNameIdentifier(getProject(), name);
    getNode().replaceChild(getNode().findChildByType(ParserTokenTypes.IDENTIFIER), nameElement);
    return this;
  }

  public String getName() {
    return getNode().getText();
  }

  public PsiElement getElement() {
    return this;
  }

  public PsiReference getReference() {
    return this;
  }

  public String toString() {
    return "ParserMethodReference [" + getName() + "]";
  }

  public TextRange getRangeInElement() {
    final PsiElement firstChild = getFirstChild();
    final int startOffsetInParent = firstChild.getStartOffsetInParent();
    return new TextRange(startOffsetInParent, startOffsetInParent + getNode().getTextLength());
  }

  @Nullable
  public PsiElement resolve() {
    HasMethods element = getMethodContainer();

    if (element == null) {
      return null;
    }

    PsiElement[] methods = element.getMethods();

    for (PsiElement method : methods) {
      if (((ParserMethod) method).getName().equals(getName())) {
        return method;
      }
    }
    return null;
  }

  private HasMethods getMethodContainer() {
    PsiElement context = getContext();
    if (!(context instanceof ParserCallingReference)) {
      return null;
    }
    ParserCallingReference callingReference = (ParserCallingReference) context;
    ParserClassReference referenceClass = callingReference.getReferenceClass();
    if (referenceClass == null) {
      if (callingReference.getReferenceObjects().length == 0) {
        try {
          PsiFile file = getContainingFile();
          ParserClass parserClass = PsiTreeUtil.getChildOfType(file, ParserClass.class);
          if (parserClass != null) {
            return parserClass;
          }
          return (HasMethods) file;
        } catch(PsiInvalidElementAccessException ignored) {
        }
      }

      final ParserObjectReference[] objects = callingReference.getReferenceObjects();
      final ParserObjectReferenceImpl object = (ParserObjectReferenceImpl) objects[objects.length - 1];
      final PsiElement element = object.resolve();
      if (element instanceof IsTyped) {
        if (element != null && ((IsTyped) element).getType() != null) {
          return ((IsTyped) element).getType();
        } else {
          return null;
        }
      } else
        return null;
    }
    PsiElement parserClass = referenceClass.getReference().resolve();
    if (parserClass == null)
      return null;
    return (HasMethods) parserClass;
  }

  @NotNull
  public String getCanonicalText() {
    String name = getName();
    return name != null ? name : "";
  }

  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    return setName(newElementName);
  }

  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
    return null;
  }

  public boolean isReferenceTo(PsiElement element) {
    return (element == resolve());
  }

  @NotNull
  public Object[] getVariants() {
    HasMethods methodContainer = getMethodContainer();
    if (methodContainer == null)
      return new Object[0];

    ParserMethod[] methods = methodContainer.getMethods();
    PsiElement parent = getParent();
    List<PsiElement> list = new ArrayList<PsiElement>();
    if (parent instanceof ParserCallingReference) {
      ParserCallingReference callingReference = (ParserCallingReference) parent;
      if (callingReference.isConstructorInvoked()) {
        List<PsiElement> possibleConstructors = new ArrayList<PsiElement>();
        for (ParserMethod method : methods) {
          if (method.isConstructor()) {
            possibleConstructors.add(method);
          }
        }
        return ParserLookupUtil.createSmartLookupItems(possibleConstructors);
      } else if(callingReference.isStaticListInvoked()) {
        for (ParserMethod parserMethod : methods) {
          if (ParserLanguageConstants.AUTO_METHOD_NAME.equals(parserMethod.getName()) ||
              ParserLanguageConstants.CONF_METHOD_NAME.equals(parserMethod.getName()) ||
              (parserMethod instanceof ParserStaticMethodImpl)) {
            List<ParserObject> objects = ParserResolveUtil.collectObjectDeclarationsInElement(parserMethod);
            for (ParserObject object : objects) {
              list.add(object);
            }
          }
        }
        //return ParserLookupUtil.createSmartLookupItems(list);
      }
    }

    for(ParserMethod method : methods) {
      if(!method.isConstructor()) {
        list.add(method);
      }
    }

    return ParserLookupUtil.createSmartLookupItems(list);
  }

  public boolean isSoft() {
    return false;
  }
}

