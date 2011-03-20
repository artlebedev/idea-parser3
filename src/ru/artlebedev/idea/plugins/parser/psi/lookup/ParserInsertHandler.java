package ru.artlebedev.idea.plugins.parser.psi.lookup;

import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.DefaultInsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupValueWithPsiElement;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.psi.PsiElement;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;

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

public class ParserInsertHandler extends DefaultInsertHandler {
  public void handleInsert(final InsertionContext context, LookupElement item) {
    super.handleInsert(context, item);
    Object o = item.getObject();
    if (o instanceof LookupValueWithPsiElement) {
      PsiElement element = ((LookupValueWithPsiElement) o).getElement();
      if (element instanceof ParserMethod) {
        CaretModel caretModel = context.getEditor().getCaretModel();
        String s = context.getEditor().getDocument().getText().substring(caretModel.getOffset(), caretModel.getOffset() + 1);
        if (!s.equals("[") && !s.equals("{") && !s.equals("(")) {
          context.getEditor().getDocument().insertString(caretModel.getOffset(), "[]");
          caretModel.moveToOffset(caretModel.getOffset() + 2);
          ParserMethod method = (ParserMethod) element;
          if (method.getParameterList().getChildren().length > 0) {
            caretModel.moveToOffset(caretModel.getOffset() - 1);
            AutoPopupController.getInstance(context.getProject()).autoPopupParameterInfo(context.getEditor(), method);
          }
        }
      }
      if (element instanceof ParserClass) {
        CaretModel caretModel = context.getEditor().getCaretModel();
        String s = context.getEditor().getDocument().getText().substring(caretModel.getOffset(), caretModel.getOffset() + 1);
        if (!s.equals(":")) {
          context.getEditor().getDocument().insertString(caretModel.getOffset(), ":");
          caretModel.moveToOffset(caretModel.getOffset() + 1);
        }
//      AutoPopupController.getInstance(context.project).autoPopupMemberLookup(context.editor);

      }
    }
  }
}
