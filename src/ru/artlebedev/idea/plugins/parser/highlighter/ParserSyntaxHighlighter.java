package ru.artlebedev.idea.plugins.parser.highlighter;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 26.03.11
 * Time: 23:28
 */
public class ParserSyntaxHighlighter extends LayeredLexerEditorHighlighter {
    public ParserSyntaxHighlighter(@Nullable final Project project,
                        @Nullable final VirtualFile virtualFile,
                        @NotNull final EditorColorsScheme colors) {
    super(new ParserFileSyntaxHighlighter(), colors);
    registerLayer(ParserTokenTypes.TEMPLATE_HTML_TEXT, new LayerDescriptor(SyntaxHighlighter.PROVIDER.create(StdFileTypes.HTML, project, virtualFile), ""));
  }

}

