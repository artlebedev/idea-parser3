package ru.artlebedev.idea.plugins.parser.editor.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserLexer;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

public class ParserFileSyntaxHighlighter extends SyntaxHighlighterBase implements ParserTokenTypes {
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
  @NonNls
  public static final String PARSER_SELF_ID = "Parser self";
  @NonNls
  public static final String PARSER_OPERATORS_ID = "Parser operators";
  @NonNls
  public static final String HTML_ENTITIES_ID = "Html entities";
  @NonNls
  public static final String PARSER_METHOD_ID = "[Annotator] Parser methods";
  @NonNls
  public static final String PARSER_METHOD_STATIC_ID = "[Annotator] Parser static methods";
  @NonNls
  public static final String PARSER_METHOD_GETTER_ID = "[Annotator] Parser getter methods";
  @NonNls
  public static final String PARSER_METHOD_SETTER_ID = "[Annotator] Parser setter methods";
  @NonNls
  public static final String PARSER_METHOD_AUTO_ID = "[Annotator] Parser auto methods";
  @NonNls
  public static final String PARSER_METHOD_CONF_ID = "[Annotator] Parser conf methods";
  @NonNls
  public static final String PARSER_METHOD_UNHANDLED_EXCEPTION_ID = "[Annotator] Parser unhandled exception methods";
  @NonNls
  public static final String PARSER_CLASS_REFERENCE_ID = "[Annotator] Parser class reference";
  @NonNls
  public static final String PARSER_SYSTEM_CLASS_REFERENCE_ID = "[Annotator] Parser system class reference";

  static {
    TextAttributesKey.createTextAttributesKey(PARSER_KEYWORD_ID, DefaultLanguageHighlighterColors.KEYWORD);
    TextAttributesKey.createTextAttributesKey(PARSER_RESULT_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_KEY_AT_SIGN_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_STRING_ID, DefaultLanguageHighlighterColors.STRING);
    TextAttributesKey.createTextAttributesKey(PARSER_LINE_COMMENT_ID, DefaultLanguageHighlighterColors.LINE_COMMENT);
    TextAttributesKey.createTextAttributesKey(PARSER_PARSERDOC_COMMENT_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_OPERATION_SIGN_ID, DefaultLanguageHighlighterColors.OPERATION_SIGN);
    TextAttributesKey.createTextAttributesKey(PARSER_PARENTHS_ID, DefaultLanguageHighlighterColors.PARENTHESES);
    TextAttributesKey.createTextAttributesKey(PARSER_BRACKETS_ID, DefaultLanguageHighlighterColors.BRACKETS);
    TextAttributesKey.createTextAttributesKey(PARSER_BRACES_ID, DefaultLanguageHighlighterColors.BRACES);
    TextAttributesKey.createTextAttributesKey(PARSER_NUMBER_ID, DefaultLanguageHighlighterColors.NUMBER);
    TextAttributesKey.createTextAttributesKey(PARSER_SEMICOLON_ID, DefaultLanguageHighlighterColors.SEMICOLON);
    TextAttributesKey.createTextAttributesKey(PARSER_BAD_CHARACTER_ID, HighlighterColors.BAD_CHARACTER);
    TextAttributesKey.createTextAttributesKey(PARSER_SELF_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_OPERATORS_ID, DefaultLanguageHighlighterColors.KEYWORD);
    TextAttributesKey.createTextAttributesKey(PARSER_METHOD_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_METHOD_STATIC_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_METHOD_GETTER_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_METHOD_SETTER_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_METHOD_AUTO_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_METHOD_CONF_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_METHOD_UNHANDLED_EXCEPTION_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_CLASS_REFERENCE_ID);
    TextAttributesKey.createTextAttributesKey(PARSER_SYSTEM_CLASS_REFERENCE_ID);
    TextAttributesKey.createTextAttributesKey(HTML_ENTITIES_ID);
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
  public static final TextAttributesKey PARSER_SELF = TextAttributesKey.createTextAttributesKey(PARSER_SELF_ID);
  public static final TextAttributesKey PARSER_OPERATORS = TextAttributesKey.createTextAttributesKey(PARSER_OPERATORS_ID);

  public static final TextAttributesKey PARSER_METHOD = TextAttributesKey.createTextAttributesKey(PARSER_METHOD_ID);
  public static final TextAttributesKey PARSER_METHOD_STATIC = TextAttributesKey.createTextAttributesKey(PARSER_METHOD_STATIC_ID);
  public static final TextAttributesKey PARSER_METHOD_GETTER = TextAttributesKey.createTextAttributesKey(PARSER_METHOD_GETTER_ID);
  public static final TextAttributesKey PARSER_METHOD_SETTER = TextAttributesKey.createTextAttributesKey(PARSER_METHOD_SETTER_ID);
  public static final TextAttributesKey PARSER_METHOD_AUTO = TextAttributesKey.createTextAttributesKey(PARSER_METHOD_AUTO_ID);
  public static final TextAttributesKey PARSER_METHOD_CONF = TextAttributesKey.createTextAttributesKey(PARSER_METHOD_CONF_ID);
  public static final TextAttributesKey PARSER_METHOD_UNHANDLED_EXCEPTION = TextAttributesKey.createTextAttributesKey(PARSER_METHOD_UNHANDLED_EXCEPTION_ID);

  public static final TextAttributesKey PARSER_SYSTEM_CLASS_REFERENCE = TextAttributesKey.createTextAttributesKey(PARSER_SYSTEM_CLASS_REFERENCE_ID);
  public static final TextAttributesKey PARSER_CLASS_REFERENCE = TextAttributesKey.createTextAttributesKey(PARSER_CLASS_REFERENCE_ID);

  public static final TextAttributesKey HTML_ENTITIES = TextAttributesKey.createTextAttributesKey(HTML_ENTITIES_ID);

  private static final Map<IElementType, TextAttributesKey> attributes;

  public static final TokenSet parserDocSet = TokenSet.create(
          ParserTokenTypes.PARSERDOC_TYPE_KEYWORD,
          ParserTokenTypes.PARSERDOC_RESULT_KEYWORD,
          ParserTokenTypes.PARSERDOC_PARAM_KEYWORD,
          ParserTokenTypes.PARSERDOC_CONSTRUCTOR_KEYWORD,
          ParserTokenTypes.PARSERDOC_OPTIONAL_KEYWORD
  );
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
  public static final TokenSet selfSet = TokenSet.create(ParserTokenTypes.SELF_KEYWORD, ParserTokenTypes.CLASS_STATIC_KEYWORD);
  public static final TokenSet htmlEntitySet = TokenSet.create(ParserTokenTypes.HTML_ENTITY);


  static {
    attributes = new HashMap<IElementType, TextAttributesKey>();

    fillMap(attributes, ParserTokenTypes.KEYWORDS, PARSER_KEYWORD);
    //fillMap(attributes, ParserTokenTypes.OPERATIONS, PARSER_OPERATION_SIGN);
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
    fillMap(attributes, selfSet, PARSER_SELF);
    fillMap(attributes, htmlEntitySet, HTML_ENTITIES);
    fillMap(attributes, ParserTokenTypes.OPERATIONS, PARSER_OPERATORS);
  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(attributes.get(tokenType));
  }
}
