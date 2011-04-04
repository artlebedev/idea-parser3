package ru.artlebedev.idea.plugins.parser.zencoding;

import com.intellij.codeInsight.template.CustomTemplateCallback;
import com.intellij.codeInsight.template.zencoding.generators.ZenCodingGenerator;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 04.04.11
 * Time: 15:29
 */
public abstract class ParserZenCodingGenerator extends ZenCodingGenerator {
  @Nullable
  public String computeTemplateKey(@NotNull CustomTemplateCallback callback) {
    Editor editor = callback.getEditor();
    PsiElement element = callback.getContext();
    System.out.println(element);
    int line = editor.getCaretModel().getLogicalPosition().line;
    int lineStart = editor.getDocument().getLineStartOffset(line);
    int elementStart;
    do {
      elementStart = element.getTextRange().getStartOffset();
      int startOffset = elementStart > lineStart ? elementStart : lineStart;
      String key = computeKey(editor, startOffset);
      if (key != null) {
        while (key.length() > 0 && !ZenCodingTemplate.checkTemplateKey(key, callback, this)) {
          key = key.substring(1);
        }
        if (key.length() > 0) {
          return key;
        }
      }
      element = element.getParent();
    }
    while (element != null && elementStart > lineStart);
    return null;
  }
}
