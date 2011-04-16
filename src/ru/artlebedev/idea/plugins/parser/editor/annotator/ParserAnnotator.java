package ru.artlebedev.idea.plugins.parser.editor.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserElementVisitor;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserIncludePathImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserMethodImpl;

import java.awt.*;
import java.util.*;
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

public class ParserAnnotator extends ParserElementVisitor implements Annotator {
  private AnnotationHolder myHolder;

  public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
    myHolder = holder;
    psiElement.accept(this);
  }

  public void visitParserIncludePath(ParserIncludePathImpl parserIncludePath) {
    PsiElement reference = parserIncludePath.resolve();
    if (reference == null) {
      myHolder.createWarningAnnotation(parserIncludePath, "Cannot resolve file");
    }
  }

  // XXX move colors out to fonts and colors dialog!
  public void visitParserMethod(ParserMethodImpl method) {
    final TextAttributesKey PARSER_METHOD_NAME = TextAttributesKey.createTextAttributesKey(
            "PARSER.METHOD",
            new TextAttributes(Color.GREEN.darker().darker(), null, null, null, Font.BOLD)
    );

    final TextAttributesKey PARSER_GETTER_NAME = TextAttributesKey.createTextAttributesKey(
            "PARSER.GETTER",
            new TextAttributes(Color.PINK.darker().darker(), null, null, null, Font.BOLD)
    );

    final TextAttributesKey PARSER_AUTO_NAME = TextAttributesKey.createTextAttributesKey(
            "PARSER.AUTO",
            new TextAttributes(Color.RED.darker().darker(), null, null, null, Font.BOLD)
    );

    final TextAttributesKey PARSER_UNHANDLED_EXCEPTION_NAME = TextAttributesKey.createTextAttributesKey(
            "PARSER.UNHANDLED_EXCEPTION",
            new TextAttributes(Color.RED.darker().darker(), null, null, null, Font.BOLD)
    );

    Annotation annotation = myHolder.createInfoAnnotation(method.findNameNode(), null);

    if(ParserLanguageConstants.UNHANDLED_EXCEPTION_METHOD_NAME.equals(method.getName())) {
      annotation.setTextAttributes(PARSER_UNHANDLED_EXCEPTION_NAME);
    } else if(ParserLanguageConstants.AUTO_METHOD_NAME.equals(method.getName())) {
      annotation.setTextAttributes(PARSER_AUTO_NAME);
    } else if((method.getName() != null) && method.getName().startsWith(ParserLanguageConstants.GETTER_METHOD_PREFIX)) {
      annotation.setTextAttributes(PARSER_GETTER_NAME);
    } else {
      annotation.setTextAttributes(PARSER_METHOD_NAME);
    }
  }

  public final List<String> skipClassReferenceNames = Arrays.asList(ParserLanguageConstants.CLASS_KEYWORD, ParserLanguageConstants.SELF_CLASS_NAME);

  public void visitParserClassReference(ParserClassReference parserClassReference) {
    final TextAttributesKey PARSER_CLASS_REFERENCE = TextAttributesKey.createTextAttributesKey(
            "PARSER.CLASS_REFERENCE",
            new TextAttributes(Color.MAGENTA.darker().darker().darker(), null, null, null, Font.BOLD)

    );

    /*if (((ParserClassReferenceImpl) parserClassReference).resolve() != null) {
        Annotation annotation = myHolder.createWarningAnnotation(parserClassReference, "Class in not imported explicitely");
        annotation.setTextAttributes(PARSER_CLASS_REFERENCE);
        annotation.registerFix(new IntentionAction() {
          public String getText() {
            return "Import class";
          }

          public String getFamilyName() {
            return "parser";
          }

          public boolean isAvailable(Project project, Editor editor, PsiFile file) {
            return true;
          }

          public void invoke(Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
            if (file instanceof ParserFile) {
              ParserFile parserFile = (ParserFile) file;
              ParserInclude include = PsiTreeUtil.getChildOfType(parserFile, ParserInclude.class);
              if (include == null) {
                PsiDocumentManager.getInstance(project).getDocument(parserFile).insertString(0, "@USE\n");
              }
            }
          }

          public boolean startInWriteAction() {
            return true;
          }
        });
      } else {*/
    if(!skipClassReferenceNames.contains(parserClassReference.getName())) {
      Annotation annotation = myHolder.createInfoAnnotation(parserClassReference, null);
      annotation.setTextAttributes(PARSER_CLASS_REFERENCE);
    }
    //}
  }
}