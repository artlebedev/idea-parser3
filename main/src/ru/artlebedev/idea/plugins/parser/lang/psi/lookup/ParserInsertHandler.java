package ru.artlebedev.idea.plugins.parser.lang.psi.lookup;

import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserMethod;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexandr Pozdeev</a>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006 <a href="mailto:a4blank@yahoo.com">Jay Bird</a>
 * Copyright 2006-2020 ArtLebedev Studio
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

  private static final ParserInsertHandler INSTANCE = new ParserInsertHandler();

  private ParserInsertHandler() {
    super();
  }

  public static ParserInsertHandler getInstance() {
    return INSTANCE;
  }

  public static final String[] bracesExpands = new String[]{
          "^connect",
          ":connect",
          ".connect",
          "^server",
          ":server",
          ".server",
          "^sql",
          ":sql",
          ".sql",
          "^table::create",
          "^xdoc::create",
          "^process",
          "^curl:session",
          "^junction"
  };

  public static final String[] roundBracesExpands = new String[]{
          "^inet:ntoa",
          "^math:abs",
          "^math:sign",
          "^math:round",
          "^math:floor",
          "^math:ceiling",
          "^math:trunc",
          "^math:frac",
          "^math:degrees",
          "^math:radians",
          "^math:sin",
          "^math:asin",
          "^math:cos",
          "^math:acos",
          "^math:tan",
          "^math:atan",
          "^math:exp",
          "^math:log",
          "^math:log10",
          "^math:pow",
          "^math:sqrt",
          "^math:random"
  };

  public void handleInsert(final InsertionContext context,@NotNull LookupElement lookupElement) {
    final Editor editor = context.getEditor();

    if (context.getCompletionChar() != '.') {
      final CaretModel caretModel = editor.getCaretModel();
      caretModel.moveToOffset(context.getSelectionEndOffset());
      editor.getSelectionModel().removeSelection();
      PsiElement element = lookupElement.getPsiElement();

      if (element instanceof ParserMethod) {
        try {
          boolean match = false;
          for(String bracesExpand : bracesExpands) {
            if(caretModel.getOffset() - bracesExpand.length() > 0) {
              if(context.getEditor().getDocument().getText().substring(caretModel.getOffset() - bracesExpand.length(), caretModel.getOffset()).trim().equals(bracesExpand)) {
                context.getEditor().getDocument().insertString(caretModel.getOffset(), "{}");
                caretModel.moveToOffset(caretModel.getOffset() + 1);
                match = true;
                break;
              }
            }
          }

          if(!match) {
            for(String bracesExpand : roundBracesExpands) {
              if(caretModel.getOffset() - bracesExpand.length() > 0) {
                if(context.getEditor().getDocument().getText().substring(caretModel.getOffset() - bracesExpand.length(), caretModel.getOffset()).trim().equals(bracesExpand)) {
                  context.getEditor().getDocument().insertString(caretModel.getOffset(), "()");
                  caretModel.moveToOffset(caretModel.getOffset() + 1);
                  match = true;
                  break;
                }
              }
            }
          }

          if(!match) {
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
        } catch(Exception ignored){

        }
      }
      if (element instanceof ParserClass) {
        try {
          ParserMethod[] parserMethods = PsiTreeUtil.getChildrenOfType(element, ParserMethod.class);

          boolean allMethodsDynamic = true;
          for(ParserMethod parserMethod : parserMethods) {
            if(!parserMethod.isDynamic() && !parserMethod.isConstructor()) {
              allMethodsDynamic = false;
            }
          }

          if(!allMethodsDynamic) {
            String s = context.getEditor().getDocument().getText().substring(caretModel.getOffset(), caretModel.getOffset() + 1);
            if (!s.equals(":")) {
              context.getEditor().getDocument().insertString(caretModel.getOffset(), ":");
              caretModel.moveToOffset(caretModel.getOffset() + 1);
            }
          } else {
            String s = context.getEditor().getDocument().getText().substring(caretModel.getOffset(), caretModel.getOffset() + 1);
            if (!s.equals(":")) {
              context.getEditor().getDocument().insertString(caretModel.getOffset(), "::");
              caretModel.moveToOffset(caretModel.getOffset() + 2);
            }
          }
//      AutoPopupController.getInstance(context.project).autoPopupMemberLookup(context.editor);
        } catch(Exception ignored) {

        }
      }
    } else {
      AutoPopupController.getInstance(context.getProject()).autoPopupMemberLookup(editor, null);
    }
  }
}
