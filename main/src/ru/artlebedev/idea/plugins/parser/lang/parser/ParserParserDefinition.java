package ru.artlebedev.idea.plugins.parser.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserLexer;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserPsiCreator;

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

public class ParserParserDefinition implements ParserDefinition, ParserElementTypes {
  @NotNull
  public Lexer createLexer(Project project) {
    return new ParserLexer();
  }

  public PsiParser createParser(Project project) {
    return new ParserParser();
  }

  public IFileElementType getFileNodeType() {
    return FILE;
  }

  @NotNull
  public TokenSet getWhitespaceTokens() {
    return TokenSet.create(ParserTokenTypes.NEW_LINE_INDENT);
  }

  @NotNull
  public TokenSet getCommentTokens() {
    return ParserTokenTypes.COMMENTS;
  }

  @NotNull
  public TokenSet getStringLiteralElements() {
    return ParserTokenTypes.STRING_LITERALS;
  }

  public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode leftAst, ASTNode rightAst) {
    return null;
  }

  @NotNull
  public PsiElement createElement(ASTNode node) {
    return ParserPsiCreator.createElement(node);
  }

  public PsiFile createFile(FileViewProvider viewProvider) {
    return new ParserFile(viewProvider);
  }
}
