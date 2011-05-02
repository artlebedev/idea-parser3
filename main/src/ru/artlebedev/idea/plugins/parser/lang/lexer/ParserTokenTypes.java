package ru.artlebedev.idea.plugins.parser.lang.lexer;


import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * idea-parser3: the most advanced parser3 ide.
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

public interface ParserTokenTypes {
  IElementType TEMPLATE_HTML_TEXT = new ParserElementType("PARSER_TEMPLATE_HTML_TEXT");
  IElementType OUTER_ELEMENT_TYPE = new ParserElementType("PARSER_FRAGMENT");

  IElementType HTML_ENTITY = new ParserElementType("HTML_ENTITY");

  IElementType IDENTIFIER = new ParserElementType("IDENTIFIER");
  IElementType NEW_LINE_INDENT = TokenType.NEW_LINE_INDENT;
  IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
  IElementType NEW_LINE = new ParserElementType("NEW_LINE");
  IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
  IElementType USELESS_CHAR = new ParserElementType("USELESS_CHAR");

  IElementType EOF = new ParserElementType("EOF");

  IElementType SHARP_COMMENT = new ParserElementType("SHARP_COMMENT");

  // ParserDoc
  IElementType PARSERDOC_TYPE_KEYWORD = new ParserElementType("PARSERDOC_TYPE_KEYWORD");
  IElementType PARSERDOC_PARAM_KEYWORD = new ParserElementType("PARSERDOC_PARAM_KEYWORD");
  IElementType PARSERDOC_RESULT_KEYWORD = new ParserElementType("PARSERDOC_RESULT_KEYWORD");
  IElementType PARSERDOC_OPTIONAL_KEYWORD = new ParserElementType("PARSERDOC_OPTIONAL_KEYWORD");
  IElementType PARSERDOC_CONSTRUCTOR_KEYWORD = new ParserElementType("PARSERDOC_CONSTRUCTOR_KEYWORD");
  IElementType PARSERDOC_DYNAMIC_KEYWORD = new ParserElementType("PARSERDOC_DYNAMIC_KEYWORD");
  IElementType PARSERDOC_START = new ParserElementType("PARSERDOC_START");

  // Keywords:
  IElementType SWITCH_KEYWORD = new ParserElementType("SWITCH_KEYWORD");
  IElementType CASE_KEYWORD = new ParserElementType("CASE_KEYWORD");
  IElementType DEFAULT_KEYWORD = new ParserElementType("DEFAULT_KEYWORD");
  IElementType FOR_KEYWORD = new ParserElementType("FOR_KEYWORD");
  IElementType IF_KEYWORD = new ParserElementType("IF_KEYWORD");
  IElementType IS_KEYWORD = new ParserElementType("IS_KEYWORD");
  IElementType RESULT_KEYWORD = new ParserElementType("RESULT_KEYWORD");
  IElementType SELF_KEYWORD = new ParserElementType("SELF_KEYWORD");
  IElementType CALLER_KEYWORD = new ParserElementType("CALLER_KEYWORD");
  IElementType CLASS_STATIC_KEYWORD = new ParserElementType("CLASS_STATIC_KEYWORD");
  IElementType THROW_KEYWORD = new ParserElementType("THROW_KEYWORD");
  IElementType TRY_KEYWORD = new ParserElementType("TRY_KEYWORD");
  IElementType WHILE_KEYWORD = new ParserElementType("WHILE_KEYWORD");
  IElementType BASE_KEYWORD = new ParserElementType("BASE_KEYWORD");
  IElementType CLASS_KEYWORD = new ParserElementType("CLASS_KEYWORD");
  IElementType USE_KEYWORD = new ParserElementType("USE_KEYWORD");
  IElementType DEF_KEYWORD = new ParserElementType("DEF_KEYWORD");
  IElementType TAINT_KEYWORD = new ParserElementType("TAINT_KEYWORD");
  IElementType UNTAINT_KEYWORD = new ParserElementType("UNTAINT_KEYWORD");
  IElementType REM_KEYWORD = new ParserElementType("REM_KEYWORD");

  /*
   * Supported in Parser 3.3.0
   */
  IElementType OPTIONS_KEYWORD = new ParserElementType("OPTIONS_KEYWORD");
  IElementType LOCALS_KEYWORD = new ParserElementType("LOCALS_KEYWORD");
  IElementType PARTIAL_KEYWORD = new ParserElementType("PARTIAL_KEYWORD");

  /*
   * Supported in Parser 3.4.1
   */
  IElementType DYNAMIC_KEYWORD = new ParserElementType("DYNAMIC_KEYWORD");
  IElementType STATIC_KEYWORD = new ParserElementType("STATIC_KEYWORD");

  // Hardcoded literals
  IElementType TRUE_KEYWORD = new ParserElementType("TRUE_KEYWORD");
  IElementType FALSE_KEYWORD = new ParserElementType("FALSE_KEYWORD");

  TokenSet KEYWORDS = TokenSet.create(
          BASE_KEYWORD, CLASS_KEYWORD,
          CASE_KEYWORD, DEFAULT_KEYWORD,
          FOR_KEYWORD, IF_KEYWORD,
          SWITCH_KEYWORD, THROW_KEYWORD,
          TRY_KEYWORD, WHILE_KEYWORD,
          TRUE_KEYWORD, FALSE_KEYWORD,
          IS_KEYWORD, USE_KEYWORD,
          CALLER_KEYWORD, OPTIONS_KEYWORD,
          LOCALS_KEYWORD, PARTIAL_KEYWORD,
          DYNAMIC_KEYWORD, STATIC_KEYWORD,
          TAINT_KEYWORD, UNTAINT_KEYWORD,
          REM_KEYWORD);

