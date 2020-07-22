package ru.artlebedev.idea.plugins.parser.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.Parser;
import ru.artlebedev.idea.plugins.parser.ParserIcons;
import ru.artlebedev.idea.plugins.parser.lang.ParserLanguage;

import javax.swing.*;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2020 <a href="mailto:allex@artlebedev.ru">Alexander Pozdeev</a>
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

public class ParserFileType extends LanguageFileType {
  public static final ParserFileType INSTANCE = new ParserFileType();

  private static final String DEFAULT_EXTENSION = "p";
  private static final String IMPRIMATUR_BLOCK_PROCESSOR_EXTENSION = "bp";
  private static final String IMPRIMATUR_PAGE_PROCESSOR_EXTENSION = "pp";
  private static final String PARSERED_XML_EXTENSION = "pxml";

  private static final String[] extensions = {
          DEFAULT_EXTENSION,
          IMPRIMATUR_BLOCK_PROCESSOR_EXTENSION,
          IMPRIMATUR_PAGE_PROCESSOR_EXTENSION,
          PARSERED_XML_EXTENSION
  };

  protected ParserFileType() {
    super(ParserLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return Parser.languageName;
  }

  @NotNull
  @Override
  public String getDescription() {
    return Parser.languageDescription;
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return extensions[0];
  }

  /*
   * We have:
   * - Parser file
   * - Parser class
   * - Imprimatur page processor
   * - Imprimatur block processor
   * - auto.p
   * file icons actually, should we put them here?
   * -- dwr
   */
  @Nullable
  @Override
  public Icon getIcon() {
    return ParserIcons.PARSER_FILE_ICON;
  }
}
