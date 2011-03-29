package ru.artlebedev.idea.plugins.parser.utils;

import com.intellij.psi.tree.IElementType;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 29.03.11
 * Time: 14:56
 */
public class ParserParserUtil {
  public static boolean isIdentifierToken(IElementType tokenType) {
    if(tokenType == ParserTokenTypes.IDENTIFIER ||
       tokenType == ParserTokenTypes.RESULT_KEYWORD ||
       tokenType == ParserTokenTypes.SELF_KEYWORD ||
       tokenType == ParserTokenTypes.CALLER_KEYWORD) {
      return true;
    } else {
      return false;
    }
  }
}
