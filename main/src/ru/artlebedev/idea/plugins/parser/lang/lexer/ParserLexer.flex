package ru.artlebedev.idea.plugins.parser.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlTokenType;

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
Name={NameStart}(({NameStart}|-|\/)*{NameStart})?

EntityStart="&"
EntityChar=[a-zA-Z0-9_#\^]*
EntityEnd=";"

Entity={EntityStart}{EntityChar}{EntityEnd}

SharpComment="#"[^\r\n]*

Number={Integer}|{Hex}|{Float}
Integer=({Digit})*
Hex="0x"({HexDigit})*
Float=({Digit})*"."({Digit})+([Ee][+\-]?({Digit})*)?

Escape="^"([\"\$\^\[\](){};:@#]|#[0-9A-Z]{2})

String={DoubleString}|{SingleString}
DoubleString=\"[^\"\n\r]*\"
SingleString='[^'\n\r]*'

XMLALPHA=[:letter:]
XMLDIGIT=[0-9]
XMLTAG_NAME=({XMLALPHA}|"_"|":")({XMLALPHA}|{XMLDIGIT}|"_"|":"|"."|"-")*
XMLNAME=({XMLALPHA}|"_"|":")({XMLALPHA}|{XMLDIGIT}|"_"|":"|"."|"-")*
// THIS COULDN'T BE APPLIED TO PARSER ASWELL!!!
XMLWHITE_SPACE_CHARS=[ \n\r\t\f]+

RussianLetters=[а-яА-Я]

DTD_REF= "\"" [^\"]* "\"" | "'" [^']* "'"
DOCTYPE= "<!" (D|d)(O|o)(C|c)(T|t)(Y|y)(P|p)(E|e)
HTML= (H|h)(T|t)(M|m)(L|l)
PUBLIC= (P|p)(U|u)(B|b)(L|l)(I|i)(C|c)

END_COMMENT="--"[ \n\r\t\f]*">"

CONDITIONAL_COMMENT_CONDITION=({XMLALPHA})({XMLALPHA}|{XMLWHITE_SPACE_CHARS}|{XMLDIGIT}|"."|"("|")"|"|"|"!"|"&")*

%state YYINITIAL
%state DOC_TYPE
%state COMMENT
%state TAG_ATTRIBUTES
%state ATTRIBUTE_VALUE_START
%state ATTRIBUTE_VALUE_DQ
%state ATTRIBUTE_VALUE_SQ
%state C_COMMENT_START
%state C_COMMENT_END
%xstate PARSERDOC, LINE_COMMENT

%%

<PARSERDOC> {
	[ \t]			{ yybegin(PARSERDOC); return ParserTokenTypes.NEW_LINE_INDENT; }
	"parameter"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_PARAM_KEYWORD; }
	"param"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_PARAM_KEYWORD; }
	"result"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_RESULT_KEYWORD; }
	"type"			{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_TYPE_KEYWORD; }
	"optional"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_OPTIONAL_KEYWORD; }
	"constructor"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_CONSTRUCTOR_KEYWORD; }
	"dynamic"		{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_DYNAMIC_KEYWORD; }
	{Name}			{ yybegin(PARSERDOC); return ParserTokenTypes.IDENTIFIER; }
	","			{ yybegin(PARSERDOC); return ParserTokenTypes.COMMA; }
	{NewLine}		{ yybegin(YYINITIAL); return ParserTokenTypes.NEW_LINE; }
	.			{ yybegin(PARSERDOC); return ParserTokenTypes.BAD_CHARACTER; }
}

<LINE_COMMENT> {
	.*			{ yybegin(YYINITIAL); return ParserTokenTypes.SHARP_COMMENT; }
}

<YYINITIAL> {Entity} { return ParserTokenTypes.HTML_ENTITY; }


<YYINITIAL> {DOCTYPE} { yybegin(DOC_TYPE); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE> {WhiteSpace} { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE> {HTML} { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE> {PUBLIC} { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE> {DTD_REF} { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE> ">" { yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<YYINITIAL> "<!--" { yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<COMMENT> "[" { yybegin(C_COMMENT_START); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<COMMENT> "<![" { yybegin(C_COMMENT_END); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<COMMENT> {END_COMMENT} { yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<COMMENT> [^] { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<C_COMMENT_START,C_COMMENT_END> {CONDITIONAL_COMMENT_CONDITION} { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_START> [^] { yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_START> "]>" { yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_START,C_COMMENT_END> {END_COMMENT} { yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_END> "]" { yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_END> [^] { yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<YYINITIAL,TAG_ATTRIBUTES,ATTRIBUTE_VALUE_START,ATTRIBUTE_VALUE_DQ,ATTRIBUTE_VALUE_SQ>"<" {XMLTAG_NAME} { yybegin(TAG_ATTRIBUTES); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<YYINITIAL,TAG_ATTRIBUTES,ATTRIBUTE_VALUE_START,ATTRIBUTE_VALUE_DQ,ATTRIBUTE_VALUE_SQ>"</" {XMLTAG_NAME} { yybegin(TAG_ATTRIBUTES); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<TAG_ATTRIBUTES> ">" { yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<TAG_ATTRIBUTES> "/>" { yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<TAG_ATTRIBUTES> "=" { yybegin(ATTRIBUTE_VALUE_START); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<TAG_ATTRIBUTES> [^] { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<ATTRIBUTE_VALUE_START> ">" { yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_START> "/>" { yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_START> "\"" { yybegin(ATTRIBUTE_VALUE_DQ); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_START> "'" { yybegin(ATTRIBUTE_VALUE_SQ); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_DQ> "\"" { yybegin(TAG_ATTRIBUTES); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_SQ> "'" { yybegin(TAG_ATTRIBUTES); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_DQ> [^\"] { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_SQ> [^'] { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<TAG_ATTRIBUTES,ATTRIBUTE_VALUE_START,ATTRIBUTE_VALUE_DQ,ATTRIBUTE_VALUE_SQ> {
	{Escape}		{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
	^[ \t]+			{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
  {WhiteSpace}+		{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
	^{NewLine}		{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
	{NewLine}		{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
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
	^"@static:"			{ yybegin(YYINITIAL); return ParserTokenTypes.KEY_AT_SIGN; }
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
	"CLASS"			{ yybegin(YYINITIAL); return ParserTokenTypes.CLASS_STATIC_KEYWORD; }
	"throw"			{ yybegin(YYINITIAL); return ParserTokenTypes.THROW_KEYWORD; }
	"try"			{ yybegin(YYINITIAL); return ParserTokenTypes.TRY_KEYWORD; }
	"untaint"			{ yybegin(YYINITIAL); return ParserTokenTypes.UNTAINT_KEYWORD; }
	"taint"			{ yybegin(YYINITIAL); return ParserTokenTypes.TAINT_KEYWORD; }
	"apply-taint"			{ yybegin(YYINITIAL); return ParserTokenTypes.APPLY_TAINT_KEYWORD; }
	"rem"			{ yybegin(YYINITIAL); return ParserTokenTypes.REM_KEYWORD; }
	"while"			{ yybegin(YYINITIAL); return ParserTokenTypes.WHILE_KEYWORD; }
	"def"			{ yybegin(YYINITIAL); return ParserTokenTypes.DEF_KEYWORD; }
	"caller"		{ yybegin(YYINITIAL); return ParserTokenTypes.CALLER_KEYWORD; }
	"sleep"			{ yybegin(YYINITIAL); return ParserTokenTypes.SLEEP_KEYWORD; }
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