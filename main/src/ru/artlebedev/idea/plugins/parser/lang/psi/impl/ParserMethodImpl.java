package ru.artlebedev.idea.plugins.parser.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.ParserIcons;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;
import ru.artlebedev.idea.plugins.parser.lang.ParserLoader;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserElementVisitor;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserDoc;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserDocConstructorInfo;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserDocDynamicInfo;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserDocParameterInfo;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserDocResultInfo;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserParameterList;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserStaticClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.resolve.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.lang.stdlib.ParserStandardClasses;
import ru.artlebedev.idea.plugins.parser.util.ParserChangeUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006 <a href="mailto:a4blank@yahoo.com">Jay Bird</a>
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

  public boolean isConstructor() {
    if(ParserLoader.getInstance().getConstructorNames().contains(getName())) {
      return true;
    } else {
      ParserDoc doc = PsiTreeUtil.getPrevSiblingOfType(this, ParserDoc.class);
      while (doc != null) {
        ParserDocConstructorInfo info = PsiTreeUtil.getChildOfType(doc, ParserDocConstructorInfo.class);
        if (info != null) {
          ParserMethod method = PsiTreeUtil.getNextSiblingOfType(doc, ParserMethod.class);

          return method.getName().equals(getName());
        }
        doc = PsiTreeUtil.getPrevSiblingOfType(doc, ParserDoc.class);
      }
    }

    return false;
  }

  public boolean isDynamic() {
    if(ParserLoader.getInstance().getConstructorNames().contains(getName())) {
      return true;
    } else {
      ParserDoc doc = PsiTreeUtil.getPrevSiblingOfType(this, ParserDoc.class);
      while (doc != null) {
        ParserDocDynamicInfo info = PsiTreeUtil.getChildOfType(doc, ParserDocDynamicInfo.class);
        if (info != null) {
          ParserMethod method = PsiTreeUtil.getNextSiblingOfType(doc, ParserMethod.class);

          return method.getName().equals(getName());
        }
        doc = PsiTreeUtil.getPrevSiblingOfType(doc, ParserDoc.class);
      }
    }

    return false;
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
    if(isConstructor()) {
      return ParserIcons.PARSER_CLASS_INITIALIZER_ICON;
    } else {
      if((getParent() != null) && (getParent() instanceof ParserStaticClass)) {
        return ParserIcons.PARSER_STATIC_METHOD_ICON;
      } else {
        return ParserIcons.PARSER_METHOD_ICON;
      }
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
    ParserDoc doc = PsiTreeUtil.getPrevSiblingOfType(this, ParserDoc.class);
    while (doc != null) {
      ParserDocResultInfo info = PsiTreeUtil.getChildOfType(doc, ParserDocResultInfo.class);
      if (info != null) {
        ParserMethod method = PsiTreeUtil.getNextSiblingOfType(doc, ParserMethod.class);

        if(method != null) {
          if(method.getName() != null) {
            if(method.getName().equals(getName())) {
              Collection<ParserFile> parserFiles = getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();
              Collection<ParserClass> parserClasses = ParserResolveUtil.getClassesFromFiles(parserFiles);
              for(ParserClass parserClass : parserClasses) {
                if(parserClass != null) {
                  if(info.getName().equals(parserClass.getName())) {
                    return parserClass;
                  }
                }
              }
            }
          }
        }
      }
      doc = PsiTreeUtil.getPrevSiblingOfType(doc, ParserDoc.class);
    }

    ParserObject resultObject = getResultObject();

    /*
     * Constructor always returns object,
     * so we don't need any return type for it
     * in autocompletition
     * -- dwr
     */
    if(isConstructor()) {
      return null;
    }

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
    // return null
    return ParserStandardClasses.STRING;
  }

  public String getParserDoc() {
    ASTNode treePrev = getNode().getTreePrev();
    if (treePrev != null && treePrev.getElementType() == ParserTokenTypes.WHITE_SPACE) {
      treePrev = treePrev.getTreePrev();
    }
    if (treePrev == null)
      return null;

    if (treePrev.getElementType() == ParserTokenTypes.SHARP_COMMENT) {
      List<String> buf = new ArrayList<String>();
      while (treePrev != null && treePrev.getElementType() == ParserTokenTypes.SHARP_COMMENT) {
        buf.add(treePrev.getText().replaceFirst("^#+", "") + "<br>");
        treePrev = treePrev.getTreePrev();
        if (treePrev != null && treePrev.getElementType() == ParserTokenTypes.WHITE_SPACE) {
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