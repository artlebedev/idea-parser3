package ru.artlebedev.idea.plugins.parser.editor.annotator;

import com.intellij.lang.annotation.*;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.editor.highlighting.ParserFileSyntaxHighlighter;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguageConstants;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserElementVisitor;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserClassReference;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserStaticMethod;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserIncludePathImpl;
import ru.artlebedev.idea.plugins.parser.lang.psi.impl.ParserMethodImpl;
import ru.artlebedev.idea.plugins.parser.lang.stdlib.ParserStandardClassesHelper;

import java.util.Arrays;
import java.util.List;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexander Pozdeev</a>
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

public class ParserAnnotator extends ParserElementVisitor implements Annotator {
  private AnnotationHolder myHolder;

  public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
    myHolder = holder;
    psiElement.accept(this);
  }

  public void visitParserIncludePath(ParserIncludePathImpl parserIncludePath) {
    PsiElement reference = parserIncludePath.resolve();
    if (reference == null) {
      AnnotationBuilder annotationBuilder =  myHolder.newAnnotation(HighlightSeverity.WARNING, "Cannot resolve file");
      annotationBuilder.create();
    }
  }

  public void visitParserMethod(ParserMethodImpl method) {
    if(method == null)
      return;

    String methodName = method.getName();

    if(methodName == null)
      return;

    AnnotationBuilder annotationBuilder =  myHolder.newSilentAnnotation(HighlightSeverity.WEAK_WARNING);

    annotationBuilder.range(method.findNameNode().getTextRange());
    if(ParserLanguageConstants.UNHANDLED_EXCEPTION_METHOD_NAME.equals(methodName)) {
      annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_METHOD_UNHANDLED_EXCEPTION);
    } else if(ParserLanguageConstants.AUTO_METHOD_NAME.equals(methodName)) {
      annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_METHOD_AUTO);
    } else if(ParserLanguageConstants.CONF_METHOD_NAME.equals(methodName)) {
      annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_METHOD_CONF);
    } else {
      if(methodName.startsWith(ParserLanguageConstants.GETTER_METHOD_PREFIX)) {
        annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_METHOD_GETTER);
      } else if(methodName.startsWith(ParserLanguageConstants.SETTER_METHOD_PREFIX)) {
        annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_METHOD_SETTER);
      } else {
        if(method instanceof ParserStaticMethod) {
          annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_METHOD_STATIC);
        } else {
          annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_METHOD);
        }
      }
    }
    annotationBuilder.create();
  }

  public final List<String> skipClassReferenceNames = Arrays.asList(ParserLanguageConstants.CLASS_KEYWORD, ParserLanguageConstants.SELF_CLASS_NAME);

  public void visitParserClassReference(ParserClassReference parserClassReference) {
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
    String className = parserClassReference.getName();
    if(!skipClassReferenceNames.contains(className)) {
      AnnotationBuilder annotationBuilder =  myHolder.newSilentAnnotation(HighlightSeverity.INFORMATION);
      annotationBuilder.range(parserClassReference.getTextRange());
      if(ParserStandardClassesHelper.loadedStandardClasses.contains(className)) {
        annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_SYSTEM_CLASS_REFERENCE);
      } else {
        annotationBuilder.textAttributes(ParserFileSyntaxHighlighter.PARSER_CLASS_REFERENCE);
      }
      annotationBuilder.create();
    }
    //}
  }
}