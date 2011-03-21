package ru.artlebedev.idea.plugins.parser.structure;

import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.Icons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 21.03.11
 * Time: 15:46
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
