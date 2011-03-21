package ru.artlebedev.idea.plugins.parser.structure;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.Icons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

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

public class ParserStructureViewElementParent extends ParserStructureViewElement {
  public ParserStructureViewElementParent(PsiElement element) {
    super(element);
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
        //return element.getIcon(0);
        return Icons.ABSTRACT_CLASS_ICON;
      }

      @Nullable
      public TextAttributesKey getTextAttributesKey() {
        return null;
      }
    };
  }
}
