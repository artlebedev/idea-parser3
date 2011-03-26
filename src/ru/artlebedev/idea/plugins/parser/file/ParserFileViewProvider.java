package ru.artlebedev.idea.plugins.parser.file;

import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.intellij.openapi.fileTypes.PlainTextLanguage;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.LanguageSubstitutors;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;
import com.intellij.psi.templateLanguages.TemplateLanguage;
import com.intellij.psi.templateLanguages.TemplateLanguageFileViewProvider;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;

import java.util.Arrays;
import java.util.Set;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
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

public class ParserFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements TemplateLanguageFileViewProvider {
  private final Language myTemplateDataLanguage;
  private static final THashSet<Language> ourRelevantLanguages =
          new THashSet(Arrays.asList(new Language[]{ParserFileType.PARSER_LANGUAGE, StdFileTypes.HTML.getLanguage()}));

  public ParserFileViewProvider(PsiManager manager, VirtualFile virtualFile, boolean physical) {
    super(manager, virtualFile, physical);
    Language language = getTemplateDataLanguage(virtualFile, manager.getProject());
    this.myTemplateDataLanguage = ((language instanceof TemplateLanguage) ? PlainTextLanguage.INSTANCE : LanguageSubstitutors.INSTANCE.substituteLanguage(language, virtualFile, manager.getProject()));
  }

  @NotNull
  @Override
  public Language getBaseLanguage() {
    return ParserFileType.PARSER_LANGUAGE;
  }

  @NotNull
  @Override
  public Language getTemplateDataLanguage() {
    return this.myTemplateDataLanguage;
  }

  @Override
  protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile fileCopy) {
    return new ParserFileViewProvider(getManager(), fileCopy, false, this.myTemplateDataLanguage);
  }

  public ParserFileViewProvider(PsiManager manager, VirtualFile virtualFile, boolean physical, Language templateDataLanguage) {
    super(manager, virtualFile, physical);
    this.myTemplateDataLanguage = templateDataLanguage;
  }

  public static Language getTemplateDataLanguage(VirtualFile virtualFile, Project project) {
    Language language = TemplateDataLanguageMappings.getInstance(project).getMapping(virtualFile);
    return language == null ? StdFileTypes.HTML.getLanguage() : language;
  }

  @NotNull
  public Set<Language> getLanguages() {
    return new THashSet(Arrays.asList(new Language[] { ParserFileType.PARSER_LANGUAGE, getTemplateDataLanguage() }));
  }

  protected PsiFile createFile(Language lang) {
    if (lang == getTemplateDataLanguage())
    {
      PsiFileImpl file = (PsiFileImpl)((ParserDefinition) LanguageParserDefinitions.INSTANCE.forLanguage(lang)).createFile(this);

      file.setContentElementType(
        new TemplateDataElementType("PARSER_TEMPLATE_DATA", ParserFileType.PARSER_LANGUAGE, ParserTokenTypes.TEMPLATE_HTML_TEXT, ParserTokenTypes.OUTER_ELEMENT_TYPE)
      );
      return file;
    }
    if (lang == getBaseLanguage()) {
      return LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
    }
    return null;
  }
}
