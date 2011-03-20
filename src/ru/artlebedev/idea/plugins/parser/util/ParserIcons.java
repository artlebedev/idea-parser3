package ru.artlebedev.idea.plugins.parser.util;

import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

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

public interface ParserIcons {
  @NonNls
  String DATA_PATH = "/icons/";

  Icon PARSER_LARGE_ICON = IconLoader.findIcon(DATA_PATH + "parser_file.png");
  Icon PARSER_FILE_ICON = IconLoader.findIcon(DATA_PATH + "parser_file.png");
  Icon EMPTY = IconLoader.findIcon(DATA_PATH + "empty.gif");
  Icon CONFIGURATION = IconLoader.findIcon(DATA_PATH + "settings.png");
}