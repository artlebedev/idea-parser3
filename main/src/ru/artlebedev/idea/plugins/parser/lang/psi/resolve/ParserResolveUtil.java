package ru.artlebedev.idea.plugins.parser.lang.psi.resolve;

import com.intellij.openapi.vfs.VirtualFileWithId;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiElementFilter;
import com.intellij.psi.util.PsiTreeUtil;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserPsiUtil;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserParameterList;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserIncludePathImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserMethodReferenceImpl;

import java.util.*;

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

public class ParserResolveUtil {

  public static List<ParserMethodReferenceImpl> getIncludesViaUse(PsiFile parserFile) {
    List<ParserMethodReferenceImpl> result = new ArrayList<ParserMethodReferenceImpl>();

    final PsiElement[] methodElements = PsiTreeUtil.collectElements(parserFile, new PsiElementFilter() {
      public boolean isAccepted(PsiElement element) {
        return element instanceof ParserCallingReference;
      }
    });

    for (PsiElement methodElement : methodElements) {
      ParserCallingReference reference = (ParserCallingReference) methodElement;
      if (reference.getReferenceClass() == null && reference.getReferenceObjects().length == 0) {
        final ParserMethodReferenceImpl method = (ParserMethodReferenceImpl) reference.getReferenceMethod();
        if (method != null && method.getName().equals(ParserLanguageConstants.USE_METHOD_NAME)) {
          result.add(method);
        }
      }
    }

    return result;
  }

  public static List<ParserObject> collectObjectDeclarationsInElement(PsiElement parent) {
    return ParserPsiUtil.collectElementsAsList(parent, ParserObject.class);
  }

  public static List<PsiElement> collectStaticObjectDeclarations(PsiElement element) {
    List<PsiElement> result = new ArrayList<PsiElement>();
    PsiElement currentElement = element;

    while (!(currentElement instanceof ParserFile)) {
      if (currentElement != null) {
        do {
          if (currentElement instanceof ParserObject) {
            ParserMethod parserMethod = PsiTreeUtil.getParentOfType(currentElement, ParserMethod.class);
            if(parserMethod != null) {
              if(ParserLanguageConstants.AUTO_METHOD_NAME.equals(parserMethod.getName())) {
                result.add(currentElement);
              }
              if(ParserLanguageConstants.CONF_METHOD_NAME.equals(parserMethod.getName())) {
                result.add(currentElement);
              }
            }
          }
          if (currentElement.getPrevSibling() != null) {
            currentElement = currentElement.getPrevSibling();
          }
        } while (currentElement.getPrevSibling() != null);

        currentElement = currentElement.getParent();
      }
    }

    return result;

  }

  /**
   * These methods should work in the way they ignore duplication functions
   * inside the whole method (uniques).
   *
   * FIXING MULTI-OBJECT BUG
   * Idea is to collect each object methods/place related to the
   *
   * BTW HOW DOES THIS FUNCTION GET THAT WE NEED THE OBJECT FROM THE CURRENT METHOD??
   *
   * @param element
   * @return
   */

  public static List<PsiElement> collectGlobalObjectDeclarations(PsiElement element) {
    List<PsiElement> result = new ArrayList<PsiElement>();
    PsiElement currentElement = element;

    while (!(currentElement instanceof ParserFile)) {
      do {
        if (currentElement instanceof ParserObject) {
          if(((ParserObject) currentElement).isGlobal()) {
            result.add(currentElement);
          }
        }
        if (currentElement.getPrevSibling() != null) {
          currentElement = currentElement.getPrevSibling();
        }
      } while (currentElement.getPrevSibling() != null);
      currentElement = currentElement.getParent();
    }

    return result;
  }

  public static List<PsiElement> collectObjectDeclarations(PsiElement element) {
    List<PsiElement> result = new ArrayList<PsiElement>();
    PsiElement currentElement = element;

    while (!(currentElement instanceof ParserFile)) {
      if (currentElement != null) {
        do {
          if (currentElement instanceof ParserObject) {
            result.add(currentElement);
          }
          if (currentElement.getPrevSibling() != null) {
            currentElement = currentElement.getPrevSibling();
          }
        } while (currentElement.getPrevSibling() != null);

        currentElement = currentElement.getParent();
      }
    }

//    Iterator<PsiElement> it = result.iterator();
//    while(it.hasNext()) {
//      PsiElement el = it.next();
//      System.out.print(((ParserObject)el).getName());
//    }

    return result;
  }

  public static List<PsiElement> collectClassIncludes(PsiElement element) {
    List<PsiElement> result = new ArrayList<PsiElement>();

    PsiElement[] includes = PsiTreeUtil.collectElements(element, new PsiElementFilter() {
      public boolean isAccepted(PsiElement element) {
        return element instanceof ParserIncludePathImpl;
      }
    });

    for (PsiElement include : includes) {
      ParserIncludePathImpl classIncl = (ParserIncludePathImpl) include;
      PsiElement parserFile = classIncl.resolve();
      if (parserFile != null) {
        ParserClass parserClass = PsiTreeUtil.getChildOfType(parserFile, ParserClass.class);
        if (parserClass != null)
          result.add(parserClass);
      }
    }

    return result;
  }

  public static List<ParserMethod> collectMethods(PsiElement where) {
    return ParserPsiUtil.collectElementsAsList(where, ParserMethod.class);
  }

  public static List<PsiElement> collectParameters(PsiElement element) {
    while (!(element instanceof ParserMethod)) {
      if (element instanceof ParserFile)
        return new ArrayList<PsiElement>();
      element = element.getParent();
    }
    ParserMethod method = (ParserMethod) element;
    ParserParameterList parameterList = method.getParameterList();
    return Arrays.asList(parameterList.getParameters());

  }

  public static Collection<ParserClass> getClassesFromFiles(Collection<ParserFile> parserFiles) {
    Collection<ParserClass> indexedClasses = new ArrayList<ParserClass>();

    for (ParserFile parserFile : parserFiles) {
      if(((parserFile instanceof VirtualFileWithId) && (((VirtualFileWithId) parserFile).getId() > 0)) || !(parserFile instanceof VirtualFileWithId)) {
        if((parserFile.getVirtualFile() != null) && parserFile.getVirtualFile().exists()) {
          ParserClass parserClass = PsiTreeUtil.getChildOfType(parserFile, ParserClass.class);
          if (parserClass != null) {
            indexedClasses.add(parserClass);
          }
        }
      }
    }
    return indexedClasses;
  }
}

