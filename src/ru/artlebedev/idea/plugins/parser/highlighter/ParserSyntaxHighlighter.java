package ru.artlebedev.idea.plugins.parser.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.XmlHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.xml.XmlTokenType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lexer.ParserLexer;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

public class ParserSyntaxHighlighter extends SyntaxHighlighterBase implements ParserTokenTypes {
  @NotNull
  public Lexer getHighlightingLexer() {
    return new ParserLexer();
  }

  @NonNls
  public static final String PARSER_KEYWORD_ID = "Parser keyword";
  @NonNls
  public static final String PARSER_RESULT_ID = "Parser result";
  @NonNls
  public static final String PARSER_KEY_AT_SIGN_ID = "Parser key at sign";
  @NonNls
  public static final String PARSER_STRING_ID = "Parser string";
  @NonNls
  public static final String PARSER_LINE_COMMENT_ID = "Parser line comment";
  @NonNls
  public static final String PARSER_PARSERDOC_COMMENT_ID = "Parser parserdoc comment";
  @NonNls
  public static final String PARSER_OPERATION_SIGN_ID = "Parser operation sign";
  @NonNls
  public static final String PARSER_PARENTHS_ID = "Parser parenths";
  @NonNls
  public static final String PARSER_BRACKETS_ID = "Parser brackets";
  @NonNls
  public static final String PARSER_BRACES_ID = "Parser braces";
  @NonNls
  public static final String PARSER_NUMBER_ID = "Parser number";
  @NonNls
  public static final String PARSER_SEMICOLON_ID = "Parser semicolon";
  @NonNls
  public static final String PARSER_BAD_CHARACTER_ID = "Parser bad character";

  static {
    TextAttributesKey.createTextAttributesKey(PARSER_KEYWORD_ID, SyntaxHighlighterColors.KEYWORD.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_RESULT_ID, new TextAttributes(Color.CYAN.darker().darker(), null, Color.CYAN.darker().darker(), EffectType.LINE_UNDERSCORE, Font.PLAIN));
    TextAttributesKey.createTextAttributesKey(PARSER_KEY_AT_SIGN_ID, new TextAttributes(Color.GREEN.darker(), null, null, null, Font.BOLD));
    TextAttributesKey.createTextAttributesKey(PARSER_STRING_ID, SyntaxHighlighterColors.STRING.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_LINE_COMMENT_ID, SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_PARSERDOC_COMMENT_ID, new TextAttributes(
                  SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes().getForegroundColor().darker().darker(),
                  null,
                  SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes().getForegroundColor().darker().darker(),
                  EffectType.LINE_UNDERSCORE,
                  Font.PLAIN));
    TextAttributesKey.createTextAttributesKey(PARSER_OPERATION_SIGN_ID, SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_PARENTHS_ID, SyntaxHighlighterColors.PARENTHS.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_BRACKETS_ID, SyntaxHighlighterColors.BRACKETS.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_BRACES_ID, SyntaxHighlighterColors.BRACES.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_NUMBER_ID, SyntaxHighlighterColors.NUMBER.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_SEMICOLON_ID, SyntaxHighlighterColors.JAVA_SEMICOLON.getDefaultAttributes());
    TextAttributesKey.createTextAttributesKey(PARSER_BAD_CHARACTER_ID, HighlighterColors.BAD_CHARACTER.getDefaultAttributes());
  }

  public static final TextAttributesKey PARSER_KEYWORD = TextAttributesKey.createTextAttributesKey(PARSER_KEYWORD_ID);
  public static final TextAttributesKey PARSER_RESULT = TextAttributesKey.createTextAttributesKey(PARSER_RESULT_ID);
  public static final TextAttributesKey PARSER_KEY_AT_SIGN = TextAttributesKey.createTextAttributesKey(PARSER_KEY_AT_SIGN_ID);
  public static final TextAttributesKey PARSER_STRING = TextAttributesKey.createTextAttributesKey(PARSER_STRING_ID);
  public static final TextAttributesKey PARSER_LINE_COMMENT = TextAttributesKey.createTextAttributesKey(PARSER_LINE_COMMENT_ID);
  public static final TextAttributesKey PARSER_PARSERDOC_COMMENT = TextAttributesKey.createTextAttributesKey(PARSER_PARSERDOC_COMMENT_ID);
  public static final TextAttributesKey PARSER_OPERATION_SIGN = TextAttributesKey.createTextAttributesKey(PARSER_OPERATION_SIGN_ID);
  public static final TextAttributesKey PARSER_PARENTHS = TextAttributesKey.createTextAttributesKey(PARSER_PARENTHS_ID);
  public static final TextAttributesKey PARSER_BRACKETS = TextAttributesKey.createTextAttributesKey(PARSER_BRACKETS_ID);
  public static final TextAttributesKey PARSER_BRACES = TextAttributesKey.createTextAttributesKey(PARSER_BRACES_ID);
  public static final TextAttributesKey PARSER_NUMBER = TextAttributesKey.createTextAttributesKey(PARSER_NUMBER_ID);
  public static final TextAttributesKey PARSER_SEMICOLON = TextAttributesKey.createTextAttributesKey(PARSER_SEMICOLON_ID);
  public static final TextAttributesKey PARSER_BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(PARSER_BAD_CHARACTER_ID);

