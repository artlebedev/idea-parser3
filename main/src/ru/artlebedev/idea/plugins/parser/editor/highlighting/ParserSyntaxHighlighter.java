package ru.artlebedev.idea.plugins.parser.editor.highlighting;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ide.highlighter.HtmlFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexander Pozdeev</a>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2011-2020 ArtLebedev Studio
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

public class ParserSyntaxHighlighter extends LayeredLexerEditorHighlighter {
    public ParserSyntaxHighlighter(@Nullable final Project project,
                        @Nullable final VirtualFile virtualFile,
                        @NotNull final EditorColorsScheme colors) {
    super(new ParserFileSyntaxHighlighter(), colors);
    registerLayer(ParserTokenTypes.TEMPLATE_HTML_TEXT, new LayerDescriptor(SyntaxHighlighter.PROVIDER.create(HtmlFileType.INSTANCE, project, virtualFile), ""));
  }

}

