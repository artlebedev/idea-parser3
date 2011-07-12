package ru.artlebedev.idea.plugins.parser.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import ru.artlebedev.idea.plugins.parser.editor.ui.ParserDocumentationForm;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;

import java.util.ArrayList;
import java.util.List;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2011 ArtLebedev Studio
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

public class ShowParserDocumentation extends AnAction {
  final static ParserDocumentationForm documentationForm = new ParserDocumentationForm();
  final private static List<String> standardClasses = new ArrayList<String>();

  static {
    /*
     * Probably we should have a loader for this stuff
     * and stuff located at ParserStandardClasses.java?
     * -- dwr
     */

    standardClasses.add("bool");

    standardClasses.add("console");

    standardClasses.add("cookie");

    standardClasses.add("curl");

    standardClasses.add("date");

    standardClasses.add("env");

    standardClasses.add("file");

    standardClasses.add("form");

    standardClasses.add("hash");

    standardClasses.add("hashfile");

    standardClasses.add("image");

    standardClasses.add("inet");

    standardClasses.add("int");

    standardClasses.add("json");

    standardClasses.add("junction");

    standardClasses.add("mail");

    standardClasses.add("math");

    standardClasses.add("memory");

    standardClasses.add("reflection");

    standardClasses.add("regex");

    standardClasses.add("request");

    standardClasses.add("response");

    standardClasses.add("status");

    standardClasses.add("string");

    standardClasses.add("table");

    standardClasses.add("void");

    standardClasses.add("xdoc");

    standardClasses.add("xnode");
  }

  public void actionPerformed(AnActionEvent e) {
    final Editor editor = (Editor)e.getDataContext().getData("editor");
    if (editor == null) {
      return;
    }
    final Project project = (Project)e.getDataContext().getData("project");
    if (project == null) {
      return;
    }
    final VirtualFile virtualFile = (VirtualFile)e.getDataContext().getData("virtualFile");
    if (virtualFile == null) {
      return;
    }
    final ParserFile psiFile = (ParserFile) PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
    if(psiFile == null) {
      return;
    }
    final PsiElement psiElement = psiFile.findElementAt(editor.getCaretModel().getOffset());
    if(psiElement == null) {
      return;
    }

    String nodeText = psiElement.getNode().getText().toLowerCase();

    if(psiElement.getNode().getElementType().equals(ParserTokenTypes.IDENTIFIER) && standardClasses.contains(nodeText)) {
      if(nodeText.equals("int") || nodeText.equals("double")) {
        documentationForm.setPage("intdoubleclasses");
      } else {
        documentationForm.setPage(nodeText + "class");
      }
      documentationForm.setVisible(true);
    }
  }
}
