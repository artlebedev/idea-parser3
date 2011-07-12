package ru.artlebedev.idea.plugins.parser.editor.codecompletion;

import com.intellij.codeInsight.completion.CompletionData;
import com.intellij.codeInsight.completion.CompletionVariant;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.psi.filters.TextFilter;
import com.intellij.psi.filters.TrueFilter;
import com.intellij.psi.filters.position.LeftNeighbour;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import ru.artlebedev.idea.plugins.parser.lang.psi.lookup.ParserInsertHandler;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2006 <a href="mailto:a4blank@yahoo.com">Jay Bird</a>
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

/**
 * This class handles normally when we should call our completion stuff
 * like ParserCompletionContributor or classes getVariants
 *
 * @author dwr
 * @version 1.0
 *
 * @see ParserCompletionContributor
 */
public class ParserCompletionData extends CompletionData {
  /**
   * Initializer
   */
  public ParserCompletionData() {
    InsertHandler handler = new ParserInsertHandler();
    LeftNeighbour afterDotFilter = new LeftNeighbour(new TextFilter("."));
    CompletionVariant variant = new CompletionVariant(afterDotFilter);
    variant.includeScopeClass(LeafPsiElement.class, true);
    variant.addCompletionFilter(TrueFilter.INSTANCE);
    variant.setInsertHandler(handler);
    registerVariant(variant);

    LeftNeighbour afterColonFilter = new LeftNeighbour(new TextFilter(":"));
    variant = new CompletionVariant(afterColonFilter);
    variant.includeScopeClass(LeafPsiElement.class, true);
    variant.addCompletionFilter(TrueFilter.INSTANCE);
    variant.setInsertHandler(handler);
    registerVariant(variant);

    LeftNeighbour afterHatFilter = new LeftNeighbour(new TextFilter("^"));
    variant = new CompletionVariant(afterHatFilter);
    variant.includeScopeClass(LeafPsiElement.class, true);
    variant.addCompletionFilter(TrueFilter.INSTANCE);
    variant.setInsertHandler(handler);
    registerVariant(variant);

    LeftNeighbour afterDollar = new LeftNeighbour(new TextFilter("$"));
    variant = new CompletionVariant(afterDollar);
    variant.includeScopeClass(LeafPsiElement.class, true);
    variant.addCompletionFilter(TrueFilter.INSTANCE);
    variant.setInsertHandler(handler);
    registerVariant(variant);
  }

//	public String findPrefix(PsiElement psiElement, int i) {
//                        WordCompletionContributor
//		return WordCompletionData.findPrefixSimple(psiElement, i);
//	}
}