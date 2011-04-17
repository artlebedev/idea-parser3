package ru.artlebedev.idea.plugins.parser.zencoding;

import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.codeInsight.template.zencoding.tokens.TemplateToken;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.file.ParserFileType;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 04.04.11
 * Time: 15:32
 */
public class ParserZenCodingGeneratorImpl extends ParserZenCodingGenerator {
  @Override
  public TemplateImpl generateTemplate(@NotNull TemplateToken token, boolean hasChildren, @NotNull PsiElement context) {
    return token.getTemplate();
  }

  @Override
  public boolean isMyContext(@NotNull PsiElement context, boolean wrapping) {
    return context.getLanguage() == ParserFileType.PARSER_LANGUAGE;
  }

  @Override
  public boolean isAppliedByDefault(@NotNull PsiElement context) {
    return true;
  }
}
