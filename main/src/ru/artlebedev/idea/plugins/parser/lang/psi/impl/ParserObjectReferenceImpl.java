package ru.artlebedev.idea.plugins.parser.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserPsiUtil;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserHashKey;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObjectReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserParameter;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserStrictClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserStrictDynamicClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.lookup.ParserLookupUtil;
import ru.artlebedev.idea.plugins.parser.lang.psi.resolve.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.util.ParserChangeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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

public class ParserObjectReferenceImpl extends ParserElementImpl implements ParserObjectReference, PsiReference {
  private static final Logger LOG = Logger.getInstance("#ParserObjectReferenceImpl");

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

  public PsiElement resolveBasic() {
    boolean isInAuto = ParserPsiUtil.isInAutoMethod(this);

    ParserObjectReference precedingObjectReference = PsiTreeUtil.getPrevSiblingOfType(this, ParserObjectReference.class);
    if (precedingObjectReference != null) {
      ParserObjectReferenceImpl objectReference = (ParserObjectReferenceImpl) precedingObjectReference;
      PsiElement psiElement = objectReference.resolveBasic();
      if (psiElement instanceof ParserObject) {
        ParserObject object = (ParserObject) psiElement;
        ParserPassedParameter value = object.getValue();
        if(value != null) {
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

          ParserClass parserClass = ((ParserObject) psiElement).getType();

          if(parserClass != null) {
            HashSet<PsiElement> hs = new HashSet<PsiElement>();

            if((parserClass instanceof ParserStrictClass) || (parserClass instanceof ParserStrictDynamicClass)) {
              for(PsiElement method : parserClass.getChildren()) {
                if(method instanceof ParserMethod) {
                  for(PsiElement methodChild : method.getChildren()) {
                    hs.addAll(ParserResolveUtil.collectGlobalObjectDeclarations(methodChild));
                  }
                }
              }
            } else {
              for(PsiElement method : parserClass.getChildren()) {
                if(method instanceof ParserMethod) {
                  for(PsiElement methodChild : method.getChildren()) {
                    hs.addAll(ParserResolveUtil.collectObjectDeclarations(methodChild));
                  }
                }
              }
            }

            for (PsiElement element : hs) {
              if (element instanceof ParserObject) {
                if (((ParserObject) element).getName().equals(getName())) {
                  return element;
                }
              }
            }
          }
        }
      }
      if(!precedingObjectReference.getName().equals(ParserLanguageConstants.SELF_CLASS_NAME) || isInAuto)
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
          //if (ParserLanguageConstants.AUTO_METHOD_NAME.equals(parserMethod.getName())) {
            PsiElement[] psiElements = ParserResolveUtil.collectObjectDeclarationsInElement(parserMethod).toArray(new PsiElement[0]);
            for (PsiElement element : psiElements) {
              if (element instanceof ParserObject) {
                if (((ParserObject) element).getName().equals(getName())) {
                  return element;
                }
              }
            }
          //}
        }
      }
      return null;
    }

    List<PsiElement> list = ParserResolveUtil.collectObjectDeclarations(this);
    PsiElement parentElement = getParent();
    while(!(parentElement instanceof ParserClass) && !(parentElement == null)) {
      parentElement = parentElement.getParent();
    }

    if(parentElement != null) {
      ParserClass parserClass = (ParserClass) parentElement;

      if((parserClass instanceof ParserStrictClass) || (parserClass instanceof ParserStrictDynamicClass)) {
        for(PsiElement method : parserClass.getChildren()) {
          if(method instanceof ParserMethod) {
            for(PsiElement methodChild : method.getChildren()) {
              list.addAll(ParserResolveUtil.collectGlobalObjectDeclarations(methodChild));
            }
          }
        }

        list.addAll(ParserResolveUtil.collectObjectDeclarations(this));
      } else {
        for(PsiElement method : parserClass.getChildren()) {
          if(method instanceof ParserMethod) {
            for(PsiElement methodChild : method.getChildren()) {
              list.addAll(ParserResolveUtil.collectObjectDeclarations(methodChild));
            }
          }
        }
      }
    }

    list.addAll(ParserResolveUtil.collectObjectDeclarations(this));

    ParserMethod currentMethod = PsiTreeUtil.getParentOfType(this, ParserMethod.class);

    if(currentMethod != null) {
      Iterator<PsiElement> iterator = list.iterator();

      while(iterator.hasNext()) {
        PsiElement element = iterator.next();
        if(element instanceof PsiNamedElement) {
          try {
            if (((PsiNamedElement) element).getName().equals("result")) {
              if (!currentMethod.equals(PsiTreeUtil.getParentOfType(element, ParserMethod.class))) {
                iterator.remove();
              }
            }
          } catch (Exception ignored) {
          }
        }
      }
    }

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

  @Nullable
  public PsiElement resolve() {
    PsiElement resolveResult = resolveBasic();
    if((resolveResult == null)) {
      if(ParserLanguageConstants.SELF_CLASS_NAME.equals(getName())) {
        final ParserClass parserClass = PsiTreeUtil.getParentOfType(this, ParserClass.class);

        return new ParserObjectImpl((ASTNode) getNode().clone()) {
          public String getName() {
            return "self";
          }

          public ASTNode findNameNode() {
            return null;
          }

          public ParserClass getType() {
            return parserClass;
          }
        };
      }

      return null;
    } else {
      return resolveResult;
    }
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
    if (!(element instanceof ParserObject) && !(element instanceof ParserParameter)) {
      return false;
    }
    return element == resolve();
  }

  @NotNull
  public Object[] getVariants() {
    boolean isInAuto = ParserPsiUtil.isInAutoMethod(this);

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
        List<PsiElement> list = new ArrayList<PsiElement>();

        ParserClass parserClass = (ParserClass) parserClassElement;
        ParserMethod[] parserMethods = parserClass.getMethods();
        for (ParserMethod parserMethod : parserMethods) {
          if (ParserLanguageConstants.AUTO_METHOD_NAME.equals(parserMethod.getName()) ||
              ParserLanguageConstants.CONF_METHOD_NAME.equals(parserMethod.getName()) ||
              (parserMethod instanceof ParserStaticMethodImpl)) {
            List<ParserObject> objects = ParserResolveUtil.collectObjectDeclarationsInElement(parserMethod);
            for (ParserObject object : objects) {
              list.add(object);
            }
          }
        }

        return ParserLookupUtil.createSmartLookupItems(list);
      }
      return new Object[0];
    }

    if ((reference.getReferenceObjects().length > 1) &&
            !(((ParserCallingReference) getParent()).getReferenceObjects()[0].getName().equals(ParserLanguageConstants.SELF_CLASS_NAME) && !isInAuto &&
              (((ParserCallingReference) getParent()).getReferenceObjects().length == 2))) {
      ParserObjectReference[] parserObjectReferences = reference.getReferenceObjects();
      ParserObjectReference parserObjectReference = parserObjectReferences[parserObjectReferences.length - 2];
      PsiElement resolved = ((ParserObjectReferenceImpl) parserObjectReference).resolveBasic();
      if (resolved != null && resolved instanceof ParserObject) {
        ParserObject parserObject = (ParserObject) resolved;
        ParserPassedParameter value = parserObject.getValue();
        if(value != null) {
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

          ParserClass parserClass = ((ParserObject) resolved).getType();

          if(parserClass != null) {
            if((parserClass instanceof ParserStrictClass) || (parserClass instanceof ParserStrictDynamicClass)) {
              for(PsiElement method : parserClass.getChildren()) {
                if(method instanceof ParserMethod) {
                  for(PsiElement methodChild : method.getChildren()) {
                    result.addAll(ParserResolveUtil.collectGlobalObjectDeclarations(methodChild));
                  }
                }
              }
            } else {
              for(PsiElement method : parserClass.getChildren()) {
                if(method instanceof ParserMethod) {
                  for(PsiElement methodChild : method.getChildren()) {
                    result.addAll(ParserResolveUtil.collectObjectDeclarations(methodChild));
                  }
                }
              }
            }

            Iterator<PsiElement> iterator = result.iterator();
            while(iterator.hasNext()) {
              PsiElement element = iterator.next();
              if(element instanceof PsiNamedElement) {
                if("result".equals(((PsiNamedElement) element).getName()))
                  iterator.remove();
              }
            }
          }
        }
      }
    } else {
      result.addAll(ParserResolveUtil.collectParameters(this));

      PsiElement parentElement = getParent();
      while(!(parentElement instanceof ParserClass) && !(parentElement == null)) {
        parentElement = parentElement.getParent();
      }

      if(parentElement != null) {
        ParserClass parserClass = (ParserClass) parentElement;

        if((parserClass instanceof ParserStrictClass) || (parserClass instanceof ParserStrictDynamicClass)) {
          for(PsiElement method : parserClass.getChildren()) {
            if(method instanceof ParserMethod) {
              for(PsiElement methodChild : method.getChildren()) {
                result.addAll(ParserResolveUtil.collectGlobalObjectDeclarations(methodChild));
              }
            }
          }
        } else {
          for(PsiElement method : parserClass.getChildren()) {
            if(method instanceof ParserMethod) {
              for(PsiElement methodChild : method.getChildren()) {
                result.addAll(ParserResolveUtil.collectObjectDeclarations(methodChild));
              }
            }
          }
        }

        ParserMethod currentMethod = PsiTreeUtil.getParentOfType(this, ParserMethod.class);

        if(currentMethod != null) {
          Iterator<PsiElement> iterator = result.iterator();

          while(iterator.hasNext()) {
            PsiElement element = iterator.next();
            if(element instanceof PsiNamedElement) {
              if("result".equals(((PsiNamedElement) element).getName())) {
                if(!currentMethod.equals(PsiTreeUtil.getParentOfType(element, ParserMethod.class))) {
                  iterator.remove();
                }
              }
            }
          }
        }
      }

      if(!(((ParserCallingReference) getParent()).getReferenceObjects()[0].getName().equals(ParserLanguageConstants.SELF_CLASS_NAME) && !isInAuto &&
                    (((ParserCallingReference) getParent()).getReferenceObjects().length == 2))) {
        result.addAll(ParserResolveUtil.collectObjectDeclarations(this));
      }
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
