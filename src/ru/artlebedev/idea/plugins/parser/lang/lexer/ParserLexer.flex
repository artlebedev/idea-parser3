package ru.artlebedev.idea.plugins.parser.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

%%

%{
    public _ParserLexer() {
      this((java.io.Reader) null);
    }

    public _ParserLexer(boolean highlightMode) {
      this((java.io.Reader) null);
    }
%}

%class _ParserLexer
%implements FlexLexer
%public
%unicode
%function advance
%type IElementType

Digit=[0-9]
HexDigit=[0-9A-Fa-f]
NewLine=\r|\n|\r\n
WhiteSpace=[ \t\f]

NameStart=[a-zA-Z0-9_]
Name={NameStart}(({NameStart}|-)*{NameStart})?

SharpComment="#"[^\r\n]*

Number={Integer}|{Hex}|{Float}
Integer=({Digit})*
Hex="0x"({HexDigit})*
Float=({Digit})*"."({Digit})+([Ee][+\-]?({Digit})*)?

Escape="^"([\$\^\[\](){};]|#[0-9A-Z]{2})

String={DoubleString}|{SingleString}
DoubleString=\"[^\"\n\r]*\"
SingleString='[^'\n\r]*'

RussianLetters=[à-ÿÀ-ß]

%state YYINITIAL
%xstate PARSERDOC, LINE_COMMENT

%%

<PARSERDOC> {
	[ \t]			{ yybegin(PARSERDOC); return ParserTokenTypes.NEW_LINE_INDENT; }
	"parameter"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_PARAM_KEYWORD; }
	"type"			{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_TYPE_KEYWORD; }
	"optional"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_OPTIONAL_KEYWORD; }
	"constructor"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_CONSTRUCTOR_KEYWORD; }
	{Name}			{ yybegin(PARSERDOC); return ParserTokenTypes.IDENTIFIER; }
	","			{ yybegin(PARSERDOC); return ParserTokenTypes.COMMA; }
	{NewLine}		{ yybegin(YYINITIAL); return ParserTokenTypes.NEW_LINE; }
	.			{ yybegin(PARSERDOC); return ParserTokenTypes.BAD_CHARACTER; }
}

<LINE_COMMENT> {
	.*			{ yybegin(YYINITIAL); return ParserTokenTypes.SHARP_COMMENT; }
}

