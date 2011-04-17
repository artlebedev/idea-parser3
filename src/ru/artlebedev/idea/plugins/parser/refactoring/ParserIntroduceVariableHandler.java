package ru.artlebedev.idea.plugins.parser.refactoring;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.refactoring.RefactoringActionHandler;
import ru.artlebedev.idea.plugins.parser.actions.WriteActionRunner;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserCallingReference;
import ru.artlebedev.idea.plugins.parser.ParserBundle;

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

public class ParserIntroduceVariableHandler implements RefactoringActionHandler {
  public void invoke(Project project, Editor editor, PsiFile file, DataContext dataContext) {
    if (!editor.getSelectionModel().hasSelection()) {
      editor.getSelectionModel().selectLineAtCaret();
      String selectedText = editor.getSelectionModel().getSelectedText();
      if (selectedText.matches("^\\s+(?s).*")) {
        String newSelection = selectedText.replaceFirst("^\\s+", "");
        int difference = selectedText.length() - newSelection.length();
        editor.getSelectionModel().setSelection(editor.getSelectionModel().getSelectionStart() + difference, editor.getSelectionModel().getSelectionEnd());
      }
    }
    if (refactor(project, editor, file, editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd()))
      editor.getSelectionModel().removeSelection();

  }

  private boolean refactor(final Project project, Editor editor, PsiFile file, int selectionStart, int selectionEnd) {
    final ParserCallingReference reference = PsiTreeUtil.findElementOfClassAtRange(file, selectionStart, selectionEnd, ParserCallingReference.class);
    if (reference == null)
      return false;
    final String varName = showDialog();
    if (varName.equals(""))
      return true;

    PsiManager.getInstance(project).performActionWithFormatterDisabled(new Runnable() {
      public void run() {
        WriteActionRunner war = new WriteActionRunner(new VariableIntroducer(reference, project, varName));
        CommandProcessor.getInstance().executeCommand(project, war, "Introduce variable", null);
      }
    });

    return true;
  }

  private String showDialog() {
    do {
      String s = Messages.showInputDialog(ParserBundle.message("actions.introduceVariable.enterName"), ParserBundle.message("actions.introduceVariable.title"), Messages.getQuestionIcon());
      if (s == null)
        return "";
      if ("".equals(s.trim())) {
        Messages.showMessageDialog(ParserBundle.message("actions.createClass.emptyName"), ParserBundle.message("actions.createClass.emptyNameTitle"), Messages.getErrorIcon());
      } else {
        return s;
      }
    } while (true);
  }

  public void invoke(Project project, PsiElement[] elements, DataContext dataContext) {
  }
}
