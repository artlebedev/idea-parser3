package ru.artlebedev.idea.plugins.parser.util.lookup;

import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.CaretModel;
import ru.artlebedev.idea.plugins.parser.psi.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.ParserMethod;

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

public class ParserInsertHandler implements InsertHandler<LookupElement> {
  public void handleInsert(InsertionContext insertionContext, LookupElement lookupElement) {
    if (lookupElement instanceof ParserMethod) {
      CaretModel caretModel = insertionContext.getEditor().getCaretModel();
      String s = insertionContext.getEditor().getDocument().getText().substring(caretModel.getOffset(), caretModel.getOffset() + 1);
      if (!s.equals("[") && !s.equals("{") && !s.equals("(")) {
        insertionContext.getEditor().getDocument().insertString(caretModel.getOffset(), "[]");
        caretModel.moveToOffset(caretModel.getOffset() + 2);
        ParserMethod method = (ParserMethod) lookupElement;
        if (method.getParameterList().getChildren().length > 0) {
          caretModel.moveToOffset(caretModel.getOffset() - 1);
          AutoPopupController.getInstance(insertionContext.getProject()).autoPopupParameterInfo(insertionContext.getEditor(), method);
        }
      }
    }
    if (lookupElement instanceof ParserClass) {
      CaretModel caretModel = insertionContext.getEditor().getCaretModel();
      String s = insertionContext.getEditor().getDocument().getText().substring(caretModel.getOffset(), caretModel.getOffset() + 1);
      if (!s.equals(":")) {
        insertionContext.getEditor().getDocument().insertString(caretModel.getOffset(), ":");
        caretModel.moveToOffset(caretModel.getOffset() + 1);
      }
    }

  }
}

