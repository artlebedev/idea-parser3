package ru.artlebedev.idea.plugins.parser;

import com.intellij.openapi.util.IconLoader;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NonNls;

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

public interface ParserIcons {
  @NonNls
  String DATA_PATH = "/ru/artlebedev/idea/plugins/parser/icons/";

//  Icon PARSER_LARGE_ICON = IconLoader.findIcon(DATA_PATH + "parser_large.png");
  Icon PARSER_MODULE_ICON = IconLoader.findIcon(DATA_PATH + "parser_module.png");
  Icon PARSER_FILE_ICON = IconLoader.findIcon(DATA_PATH + "parser_file.png");

  Icon PARSER_CLASS_FILE_ICON = IconLoader.findIcon(DATA_PATH + "parser_class.png");
//  Icon PARSER_HTML_FILE_ICON = IconLoader.findIcon(DATA_PATH + "parser_html.png");

  Icon EMPTY = IconLoader.findIcon(DATA_PATH + "empty.gif");
  Icon CONFIGURATION = IconLoader.findIcon(DATA_PATH + "settings.png");

  Icon PARSER_CLASS_ICON = PlatformIcons.CLASS_ICON;
  Icon PARSER_VARIABLE_ICON = PlatformIcons.VARIABLE_ICON;
  Icon PARSER_CLASS_INITIALIZER_ICON = PlatformIcons.CLASS_INITIALIZER;
  Icon PARSER_METHOD_ICON = PlatformIcons.METHOD_ICON;
  Icon PARSER_STATIC_METHOD_ICON = IconLoader.getIcon("/nodes/static.png");
  Icon PARSER_PROPERTY_ICON = PlatformIcons.PROPERTY_ICON;
  Icon PARSER_PARAMETER_ICON = PlatformIcons.PARAMETER_ICON;
  Icon PARSER_BASE_CLASS_ICON = PlatformIcons.ABSTRACT_CLASS_ICON;
}