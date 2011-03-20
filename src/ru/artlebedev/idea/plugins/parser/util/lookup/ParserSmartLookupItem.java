package ru.artlebedev.idea.plugins.parser.util.lookup;

import com.intellij.codeInsight.lookup.DeferredUserLookupValue;
import com.intellij.codeInsight.lookup.LookupItem;
import com.intellij.codeInsight.lookup.LookupValueWithPriority;
import com.intellij.codeInsight.lookup.LookupValueWithPsiElement;
import com.intellij.codeInsight.lookup.LookupValueWithUIHint;
import com.intellij.codeInsight.lookup.PresentableLookupValue;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.ui.RowIcon;
import ru.artlebedev.idea.plugins.parser.psi.IsTyped;
import ru.artlebedev.idea.plugins.parser.psi.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.ParserMethod;
import ru.artlebedev.idea.plugins.parser.util.ParserIcons;

import javax.swing.*;
import java.awt.*;

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

public class ParserSmartLookupItem implements PresentableLookupValue, LookupValueWithPriority, DeferredUserLookupValue, LookupValueWithUIHint, LookupValueWithPsiElement, Iconable {
  private PsiNamedElement element;
  private Icon icon;

  public ParserSmartLookupItem(PsiNamedElement element) {
    super();
    this.element = element;
  }

  public String getPresentation() {
    return element.getName() == null ? "null" : element.getName();
  }

  public int getPriority() {
    return NORMAL;
  }

  public boolean handleUserSelection(LookupItem lookupItem, Project project) {
    return true;
  }

  public String getTypeHint() {

    if (element instanceof ParserMethod) {
      ParserMethod method = (ParserMethod) element;
      ParserClass returnValueType = method.getReturnValueType();
      if (returnValueType != null)
        return returnValueType.getName();
    }
    if (element instanceof IsTyped) {
      ParserClass aClass = ((IsTyped) element).getType();
      if (aClass != null)
        return aClass.getName();
    }
    if (element instanceof ParserClass) {
      return element.getContainingFile().getName();
    }

    return null;
  }

  public Color getColorHint() {
    return null;
  }

  public boolean isBold() {
//		if (!element.getContainingFile().isPhysical())
//			return true;
    return false;
  }

  public PsiElement getElement() {
    return element;
  }

  public Icon getIcon(int flags) {
    if (icon == null) {
      RowIcon i = new RowIcon(2);
      i.setIcon(element.getIcon(flags), 0);
      i.setIcon(ParserIcons.EMPTY, 1);
      icon = i;
    }
    return icon;
  }
}

