package ru.artlebedev.idea.plugins.parser.editor.formatting;

import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.IElementType;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;

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

public class ParserSpacingProcessor {
  private ASTNode myParent;
  private ASTNode myChild1;
  private ASTNode myChild2;
  private final CodeStyleSettings mySettings;
  private Spacing myResult;
  private final IElementType type1;
  private final IElementType type2;

  public Spacing getResult() {
    return myResult;
  }

  public ParserSpacingProcessor(final ASTNode parent, final ASTNode child1, final ASTNode child2, final CodeStyleSettings settings) {
    myParent = parent;
    myChild1 = child1;
    myChild2 = child2;
    mySettings = settings;
    type1 = child1.getElementType();
    type2 = child2.getElementType();
//		visit(parent);
  }

  public void visitFile(ASTNode node) {
    if (type1 == ParserElementTypes.METHOD && type2 == ParserElementTypes.METHOD) {
      myResult = Spacing.createSpacing(0, 0, 3, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    } else if (type1 == ParserElementTypes.INCLUDE && type2 == ParserElementTypes.CLASS) {
      myResult = Spacing.createSpacing(0, 0, 3, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    } else if (type1 == ParserElementTypes.CLASS && type2 == ParserElementTypes.METHOD) {
      myResult = Spacing.createSpacing(0, 0, 3, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    } else if (type1 == ParserElementTypes.INCLUDE && type2 == ParserElementTypes.METHOD) {
      myResult = Spacing.createSpacing(0, 0, 3, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    }
  }

  public void visitMethod(ASTNode node) {
    if (type1 == ParserElementTypes.OBJECT && type2 == ParserElementTypes.OBJECT) {
      myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    }
  }

  public void visitClass(ASTNode node) {
    if (type1 == ParserElementTypes.METHOD && type2 == ParserElementTypes.METHOD) {
      myResult = Spacing.createSpacing(0, 0, 3, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    }
  }

  public void visitCallingReference(ASTNode node) {
    /*if (type1 == ParserElementTypes.PASSED_PARAMETER && myChild1.getTreePrev().getText().equals("{")) {
        myResult = Spacing.createSpacing(1, 1, 0, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
      }*/
    /*if (type2 == ParserElementTypes.PASSED_PARAMETER && myChild2.getTreePrev().getText().equals("{")) {
        myResult = Spacing.createSpacing(1, 1, 0, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
      }*/
  }
}
