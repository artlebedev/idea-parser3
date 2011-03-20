package ru.artlebedev.idea.plugins.parser.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.parser.parsers.StatementParser;

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

public class ParserParser implements PsiParser {
  @NotNull
  @Override
  public ASTNode parse(IElementType iElementType, PsiBuilder psiBuilder) {
    final PsiBuilder.Marker rootMarker = psiBuilder.mark();
    while (!psiBuilder.eof()) {
      StatementParser.parseSourceElement(psiBuilder);
    }
    rootMarker.done(iElementType);
    return psiBuilder.getTreeBuilt();
  }
}
