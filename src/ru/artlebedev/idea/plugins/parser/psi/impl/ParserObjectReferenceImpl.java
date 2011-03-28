package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserHashKey;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObjectReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserParameter;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.psi.lookup.ParserLookupUtil;
import ru.artlebedev.idea.plugins.parser.psi.resolve.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.utils.ParserChangeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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

public class ParserObjectReferenceImpl extends ParserElementImpl implements ParserObjectReference, PsiReference {
  public ParserObjectReferenceImpl(ASTNode astNode) {
    super(astNode);
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
    ParserObjectReference precedingObjectReference = PsiTreeUtil.getPrevSiblingOfType(this, ParserObjectReference.class);
    if (precedingObjectReference != null) {
      ParserObjectReferenceImpl objectReference = (ParserObjectReferenceImpl) precedingObjectReference;
      PsiElement psiElement = objectReference.resolve();
      if (psiElement instanceof ParserObject) {
        ParserObject object = (ParserObject) psiElement;
        ParserPassedParameter value = object.getValue();
        boolean hasNotHashKeyChildren = false;
        PsiElement[] children = value.getChildren();
        for (PsiElement child : children) {
          if (!(child instanceof ParserHashKey)) {
            if (!"".equals(child.getText().trim())) {
              hasNotHashKeyChildren = true;
            }
          }
        }
        if (!hasNotHashKeyChildren) {
          for (PsiElement child : children) {
            if (child instanceof ParserHashKey) {
              if (((ParserHashKey) child).getName().equals(getName())) {
                return child;
              }
            }
          }
        }
      }
      return null;
    }

    ParserClassReference precedingClassReference = PsiTreeUtil.getPrevSiblingOfType(this, ParserClassReference.class);
    if (precedingClassReference != null) {
      ParserClassReferenceImpl classReference = (ParserClassReferenceImpl) precedingClassReference;
      PsiElement parserClassElement = classReference.resolve();
      if (parserClassElement != null) {
        ParserClass parserClass = (ParserClass) parserClassElement;
        ParserMethod[] parserMethods = parserClass.getMethods();
        for (ParserMethod parserMethod : parserMethods) {
          if (ParserLanguageConstants.AUTO_METHOD_NAME.equals(parserMethod.getName())) {
            PsiElement[] psiElements = ParserResolveUtil.collectObjectDeclarationsInElement(parserMethod).toArray(new PsiElement[0]);
            for (PsiElement element : psiElements) {
              if (element instanceof ParserObject) {
                if (((ParserObject) element).getName().equals(getName())) {
                  return element;
                }
              }
            }
          }
        }
      }
      return null;
    }
    List<PsiElement> list = ParserResolveUtil.collectObjectDeclarations(this);
    for (PsiElement psiElement : list) {
      if (psiElement instanceof ParserObject) {
        String name = ((ParserObject) psiElement).getName();
        if (name != null && name.equals(getName())) {
          return psiElement;
        }
      }
    }
    list = ParserResolveUtil.collectParameters(this);
    for (PsiElement psiElement : list) {
      if (psiElement instanceof ParserParameter) {
        String name = ((ParserParameter) psiElement).getName();
        if (name != null && name.equals(getName())) {
          return psiElement;
        }
      }
    }
    //todo add auto method variables

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
    if (!(element instanceof ParserObject) && !(element instanceof ParserParameter)) {
      return false;
    }
    return element == resolve();
  }

  public Object[] getVariants() {
    final PsiElement parent = getParent();
    if (!(parent instanceof ParserCallingReferenceImpl)) {
      return new Object[0];
    }
    ParserCallingReferenceImpl reference = (ParserCallingReferenceImpl) parent;

    //List<PsiElement> result = new ArrayList<PsiElement>();
    HashSet<PsiElement> result = new HashSet<PsiElement>();
    if (reference.getReferenceClass() == null && reference.getReferenceObjects().length == 1) {
      Collection<ParserFile> parserFiles = getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();
      result.addAll(ParserResolveUtil.getClassesFromFiles(parserFiles));
      //todo add auto method variables
    }

    if (reference.getReferenceClass() != null && reference.getReferenceObjects().length == 1) {
      ParserClassReferenceImpl classReference = (ParserClassReferenceImpl) reference.getReferenceClass();
      PsiElement parserClassElement = classReference.resolve();
      if (parserClassElement != null) {
        ParserClass parserClass = (ParserClass) parserClassElement;
        ParserMethod[] parserMethods = parserClass.getMethods();
        for (ParserMethod parserMethod : parserMethods) {
          if (ParserLanguageConstants.AUTO_METHOD_NAME.equals(parserMethod.getName())) {
            List<PsiElement> list = new ArrayList<PsiElement>();
            List<ParserObject> objects = ParserResolveUtil.collectObjectDeclarationsInElement(parserMethod);
            for (ParserObject object : objects) {
              list.add(object);
            }
            return ParserLookupUtil.createSmartLookupItems(list);
          }
        }
      }
      return new Object[0];
    }

    if (reference.getReferenceObjects().length > 1) {
      ParserObjectReference[] parserObjectReferences = reference.getReferenceObjects();
      ParserObjectReference parserObjectReference = parserObjectReferences[parserObjectReferences.length - 2];
      PsiElement resolved = ((ParserObjectReferenceImpl) parserObjectReference).resolve();
      if (resolved != null && resolved instanceof ParserObject) {
        ParserObject parserObject = (ParserObject) resolved;
        ParserPassedParameter value = parserObject.getValue();
        boolean hasNotHashKeyChildren = false;
        for (PsiElement child : value.getChildren()) {
          if (!(child instanceof ParserHashKey)) {
            if (!"".equals(child.getText().trim())) {
              hasNotHashKeyChildren = true;
            }
          }
        }
        if (!hasNotHashKeyChildren) {
          result.addAll(Arrays.asList(value.getChildren()));
        }
      }
    } else {
      result.addAll(ParserResolveUtil.collectParameters(this));

      if(getParent() != null) {
        if(getParent().getParent() != null) {
          if(getParent().getParent().getParent() != null) {
            ParserClass parserClass = (ParserClass) getParent().getParent().getParent();
            for(PsiElement method : parserClass.getChildren()) {
              if(method instanceof ParserMethod) {
                for(PsiElement methodChild : method.getChildren()) {
                  result.addAll(ParserResolveUtil.collectObjectDeclarations(methodChild));
                }
              }
            }
          }
        }
      }
      //result.addAll(ParserResolveUtil.collectObjectDeclarations(this));
    }

//		return result.toArray(new PsiElement[0]);
    List<PsiElement> resultList = new ArrayList<PsiElement>();
    resultList.addAll(result);
    return ParserLookupUtil.createSmartLookupItems(resultList);
  }

  public boolean isSoft() {
    return false;
  }

  public String toString() {
    return "ParserObjectReference [" + getName() + "]";
  }
}
