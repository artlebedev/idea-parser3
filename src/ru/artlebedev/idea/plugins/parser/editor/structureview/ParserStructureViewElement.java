package ru.artlebedev.idea.plugins.parser.editor.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserStaticMethod;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
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

public class ParserStructureViewElement implements StructureViewTreeElement {
  protected PsiElement element;

  public ParserStructureViewElement(PsiElement element) {
    this.element = element;
  }

  public Object getValue() {
    return element;
  }

  public ItemPresentation getPresentation() {
    return new ItemPresentation() {

      public String getPresentableText() {
        if (element instanceof PsiNamedElement)
          return ((PsiNamedElement) element).getName();
        return null;
      }

      @Nullable
      public String getLocationString() {
        return null;
      }

      @Nullable
      public Icon getIcon(boolean open) {
        return element.getIcon(0);
      }

      @Nullable
      public TextAttributesKey getTextAttributesKey() {
        return null;
      }
    };
  }

  public StructureViewTreeElement[] getChildren() {
    List<StructureViewTreeElement> elements = new ArrayList<StructureViewTreeElement>();

    if(element instanceof ParserClass) {
      if(((ParserClass) element).getParentClass() != null) {
        elements.add(new ParserStructureViewElementParent(((ParserClass) element).getParentClass()));
      }
    }

    for (PsiElement psiElement : element.getChildren()) {
      for (Class suitableClass : ParserStructureViewModel.suitableClasses) {
        if (suitableClass.isInstance(psiElement)) {
          if(psiElement instanceof ParserMethod) {
            if((psiElement instanceof ParserStaticMethod) ||
                  ParserLanguageConstants.AUTO_METHOD_NAME.equals(((ParserMethod) psiElement).getName())) {
              Collections.addAll(elements, new ParserStructureViewElement(psiElement).getChildren());

              continue;
            }
          }

          elements.add(new ParserStructureViewElement(psiElement));
        }
      }
    }

    return elements.toArray(new StructureViewTreeElement[elements.size()]);
  }

  public void navigate(boolean requestFocus) {
    ((NavigationItem) element).navigate(requestFocus);
  }

  public boolean canNavigate() {
    return ((NavigationItem) element).canNavigate();
  }

  public boolean canNavigateToSource() {
    return ((NavigationItem) element).canNavigateToSource();
  }
}
