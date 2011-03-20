package ru.artlebedev.idea.plugins.parser.findUsages;

import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.indexer.ParserWordsScanner;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethodReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObjectReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserParameter;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.psi.impl.ParserObjectReferenceImpl;

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

public class ParserFindUsagesProvider implements FindUsagesProvider, ParserTokenTypes {

  public WordsScanner getWordsScanner() {
    return new ParserWordsScanner();
  }

  public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
    return psiElement instanceof PsiNamedElement;
  }

  public String getHelpId(@NotNull PsiElement psiElement) {
    return null;
  }

  @NotNull
  public String getType(PsiElement element) {
//		System.out.println("get type for " + element);
    if (element instanceof ParserClass || element instanceof ParserClassReference) {
      return "class";
    }
    if (element instanceof ParserMethod || element instanceof ParserMethodReference) {
      return "method";
    }
    if (element instanceof ParserObject) {
      ParserObject object = (ParserObject) element;
      ParserClass type = object.getType();
      if (type != null && type.getName() != null) {
        return type.getName().toLowerCase();
      } else {
        return "variable";
      }
    }
    if (element instanceof ParserObjectReference) {
      PsiElement objectElement = ((ParserObjectReferenceImpl) element).resolve();
      if (objectElement instanceof ParserObject) {
        ParserObject object = (ParserObject) objectElement;
        if (object != null) {
          ParserClass type = object.getType();
          if (type != null && type.getName() != null) {
            return type.getName();
          } else {
            return "variable";
          }
        }
        return "undefined";
      }
      if (objectElement instanceof ParserParameter) {
        return "parameter";
      }

    }
    if (element instanceof ParserParameter) {
      return "parameter";
    }
    return "";
  }

  @NotNull
  public String getDescriptiveName(@NotNull PsiElement element) {
    if (!canFindUsagesFor(element)) {
      return "";
    }

    String name = ((PsiNamedElement) element).getName();
    return name != null ? name : "";
  }

  @NotNull
  public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
    return getDescriptiveName(element);
  }
}