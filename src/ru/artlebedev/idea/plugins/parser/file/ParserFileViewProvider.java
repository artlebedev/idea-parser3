package ru.artlebedev.idea.plugins.parser.file;

import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.StdLanguages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.templateLanguages.TemplateLanguageFileViewProvider;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguage;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;

import java.util.Arrays;
import java.util.Set;

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

public class ParserFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements TemplateLanguageFileViewProvider {

  private static final Language PARSER_LANGUAGE = Language.findInstance(ParserLanguage.class);
  private static final Set<Language> ourRelevantLanguages = new THashSet<Language>(
          Arrays.asList(StdLanguages.HTML, PARSER_LANGUAGE)
  );

  public ParserFileViewProvider(PsiManager manager, VirtualFile file, boolean physical) {
    super(manager, file, physical);
  }

  @NotNull
  public Set<Language> getLanguages() {
    return ourRelevantLanguages;
  }

  @Override
  @NotNull
  public Language getBaseLanguage() {
    return PARSER_LANGUAGE;
  }

  @NotNull
  public Language getTemplateDataLanguage() {
    return StdLanguages.HTML;
  }

  private static TemplateDataElementType ourTemplateDataType = new TemplateDataElementType(
          "TEMPLATE_DATA in Parser",
          PARSER_LANGUAGE, ParserTokenTypes.TEMPLATE_HTML_TEXT, ParserTokenTypes.OUTER_ELEMENT_TYPE);

  @Override
  @Nullable
  protected PsiFile createFile(final Language lang) {
    if (lang == getTemplateDataLanguage()) {
      final PsiFileImpl file = (PsiFileImpl) LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
      file.setContentElementType(ourTemplateDataType);
      return file;
    }

    if (lang == getBaseLanguage()) {
      return LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
    }
    return null;
  }

  @Override
  protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile virtualFile) {
    return new ParserFileViewProvider(getManager(), virtualFile, false);
  }
}