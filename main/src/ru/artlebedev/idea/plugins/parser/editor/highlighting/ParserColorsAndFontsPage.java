package ru.artlebedev.idea.plugins.parser.editor.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.ParserIcons;

import javax.swing.*;
import java.util.Map;

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

public class ParserColorsAndFontsPage implements ColorSettingsPage {
  @NotNull
  @Override
  public String getDisplayName() {
    return "Parser";
  }

  @Override
  public Icon getIcon() {
    return ParserIcons.PARSER_FILE_ICON;
  }

  private static final AttributesDescriptor[] ATTRS =
          new AttributesDescriptor[]{
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_KEYWORD_ID, ParserFileSyntaxHighlighter.PARSER_KEYWORD),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_RESULT_ID, ParserFileSyntaxHighlighter.PARSER_RESULT),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_KEY_AT_SIGN_ID, ParserFileSyntaxHighlighter.PARSER_KEY_AT_SIGN),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_STRING_ID, ParserFileSyntaxHighlighter.PARSER_STRING),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_LINE_COMMENT_ID, ParserFileSyntaxHighlighter.PARSER_LINE_COMMENT),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_PARSERDOC_COMMENT_ID, ParserFileSyntaxHighlighter.PARSER_PARSERDOC_COMMENT),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_OPERATION_SIGN_ID, ParserFileSyntaxHighlighter.PARSER_OPERATION_SIGN),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_PARENTHS_ID, ParserFileSyntaxHighlighter.PARSER_PARENTHESES),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_BRACKETS_ID, ParserFileSyntaxHighlighter.PARSER_BRACKETS),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_BRACES_ID, ParserFileSyntaxHighlighter.PARSER_BRACES),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_NUMBER_ID, ParserFileSyntaxHighlighter.PARSER_NUMBER),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_SEMICOLON_ID, ParserFileSyntaxHighlighter.PARSER_SEMICOLON),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_BAD_CHARACTER_ID, ParserFileSyntaxHighlighter.PARSER_BAD_CHARACTER),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_SELF_ID, ParserFileSyntaxHighlighter.PARSER_SELF),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_METHOD_ID, ParserFileSyntaxHighlighter.PARSER_METHOD),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_METHOD_GETTER_ID, ParserFileSyntaxHighlighter.PARSER_METHOD_GETTER),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_METHOD_SETTER_ID, ParserFileSyntaxHighlighter.PARSER_METHOD_SETTER),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_METHOD_AUTO_ID, ParserFileSyntaxHighlighter.PARSER_METHOD_AUTO),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_METHOD_UNHANDLED_EXCEPTION_ID, ParserFileSyntaxHighlighter.PARSER_METHOD_UNHANDLED_EXCEPTION),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_CLASS_REFERENCE_ID, ParserFileSyntaxHighlighter.PARSER_CLASS_REFERENCE),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_SYSTEM_CLASS_REFERENCE_ID, ParserFileSyntaxHighlighter.PARSER_SYSTEM_CLASS_REFERENCE),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_METHOD_STATIC_ID, ParserFileSyntaxHighlighter.PARSER_METHOD_STATIC),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_METHOD_CONF_ID, ParserFileSyntaxHighlighter.PARSER_METHOD_CONF),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.HTML_ENTITIES_ID, ParserFileSyntaxHighlighter.HTML_ENTITIES)
          };

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return ATTRS;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new ParserFileSyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "@CLASS\n" +
            "test\n\n" +
            "@BASE\n" +
            "test2\n\n" +
            "@OPTIONS\n" +
            "locals\n\n" +
            "# Everything marked as [Annotator] is not shown here.\n\n" +
            "@auto[]\n" +
            "$result[test]\n" +
            "###\n\n" +
            "#: constructor\n" +
            "#: param param1 type string\n" +
            "@create[param1]\n" +
            "###\n\n" +
            "@method1[]\n" +
            "###\n\n" +
            "@static:staticmethod1[]\n" +
            "###\n\n" +
            "@method2[]\n" +
            "^self.method1[]\n" +
            "$table[^table::create[]]\n" +
            "###\n" +
            "@htmlmethod[]\n" +
            "Just a sample html text with entity &nbsp^;\n" +
            "###\n" +
            "";

  }

  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }
}