<YYINITIAL> {
	{Escape}		{ yybegin(YYINITIAL); return ParserTokenTypes.ESCAPE; }
	^[ \t]+			{ yybegin(YYINITIAL); return ParserTokenTypes.NEW_LINE_INDENT; }
	{WhiteSpace}+		{ yybegin(YYINITIAL); return ParserTokenTypes.WHITE_SPACE; }
	^{NewLine}		{ yybegin(YYINITIAL); return ParserTokenTypes.NEW_LINE_INDENT; }
	{NewLine}		{ yybegin(YYINITIAL); return ParserTokenTypes.NEW_LINE; }
	^"@USE"		$	{ yybegin(YYINITIAL); return ParserTokenTypes.USE_KEYWORD; }
	^"@CLASS"	$	{ yybegin(YYINITIAL); return ParserTokenTypes.CLASS_KEYWORD; }
	^"@BASE"	$	{ yybegin(YYINITIAL); return ParserTokenTypes.BASE_KEYWORD; }
	^"@OPTIONS"	$	{ yybegin(YYINITIAL); return ParserTokenTypes.OPTIONS_KEYWORD; }
	^"locals"	$	{ yybegin(YYINITIAL); return ParserTokenTypes.LOCALS_KEYWORD; }
	^"partial"	$	{ yybegin(YYINITIAL); return ParserTokenTypes.PARTIAL_KEYWORD; }
	^"dynamic"	$	{ yybegin(YYINITIAL); return ParserTokenTypes.DYNAMIC_KEYWORD; }
	^"static"	$	{ yybegin(YYINITIAL); return ParserTokenTypes.STATIC_KEYWORD; }
	^"@"			{ yybegin(YYINITIAL); return ParserTokenTypes.KEY_AT_SIGN; }
	"@"			{ yybegin(YYINITIAL); return ParserTokenTypes.AT_SIGN; }
	^"#:"			{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_START; }
//	^{SharpComment}		{ yybegin(YYINITIAL); return ParserTokenTypes.SHARP_COMMENT; }
	^"#"			{ yybegin(LINE_COMMENT); yypushback(1); }
	"^"			{ yybegin(YYINITIAL); return ParserTokenTypes.HAT_SIGN; }
	"\$"			{ yybegin(YYINITIAL); return ParserTokenTypes.DOLLAR; }
	";"			{ yybegin(YYINITIAL); return ParserTokenTypes.SEMICOLON; }
	"("			{ yybegin(YYINITIAL); return ParserTokenTypes.LPAR; }
	")"			{ yybegin(YYINITIAL); return ParserTokenTypes.RPAR; }
	"["			{ yybegin(YYINITIAL); return ParserTokenTypes.LBRACKET; }
	"]"			{ yybegin(YYINITIAL); return ParserTokenTypes.RBRACKET; }
	"{"			{ yybegin(YYINITIAL); return ParserTokenTypes.LBRACE; }
	"}"			{ yybegin(YYINITIAL); return ParserTokenTypes.RBRACE; }
	"."			{ yybegin(YYINITIAL); return ParserTokenTypes.DOT; }
	":"			{ yybegin(YYINITIAL); return ParserTokenTypes.COLON; }
	[,=#\"'?]		{ yybegin(YYINITIAL); return ParserTokenTypes.USELESS_CHAR; }
	{RussianLetters}	{ yybegin(YYINITIAL); return ParserTokenTypes.USELESS_CHAR; }
	{String}		{ yybegin(YYINITIAL); return ParserTokenTypes.STRING_LITERAL; }
	"switch"		{ yybegin(YYINITIAL); return ParserTokenTypes.SWITCH_KEYWORD; }
	"case"			{ yybegin(YYINITIAL); return ParserTokenTypes.CASE_KEYWORD; }
	"DEFAULT"		{ yybegin(YYINITIAL); return ParserTokenTypes.DEFAULT_KEYWORD; }
	"for"			{ yybegin(YYINITIAL); return ParserTokenTypes.FOR_KEYWORD; }
	"if"			{ yybegin(YYINITIAL); return ParserTokenTypes.IF_KEYWORD; }
	"is"			{ yybegin(YYINITIAL); return ParserTokenTypes.IS_KEYWORD; }
	"result"		{ yybegin(YYINITIAL); return ParserTokenTypes.RESULT_KEYWORD; }
	"self"			{ yybegin(YYINITIAL); return ParserTokenTypes.SELF_KEYWORD; }
	"throw"			{ yybegin(YYINITIAL); return ParserTokenTypes.THROW_KEYWORD; }
	"try"			{ yybegin(YYINITIAL); return ParserTokenTypes.TRY_KEYWORD; }
	"while"			{ yybegin(YYINITIAL); return ParserTokenTypes.WHILE_KEYWORD; }
	"def"			{ yybegin(YYINITIAL); return ParserTokenTypes.DEF_KEYWORD; }
	"caller"		{ yybegin(YYINITIAL); return ParserTokenTypes.CALLER_KEYWORD; }
	"<"			{ yybegin(YYINITIAL); return ParserTokenTypes.LT; }
	">"			{ yybegin(YYINITIAL); return ParserTokenTypes.GT; }
	"<="			{ yybegin(YYINITIAL); return ParserTokenTypes.LE; }
	">="			{ yybegin(YYINITIAL); return ParserTokenTypes.GE; }
	"=="			{ yybegin(YYINITIAL); return ParserTokenTypes.EQEQ; }
	"!="			{ yybegin(YYINITIAL); return ParserTokenTypes.NE; }
	"+"			{ yybegin(YYINITIAL); return ParserTokenTypes.PLUS; }
	"-"			{ yybegin(YYINITIAL); return ParserTokenTypes.MINUS; }
	"*"			{ yybegin(YYINITIAL); return ParserTokenTypes.MULT; }
	"%"			{ yybegin(YYINITIAL); return ParserTokenTypes.PERC; }
	"<<"			{ yybegin(YYINITIAL); return ParserTokenTypes.LTLT; }
	">>"			{ yybegin(YYINITIAL); return ParserTokenTypes.GTGT; }
	"&"			{ yybegin(YYINITIAL); return ParserTokenTypes.AND; }
	"|"			{ yybegin(YYINITIAL); return ParserTokenTypes.OR; }
	"!|"			{ yybegin(YYINITIAL); return ParserTokenTypes.XOR; }
	"!"			{ yybegin(YYINITIAL); return ParserTokenTypes.EXCL; }
	"~"			{ yybegin(YYINITIAL); return ParserTokenTypes.TILDE; }
	"&&"			{ yybegin(YYINITIAL); return ParserTokenTypes.ANDAND; }
	"||"			{ yybegin(YYINITIAL); return ParserTokenTypes.OROR; }
	"/"			{ yybegin(YYINITIAL); return ParserTokenTypes.DIV; }
	"\\"			{ yybegin(YYINITIAL); return ParserTokenTypes.DIR; }
	"eq"			{ yybegin(YYINITIAL); return ParserTokenTypes.LITEQ; }
	"ne"			{ yybegin(YYINITIAL); return ParserTokenTypes.LITNE; }
	"le"			{ yybegin(YYINITIAL); return ParserTokenTypes.LITLE; }
	"ge"			{ yybegin(YYINITIAL); return ParserTokenTypes.LITGE; }
	"lt"			{ yybegin(YYINITIAL); return ParserTokenTypes.LITLT; }
	"gt"			{ yybegin(YYINITIAL); return ParserTokenTypes.LITGT; }
	{Name}			{ yybegin(YYINITIAL); return ParserTokenTypes.IDENTIFIER; }
}

	.			{ yybegin(YYINITIAL); return ParserTokenTypes.BAD_CHARACTER; }