package ru.artlebedev.idea.plugins.parser.editor.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserLexer;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

public class ParserSyntaxHighlighter extends SyntaxHighlighterBase {
  private static Map<IElementType, TextAttributesKey> keys1;
  private static Map<IElementType, TextAttributesKey> keys2;

  @NotNull
  public Lexer getHighlightingLexer() {
    return new ParserLexer();
  }

  static final TextAttributesKey PARSER_KEYWORD = TextAttributesKey.createTextAttributesKey(
          "PARSER.KEYWORD",
          SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_RESULT = TextAttributesKey.createTextAttributesKey(
          "PARSER.RESULT",
          new TextAttributes(Color.CYAN.darker().darker(), null, Color.CYAN.darker().darker(), EffectType.LINE_UNDERSCORE, Font.PLAIN)
  );

  static final TextAttributesKey PARSER_KEY_AT_SIGN = TextAttributesKey.createTextAttributesKey(
          "PARSER.KEY_AT_SIGN",
          new TextAttributes(Color.GREEN.darker(), null, null, null, Font.BOLD)
  );

  static final TextAttributesKey PARSER_STRING = TextAttributesKey.createTextAttributesKey(
          "PARSER.STRING",
          SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  /*static final TextAttributesKey JS_REGEXP = TextAttributesKey.createTextAttributesKey(
       "JS.REGEXP",
       new TextAttributes(Color.blue.brighter(), null, null, null, Font.PLAIN)
   );*/

  static final TextAttributesKey PARSER_LINE_COMMENT = TextAttributesKey.createTextAttributesKey(
          "PARSER.LINE_COMMENT",
          SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
          "PARSER.BLOCK_COMMENT",
          SyntaxHighlighterColors.JAVA_BLOCK_COMMENT.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_OPERATION_SIGN = TextAttributesKey.createTextAttributesKey(
          "PARSER.OPERATION_SIGN",
          SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_PARENTHS = TextAttributesKey.createTextAttributesKey(
          "PARSER.PARENTHS",
          SyntaxHighlighterColors.PARENTHS.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_BRACKETS = TextAttributesKey.createTextAttributesKey(
          "PARSER.BRACKETS",
          SyntaxHighlighterColors.BRACKETS.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_BRACES = TextAttributesKey.createTextAttributesKey(
          "PARSER.BRACES",
          SyntaxHighlighterColors.BRACES.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_NUMBER = TextAttributesKey.createTextAttributesKey(
          "PARSER.COLON",
          SyntaxHighlighterColors.NUMBER.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_SEMICOLON = TextAttributesKey.createTextAttributesKey(
          "PARSER.SEMICOLON",
          SyntaxHighlighterColors.JAVA_SEMICOLON.getDefaultAttributes()
  );

  static final TextAttributesKey PARSER_BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
          "PARSER.BADCHARACTER",
          HighlighterColors.BAD_CHARACTER.getDefaultAttributes()
  );

  static {
    keys1 = new HashMap<IElementType, TextAttributesKey>();
    keys2 = new HashMap<IElementType, TextAttributesKey>();

    fillMap(keys1, ParserTokenTypes.KEYWORDS, PARSER_KEYWORD);
    fillMap(keys2, ParserTokenTypes.OPERATIONS, PARSER_OPERATION_SIGN);

    keys1.put(ParserTokenTypes.RESULT_KEYWORD, PARSER_RESULT);
    keys1.put(ParserTokenTypes.NUMERIC_LITERAL, PARSER_NUMBER);
    keys1.put(ParserTokenTypes.STRING_LITERAL, PARSER_STRING);
    /*keys1.put(ParserTokenTypes.REGEXP_LITERAL, JS_REGEXP);*/

    keys1.put(ParserTokenTypes.LPAR, PARSER_PARENTHS);
    keys1.put(ParserTokenTypes.RPAR, PARSER_PARENTHS);

    keys1.put(ParserTokenTypes.LBRACE, PARSER_BRACES);
    keys1.put(ParserTokenTypes.RBRACE, PARSER_BRACES);

    keys1.put(ParserTokenTypes.LBRACKET, PARSER_BRACKETS);
    keys1.put(ParserTokenTypes.RBRACKET, PARSER_BRACKETS);

    keys1.put(ParserTokenTypes.KEY_AT_SIGN, PARSER_KEY_AT_SIGN);
    keys1.put(ParserTokenTypes.HAT_SIGN, PARSER_KEY_AT_SIGN);
    keys1.put(ParserTokenTypes.DOLLAR, PARSER_KEY_AT_SIGN);
    keys1.put(ParserTokenTypes.SEMICOLON, PARSER_SEMICOLON);

//		keys1.put(ParserTokenTypes.REM_COMMENT, PARSER_BLOCK_COMMENT);
//		keys1.put(ParserTokenTypes.XML_STYLE_COMMENT, PARSER_BLOCK_COMMENT);
    keys1.put(ParserTokenTypes.SHARP_COMMENT, PARSER_LINE_COMMENT);
    keys1.put(ParserTokenTypes.PARSERDOC_START, PARSER_LINE_COMMENT);
    keys1.put(ParserTokenTypes.PARSERDOC_TYPE_KEYWORD, PARSER_LINE_COMMENT);
    keys1.put(ParserTokenTypes.PARSERDOC_PARAM_KEYWORD, PARSER_LINE_COMMENT);
    keys1.put(ParserTokenTypes.BAD_CHARACTER, PARSER_BAD_CHARACTER);

  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(keys1.get(tokenType), keys2.get(tokenType));
  }

  public Map<IElementType, TextAttributesKey> getKeys1() {
    return (Map<IElementType, TextAttributesKey>) ((HashMap) keys1).clone();
  }

  public Map<IElementType, TextAttributesKey> getKeys2() {
    return (Map<IElementType, TextAttributesKey>) ((HashMap) keys2).clone();
  }
}
