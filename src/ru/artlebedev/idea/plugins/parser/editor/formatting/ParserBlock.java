package ru.artlebedev.idea.plugins.parser.editor.formatting;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.Wrap;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;

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

public class ParserBlock implements Block {
  private ASTNode myNode;

  private final CodeStyleSettings mySettings;

  private Alignment myAlignment;
  private Indent myIndent;
  private Wrap myWrap;
  private List<Block> mySubBlocks = null;

  public ParserBlock(final ASTNode node, final Alignment alignment, final Indent indent, final Wrap wrap, final CodeStyleSettings settings) {
    myAlignment = alignment;
    myIndent = indent;
    myNode = node;
    myWrap = wrap;
    mySettings = settings;
  }

  public ASTNode getNode() {
    return myNode;
  }

  @NotNull
  public TextRange getTextRange() {
    return myNode.getTextRange();
  }

  @NotNull
  public List<Block> getSubBlocks() {
    if (mySubBlocks == null) {
      SubBlockVisitor visitor = new SubBlockVisitor(getSettings());
//			visitor.visit(myNode);
      mySubBlocks = visitor.getBlocks();
    }
    return mySubBlocks;
  }

  @Nullable
  public Wrap getWrap() {
//		return myWrap;
    return null;
  }

  @Nullable
  public Indent getIndent() {
    return myIndent;
  }

  @Nullable
  public Alignment getAlignment() {
    return null;
  }

  @Nullable
  public Spacing getSpacing(Block child1, Block child2) {
//		return new ParserSpacingProcessor(getNode(), ((ParserBlock) child1).getNode(), ((ParserBlock) child2).getNode(), mySettings).getResult();
    return null;
  }

  @NotNull
  public ChildAttributes getChildAttributes(final int newChildIndex) {
    Indent indent = null;
    if (myNode.getElementType() == ParserElementTypes.CALLING_REFERENCE && myNode.getText().contains("{")) {
      indent = Indent.getNormalIndent();
    } else {
      indent = Indent.getNoneIndent();
    }

    return new ChildAttributes(indent, null);
  }

  public boolean isIncomplete() {
//		return isIncomplete(myNode);
    return false;
  }

  private boolean isIncomplete(ASTNode node) {
    ASTNode lastChild = node.getLastChildNode();
    while (lastChild != null && lastChild.getPsi() instanceof PsiWhiteSpace) {
      lastChild = lastChild.getTreePrev();
    }
    if (lastChild == null) return false;
    if (lastChild.getPsi() instanceof PsiErrorElement) return true;
    return isIncomplete(lastChild);
  }

  public CodeStyleSettings getSettings() {
    return mySettings;
  }

  public boolean isLeaf() {
//		return myNode.getFirstChildNode() == null;
    return false;
  }
}
