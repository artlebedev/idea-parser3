package ru.artlebedev.idea.plugins.parser.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserPsiUtil;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserDynamicClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserStaticMethod;

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

public class ParserDynamicClassImpl extends ParserClassImpl implements ParserDynamicClass {
  public ParserDynamicClassImpl(ASTNode astNode) {
    super(astNode);
  }

  public String toString() {
    return "ParserDynamicClass";
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
      if(psiElements[i] instanceof ParserStaticMethod)
        continue;

      result[i] = (ParserMethod) psiElements[i];
    }
    for (int i = 0; i < parentMethods.length; i++) {
      if(parentMethods[i] instanceof ParserStaticMethod)
        continue;

      result[psiElements.length + i] = parentMethods[i];
    }
    return result;
  }
}
