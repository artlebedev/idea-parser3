package ru.artlebedev.idea.plugins.parser.file;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

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

public class ParserFileTypeLoader extends FileTypeFactory {
  public void createFileTypes(@NotNull FileTypeConsumer consumer) {
    consumer.consume(ParserFileType.PARSER_FILE_TYPE, ParserFileType.DEFAULT_EXTENSION);
    consumer.consume(ParserFileType.PARSER_FILE_TYPE, ParserFileType.IMPRIMATUR_BLOCK_PROCESSOR_EXTENSION);
    consumer.consume(ParserFileType.PARSER_FILE_TYPE, ParserFileType.IMPRIMATUR_PAGE_PROCESSOR_EXTENSION);
    consumer.consume(ParserFileType.PARSER_FILE_TYPE, ParserFileType.PARSERED_HTML_EXTENSION);
  }
}
