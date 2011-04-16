package ru.artlebedev.idea.plugins.parser.editor.codecompletion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserAfterBirdCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserAfterDollarCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserAfterSignCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserDefaultCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserLogicalStatementCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserOptionCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserTaintCompletionProvider;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2011 ArtLebedev Studio
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

public class ParserCompletionContributor extends CompletionContributor {
  private static final Logger log = Logger.getInstance("ParserCompletionContributor");

  private static final ElementPattern<PsiElement> DEFAULT = StandardPatterns.instanceOf(PsiElement.class).andNot(psiElement().afterLeaf("@"));
  private static final ElementPattern<PsiElement> OPTION = StandardPatterns.instanceOf(PsiElement.class).andNot(psiElement().afterLeaf("@"));
  private static final ElementPattern<PsiElement> TAINT = StandardPatterns.instanceOf(PsiElement.class).andNot(psiElement().afterLeaf("@"));
  private static final ElementPattern<PsiElement> LOGICAL_STATEMENT = StandardPatterns.instanceOf(PsiElement.class);
  private static final ElementPattern<PsiElement> AFTER_BIRD = psiElement().afterLeaf("^");
  private static final ElementPattern<PsiElement> AFTER_DOLLAR = psiElement().afterLeaf("$");
  private static final ElementPattern<PsiElement> AFTER_SIGN = psiElement().afterLeaf("@");

  public ParserCompletionContributor() {
    log.info("Created parser completion contributor");

    extend(CompletionType.BASIC, DEFAULT,           new ParserDefaultCompletionProvider());
    extend(CompletionType.BASIC, OPTION,            new ParserOptionCompletionProvider());
    extend(CompletionType.BASIC, TAINT,             new ParserTaintCompletionProvider());
    extend(CompletionType.BASIC, LOGICAL_STATEMENT, new ParserLogicalStatementCompletionProvider());
    extend(CompletionType.BASIC, AFTER_BIRD,        new ParserAfterBirdCompletionProvider());
    extend(CompletionType.BASIC, AFTER_DOLLAR,      new ParserAfterDollarCompletionProvider());
    extend(CompletionType.BASIC, AFTER_SIGN,        new ParserAfterSignCompletionProvider());
  }
}
