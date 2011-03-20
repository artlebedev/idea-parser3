package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.Icons;
import ru.artlebedev.idea.plugins.parser.ParserLoader;

import javax.swing.*;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 20.03.11
 * Time: 14:49
 */
public class ParserStaticMethodImpl extends ParserMethodImpl {
  public static final Icon STATIC_ICON = IconLoader.getIcon("/nodes/static.png");

  public ParserStaticMethodImpl(ASTNode astNode) {
    super(astNode);
  }

  public Icon getIcon(int flags) {
    return STATIC_ICON;
  }

  public Icon getIcon() {
    return STATIC_ICON;
  }
}
