package ru.artlebedev.idea.plugins.parser.formatter;

import com.intellij.formatting.Block;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.parser.ParserElementTypes;

import java.util.ArrayList;
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

public class SubBlockVisitor {
  List<Block> myBlocks = new ArrayList<Block>();
  private final CodeStyleSettings mySettings;

  public SubBlockVisitor(CodeStyleSettings settings) {
    mySettings = settings;
  }

  public List<Block> getBlocks() {
    return myBlocks;
  }

  public void visitElement(final ASTNode node) {
    ASTNode child = node.getFirstChildNode();
    while (child != null) {
      if (child.getElementType() != ParserTokenTypes.NEW_LINE_INDENT &&
              child.getElementType() != ParserTokenTypes.WHITE_SPACE &&
              child.getElementType() != ParserTokenTypes.NEW_LINE &&
              child.getTextRange().getLength() > 0) {
        Indent childIndent = getIndent(node, child);
        Wrap wrap = getWrap(node, child);
        myBlocks.add(new ParserBlock(child, null, childIndent, wrap, mySettings));
      }
      child = child.getTreeNext();
    }
  }

  private Wrap getWrap(ASTNode node, ASTNode child) {
    if (child.getElementType() == ParserElementTypes.PASSED_PARAMETER && child.getTreePrev().getText().equals("{")) {
      return Wrap.createWrap(WrapType.ALWAYS, true);
    }
    return null;
  }

  private Indent getIndent(ASTNode node, ASTNode child) {
    if (child.getElementType() == ParserElementTypes.PASSED_PARAMETER && child.getTreePrev().getText().equals("{")) {
      return Indent.getNormalIndent();
    }
    if (node.getElementType() == ParserElementTypes.CALLING_REFERENCE && child.getElementType() == ParserTokenTypes.RBRACE) {
      return Indent.getNormalIndent();
    }
    return Indent.getNoneIndent();
  }
}
