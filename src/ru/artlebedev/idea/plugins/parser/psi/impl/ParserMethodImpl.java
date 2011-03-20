package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Icons;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.ParserLoader;
import ru.artlebedev.idea.plugins.parser.lang.ParserStandardClasses;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.ParserElementVisitor;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserDoc;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserDocParameterInfo;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserParameterList;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.psi.resolve.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.utils.ParserChangeUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

public class ParserMethodImpl extends ParserElementImpl implements ParserMethod {

  private ParserObject resultObject;
  private int hasResult;


  public ParserMethodImpl(ASTNode astNode) {
    super(astNode);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ParserElementVisitor) {
      ((ParserElementVisitor) visitor).visitParserMethod(this);
    } else {
      visitor.visitElement(this);
    }
  }

  public int getTextOffset() {
    final ASTNode name = findNameNode();
    return name != null ? name.getStartOffset() : super.getTextOffset();
  }

  public String getName() {
    ASTNode nameNode = findNameNode();
    if (nameNode != null)
      return nameNode.getText();
    return null;
  }

  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    ASTNode identifier = ParserChangeUtil.createNameIdentifier(getProject(), name);
    ASTNode nameNode = findNameNode();
    if (nameNode != null)
      getNode().replaceChild(nameNode, identifier);
    return this;
  }

  public ASTNode findNameNode() {
    return getNode().findChildByType(ParserTokenTypes.IDENTIFIER);
  }

  public String toString() {
    return "ParserMethod";
  }

  public Icon getIcon(int flags) {
    // XXX should follow ParserDoc etc
    // XXX add support for static (idea has static.png) and parser has @static keyword
    if(ParserLoader.getInstance().getConstructorNames().contains(getName())) {
      return Icons.CLASS_INITIALIZER;
    } else {
      return Icons.METHOD_ICON;
    }
  }

  public Icon getIcon() {
    if(ParserLoader.getInstance().getConstructorNames().contains(getName())) {
      return Icons.CLASS_INITIALIZER;
    } else {
      return Icons.METHOD_ICON;
    }
  }

  public ParserParameterList getParameterList() {
    return PsiTreeUtil.getChildOfAnyType(this, ParserParameterList.class);
  }

  private ParserObject getResultObject() {
    /*
     * fix wrong result of type string
     * -- jay
     *
     * Fixed it by commenting out hasResult != NO_RESULT.
     * Will find the reason it wasn't work (probably psi tree cache?)
     * -- dwr
     */
    if (resultObject == null /*&& hasResult != NO_RESULT*/) {
      List<ParserObject> parserObjects = ParserResolveUtil.collectObjectDeclarationsInElement(this);
      List<ParserObject> resultObjects = new ArrayList<ParserObject>();
      for (ParserObject object : parserObjects) {
        if ("result".equals(object.getName())) {
          resultObjects.add(object);
        }
      }
      hasResult = NO_RESULT;
      if (resultObjects.size() == 1) {
        resultObject = resultObjects.get(0);
        hasResult = ONE_RESULT;
      } else {
        hasResult = MANY_RESULTS;
        resultObject = null;
      }
    }
    return resultObject;
  }

  public ParserPassedParameter getReturnValue() {
    ParserObject resultObject = getResultObject();
    if (resultObject != null) {
      return resultObject.getValue();
    }
    return null;
  }

  public ParserClass getReturnValueType() {
    ParserObject resultObject = getResultObject();
    if (hasResult == NO_RESULT) {
      return ParserStandardClasses.STRING;
    }
    if (resultObject != null) {
      return resultObject.getType();
    }
    /*
     * XXX suppose that all parser methods
     *     are returning string if they are
     *     not belongs to constructors,
     *     which should return object itself
     *     (which is already fixed)
     *     -- dwr
     */
    //return null;
    return ParserStandardClasses.STRING;
  }

  public String getParserDoc() {
    ASTNode treePrev = getNode().getTreePrev();
    if (treePrev != null && treePrev.getElementType() == ParserTokenTypes.NEW_LINE) {
      treePrev = treePrev.getTreePrev();
    }
    if (treePrev == null)
      return null;

    if (treePrev.getElementType() == ParserTokenTypes.SHARP_COMMENT) {
      List<String> buf = new ArrayList<String>();
      while (treePrev != null && treePrev.getElementType() == ParserTokenTypes.SHARP_COMMENT) {
        buf.add(treePrev.getText().replaceFirst("^#+", "") + "<br>");
        treePrev = treePrev.getTreePrev();
        if (treePrev != null && treePrev.getElementType() == ParserTokenTypes.NEW_LINE) {
          treePrev = treePrev.getTreePrev();
        }
      }
      StringBuffer buffer = new StringBuffer();
      for (int i = 1; i < buf.size() + 1; i++) {
        String s = buf.get(buf.size() - i);
        buffer.append(s);
      }
      return buffer.toString();
    }
    return treePrev.getText();
  }

  public ParserDocParameterInfo[] getParameterInfo() {
    List<ParserDocParameterInfo> result = new ArrayList<ParserDocParameterInfo>();

    ParserDoc doc = PsiTreeUtil.getPrevSiblingOfType(this, ParserDoc.class);
    while (doc != null) {
      ParserDocParameterInfo info = PsiTreeUtil.getChildOfType(doc, ParserDocParameterInfo.class);
      if (info != null) {
        result.add(info);
      }
      doc = PsiTreeUtil.getPrevSiblingOfType(doc, ParserDoc.class);
    }

    return result.toArray(new ParserDocParameterInfo[result.size()]);
  }
}