  private static final Map<IElementType, TextAttributesKey> attributes;

  public static final TokenSet parserDocSet = TokenSet.create(ParserTokenTypes.PARSERDOC_TYPE_KEYWORD, ParserTokenTypes.PARSERDOC_PARAM_KEYWORD, ParserTokenTypes.PARSERDOC_CONSTRUCTOR_KEYWORD, ParserTokenTypes.PARSERDOC_OPTIONAL_KEYWORD);
  public static final TokenSet parsSet = TokenSet.create(ParserTokenTypes.LPAR, ParserTokenTypes.RPAR);
  public static final TokenSet bracesSet = TokenSet.create(ParserTokenTypes.LBRACE, ParserTokenTypes.RBRACE);
  public static final TokenSet bracketsSet = TokenSet.create(ParserTokenTypes.LBRACKET, ParserTokenTypes.RBRACKET);
  public static final TokenSet atSignSet = TokenSet.create(ParserTokenTypes.KEY_AT_SIGN, ParserTokenTypes.HAT_SIGN, ParserTokenTypes.DOLLAR);
  public static final TokenSet lineCommentSet = TokenSet.create(ParserTokenTypes.SHARP_COMMENT, ParserTokenTypes.PARSERDOC_START);
  public static final TokenSet resultKeywordSet = TokenSet.create(ParserTokenTypes.RESULT_KEYWORD);
  public static final TokenSet numericLiteralSet = TokenSet.create(ParserTokenTypes.NUMERIC_LITERAL);
  public static final TokenSet stringLiteralSet = TokenSet.create(ParserTokenTypes.STRING_LITERAL);
  public static final TokenSet semicolonSet = TokenSet.create(ParserTokenTypes.SEMICOLON);
  public static final TokenSet badCharacterSet = TokenSet.create(ParserTokenTypes.BAD_CHARACTER);
  public static final TokenSet xmlTagSet = TokenSet.create(
    XmlTokenType.XML_START_TAG_START,
    XmlTokenType.XML_END_TAG_START,
    XmlTokenType.XML_TAG_END,
    XmlTokenType.XML_EMPTY_ELEMENT_END,
    XmlTokenType.XML_TAG_NAME,
    XmlTokenType.TAG_WHITE_SPACE,
    XmlTokenType.XML_NAME,
    XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN,
    XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER,
    XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER,
    XmlTokenType.XML_EQ
  );
  public static final TokenSet xmlAttributeValueSet = TokenSet.create(
    XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN,
    XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER,
    XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER
  );
  public static final TokenSet xmlAttributeName = TokenSet.create(
    XmlTokenType.XML_NAME,
    XmlTokenType.XML_EQ
  );
  public static final TokenSet xmlAttributeTagName = TokenSet.create(
    XmlTokenType.XML_TAG_NAME
  );

  static {
    attributes = new HashMap<IElementType, TextAttributesKey>();

    fillMap(attributes, ParserTokenTypes.KEYWORDS, PARSER_KEYWORD);
    fillMap(attributes, ParserTokenTypes.OPERATIONS, PARSER_OPERATION_SIGN);
    fillMap(attributes, resultKeywordSet, PARSER_RESULT);
    fillMap(attributes, numericLiteralSet, PARSER_NUMBER);
    fillMap(attributes, stringLiteralSet, PARSER_STRING);
    fillMap(attributes, parsSet, PARSER_PARENTHS);
    fillMap(attributes, bracesSet, PARSER_BRACES);
    fillMap(attributes, bracketsSet, PARSER_BRACKETS);
    fillMap(attributes, atSignSet, PARSER_KEY_AT_SIGN);
    fillMap(attributes, semicolonSet, PARSER_SEMICOLON);
    fillMap(attributes, lineCommentSet, PARSER_LINE_COMMENT);
    fillMap(attributes, parserDocSet, PARSER_PARSERDOC_COMMENT);
    fillMap(attributes, badCharacterSet, PARSER_BAD_CHARACTER);
    fillMap(attributes, xmlTagSet, XmlHighlighterColors.XML_TAG);
    fillMap(attributes, xmlAttributeTagName, XmlHighlighterColors.XML_TAG_NAME);
    fillMap(attributes, xmlAttributeName, XmlHighlighterColors.XML_ATTRIBUTE_NAME);
    fillMap(attributes, xmlAttributeValueSet, XmlHighlighterColors.XML_ATTRIBUTE_VALUE);
  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(attributes.get(tokenType));
  }
}
