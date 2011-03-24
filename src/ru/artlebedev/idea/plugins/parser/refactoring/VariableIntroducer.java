package ru.artlebedev.idea.plugins.parser.refactoring;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserPassedParameter;
import ru.artlebedev.idea.plugins.parser.utils.ParserChangeUtil;

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

public class VariableIntroducer implements Runnable {

  ParserCallingReference reference;
  Project myProject;
  String varName;

  public VariableIntroducer(ParserCallingReference reference, Project myProject, String varName) {
    this.reference = reference;
    this.myProject = myProject;
    this.varName = varName;

  }

  public void run() {
    List<PsiElement> generated = new ArrayList<PsiElement>();
    try {
      generated = ParserChangeUtil.createExpressionFromText(myProject, "$a[b]");
    } catch (IncorrectOperationException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

    if (generated.size() == 1) {
      ParserObject parserObject = (ParserObject) generated.get(0);
      try {
        reference.getParent().getNode().addChild(parserObject.getNode(), reference.getNode());

        parserObject.setName(varName);
        ParserPassedParameter value = parserObject.getValue();

        value.getNode().addChild(reference.getNode());
        value.getNode().removeChild(value.getNode().getFirstChildNode());


      } catch (IncorrectOperationException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }

    }

  }
}