  // Literals
  IElementType NUMERIC_LITERAL = new ParserElementType("NUMERIC_LITERAL");
  IElementType STRING_LITERAL = new ParserElementType("STRING_LITERAL");
  IElementType ESCAPE = new ParserElementType("ESCAPE");

  // Punctuators
  IElementType LBRACE = new ParserElementType("LBRACE");// {
  IElementType RBRACE = new ParserElementType("RBRACE");// }
  IElementType LPAR = new ParserElementType("LPAR");// (
  IElementType RPAR = new ParserElementType("RPAR");// )
  IElementType LBRACKET = new ParserElementType("LBRACKET");// [
  IElementType RBRACKET = new ParserElementType("RBRACKET");// ]
  IElementType DOT = new ParserElementType("DOT");// .
  IElementType COLON = new ParserElementType("COLON");//:
  IElementType SEMICOLON = new ParserElementType("SEMICOLON");// ;
  IElementType AT_SIGN = new ParserElementType("AT_SIGN"); // @
  IElementType KEY_AT_SIGN = new ParserElementType("KEY_AT_SIGN"); // @ at the beginning of the line
  IElementType DOLLAR = new ParserElementType("DOLLAR"); // $
  IElementType HAT_SIGN = new ParserElementType("HAT_SIGN"); //^
  IElementType COMMA = new ParserElementType("COMMA"); //,

  IElementType LT = new ParserElementType("LT");// <
  IElementType GT = new ParserElementType("GT");// >
  IElementType LE = new ParserElementType("LE");// <=
  IElementType GE = new ParserElementType("GE");// >=
  IElementType EQEQ = new ParserElementType("EQEQ");// ==
  IElementType NE = new ParserElementType("NE");// !=
  IElementType PLUS = new ParserElementType("PLUS");// +
  IElementType MINUS = new ParserElementType("MINUS");// -
  IElementType MULT = new ParserElementType("MULT");// *
  IElementType PERC = new ParserElementType("PERC");// %
  IElementType LTLT = new ParserElementType("LTLT");// <<
  IElementType GTGT = new ParserElementType("GTGT");// >>
  IElementType AND = new ParserElementType("AND");// &
  IElementType OR = new ParserElementType("OR");// |
  IElementType XOR = new ParserElementType("XOR");// !|
  IElementType EXCL = new ParserElementType("EXCL");// !
  IElementType TILDE = new ParserElementType("TILDE");// ~
  IElementType ANDAND = new ParserElementType("ANDAND");// &&
  IElementType OROR = new ParserElementType("OROR");// ||
  IElementType DIV = new ParserElementType("DIV"); // /
  IElementType DIR = new ParserElementType("DIR"); // \
  IElementType LITEQ = new ParserElementType("LITEQ");
  IElementType LITNE = new ParserElementType("LITNE");
  IElementType LITLE = new ParserElementType("LITLE");
  IElementType LITGE = new ParserElementType("LITGE");
  IElementType LITLT = new ParserElementType("LITLT");
  IElementType LITGT = new ParserElementType("LITGT");

  TokenSet OPERATIONS = TokenSet.create(
          LT, GT, LE, GE, EQEQ, NE, PLUS, MINUS, MULT, PERC, LTLT, GTGT, AND, OR,
          XOR, EXCL, TILDE, ANDAND, OROR, DIV, LITEQ, LITNE, LITLE, LITGE,
          LITLT, LITGT, IS_KEYWORD, DEF_KEYWORD
  );

  TokenSet ASSOC_OPERATIONS = TokenSet.create(PLUS, MULT, AND, OR, XOR, OROR, ANDAND);

  TokenSet EQUALITY_OPERATIONS = TokenSet.create(
          EQEQ, NE, LITEQ, LITNE
  );

  TokenSet RELATIONAL_OPERATIONS = TokenSet.create(
          LT, GT, LE, GE, IS_KEYWORD, DEF_KEYWORD, LITGE, LITGT, LITLE, LITLT
  );

  TokenSet ADDITIVE_OPERATIONS = TokenSet.create(
          PLUS, MINUS
  );

  TokenSet MULTIPLICATIVE_OPERATIONS = TokenSet.create(
          MULT, DIV, PERC
  );

  TokenSet SHIFT_OPERATIONS = TokenSet.create(
          LTLT, GTGT
  );

  TokenSet UNARY_OPERATIONS = TokenSet.create(
          PLUS, MINUS, TILDE, EXCL
  );

  TokenSet COMMENTS = TokenSet.create(
          SHARP_COMMENT
  );

  TokenSet STRING_LITERALS = TokenSet.create(
          STRING_LITERAL
  );

  /**
   * characters that could be in an include string
   */
  TokenSet PATH_CHARACTERS = TokenSet.create(
          DOT, DIV, DIR, EXCL
  );
  /**
   * possible opening braces : ( [ {
   */
  TokenSet OPENING_BRACES = TokenSet.create(
          LBRACE, LBRACKET, LPAR
  );
  /**
   * possible closing braces : ) ] }
   */
  TokenSet CLOSING_BRACES = TokenSet.create(
          RBRACE, RBRACKET, RPAR
  );

  TokenSet METHOD_DELIMITERS = TokenSet.create(
          EOF, KEY_AT_SIGN
  );

}

