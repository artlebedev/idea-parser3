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

DIGIT=[0-9]
HEX_DIGIT=[0-9A-Fa-f]

WHITE_SPACE_CHAR=[\ \n\r\t\f]

START_CHAR=[a-zA-Z0-9_]
NAME_CHAR=[a-zA-Z0-9_\/]

IDENTIFIER={START_CHAR}({NAME_CHAR})*

INTEGER_LITERAL={DECIMAL_INTEGER_LITERAL}|{HEX_INTEGER_LITERAL}|{DOUBLE_LITERAL}
DECIMAL_INTEGER_LITERAL=(0|([1-9]({DIGIT})*))
HEX_INTEGER_LITERAL=0x({HEX_DIGIT})*
DOUBLE_LITERAL=({DIGIT})*"."({DIGIT})+([Ee][+\-]?({DIGIT})*)?

ESCAPE="^"([\"\$\^\[\](){};:@#]|#[0-9A-Z]{2})

ENTITY_START="&"
ENTITY_CHAR=[a-zA-Z0-9_#\^]*
ENTITY_END=";"

ENTITY={ENTITY_START}{ENTITY_CHAR}{ENTITY_END}

STRING_LITERAL={DOUBLE_STRING_LITERAL}|{SINGLE_STRING_LITERAL}
DOUBLE_STRING_LITERAL=\"[^\"\n\r]*\"
SINGLE_STRING_LITERAL='[^'\n\r]*'

RUSSIAN_LETTERS=[а-яА-Я]

XMLALPHA=[:letter:]
XMLDIGIT=[0-9]
XMLTAG_NAME=({XMLALPHA}|"_"|":")({XMLALPHA}|{XMLDIGIT}|"_"|":"|"."|"-")*
XMLNAME=({XMLALPHA}|"_"|":")({XMLALPHA}|{XMLDIGIT}|"_"|":"|"."|"-")*
// THIS COULDN'T BE APPLIED TO PARSER ASWELL!!!
XMLWHITE_SPACE_CHARS=[ \n\r\t\f]+

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
	[ \t]						{ yybegin(PARSERDOC); return ParserTokenTypes.NEW_LINE_INDENT; }
	"parameter"					{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_PARAM_KEYWORD; }
	"param"						{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_PARAM_KEYWORD; }
	"result"					{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_RESULT_KEYWORD; }
	"type"						{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_TYPE_KEYWORD; }
	"optional"					{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_OPTIONAL_KEYWORD; }
	"constructor"				{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_CONSTRUCTOR_KEYWORD; }
	"dynamic"					{ yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_DYNAMIC_KEYWORD; }
	{IDENTIFIER}				{ yybegin(PARSERDOC); return ParserTokenTypes.IDENTIFIER; }
	","							{ yybegin(PARSERDOC); return ParserTokenTypes.COMMA; }
	{WHITE_SPACE_CHAR}			{ yybegin(YYINITIAL); return ParserTokenTypes.WHITE_SPACE; }
	.							{ yybegin(PARSERDOC); return ParserTokenTypes.BAD_CHARACTER; }
}

<LINE_COMMENT> {
	.*							{ yybegin(YYINITIAL); return ParserTokenTypes.SHARP_COMMENT; }
}

<YYINITIAL>	{ENTITY}			{ return ParserTokenTypes.HTML_ENTITY; }

<YYINITIAL>	{DOCTYPE} 			{ yybegin(DOC_TYPE); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE>	{WHITE_SPACE_CHAR}	{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE>	{HTML}				{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE>	{PUBLIC}			{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE>	{DTD_REF}			{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<DOC_TYPE> ">"					{ yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<YYINITIAL>	"<!--"				{ yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<COMMENT>	"["					{ yybegin(C_COMMENT_START); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<COMMENT>	"<!["				{ yybegin(C_COMMENT_END); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<COMMENT>	{END_COMMENT}		{ yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<COMMENT>	[^]					{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<C_COMMENT_START,C_COMMENT_END> {CONDITIONAL_COMMENT_CONDITION} { return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_START> [^] 			{ yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_START> "]>" 			{ yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_START,C_COMMENT_END> {END_COMMENT} { yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_END> "]" 			{ yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<C_COMMENT_END> [^] 			{ yybegin(COMMENT); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<YYINITIAL,TAG_ATTRIBUTES,ATTRIBUTE_VALUE_START,ATTRIBUTE_VALUE_DQ,ATTRIBUTE_VALUE_SQ>"<" {XMLTAG_NAME} { yybegin(TAG_ATTRIBUTES); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<YYINITIAL,TAG_ATTRIBUTES,ATTRIBUTE_VALUE_START,ATTRIBUTE_VALUE_DQ,ATTRIBUTE_VALUE_SQ>"</" {XMLTAG_NAME} { yybegin(TAG_ATTRIBUTES); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<TAG_ATTRIBUTES>	">"			{ yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<TAG_ATTRIBUTES>	"/>"		{ yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<TAG_ATTRIBUTES>	"="			{ yybegin(ATTRIBUTE_VALUE_START); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<TAG_ATTRIBUTES>	[^]			{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<ATTRIBUTE_VALUE_START> ">"		{ yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_START> "/>"	{ yybegin(YYINITIAL); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_START> "\""	{ yybegin(ATTRIBUTE_VALUE_DQ); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_START> "'"		{ yybegin(ATTRIBUTE_VALUE_SQ); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_DQ> "\""		{ yybegin(TAG_ATTRIBUTES); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_SQ> "'"		{ yybegin(TAG_ATTRIBUTES); return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_DQ> [^\"]		{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
<ATTRIBUTE_VALUE_SQ> [^']		{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }

<TAG_ATTRIBUTES,ATTRIBUTE_VALUE_START,ATTRIBUTE_VALUE_DQ,ATTRIBUTE_VALUE_SQ> {
	{ESCAPE}					{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
	^[ \t]+						{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
	{WHITE_SPACE_CHAR}+			{ return ParserTokenTypes.TEMPLATE_HTML_TEXT; }
}

<YYINITIAL>	{ESCAPE}			{ return ParserTokenTypes.ESCAPE; }
<YYINITIAL>	{WHITE_SPACE_CHAR}+	{ return ParserTokenTypes.WHITE_SPACE; }
<YYINITIAL>	^[ \t]+				{ return ParserTokenTypes.NEW_LINE_INDENT; }

<YYINITIAL>	^"@USE"		$		{ return ParserTokenTypes.USE_KEYWORD; }
<YYINITIAL>	^"@CLASS"	$		{ return ParserTokenTypes.CLASS_KEYWORD; }
<YYINITIAL>	^"@BASE"	$		{ return ParserTokenTypes.BASE_KEYWORD; }
<YYINITIAL>	^"@OPTIONS"	$		{ return ParserTokenTypes.OPTIONS_KEYWORD; }

<YYINITIAL>	^"locals"	$		{ return ParserTokenTypes.LOCALS_KEYWORD; }
<YYINITIAL>	^"partial"	$		{ return ParserTokenTypes.PARTIAL_KEYWORD; }
<YYINITIAL>	^"dynamic"	$		{ return ParserTokenTypes.DYNAMIC_KEYWORD; }
<YYINITIAL>	^"static"	$		{ return ParserTokenTypes.STATIC_KEYWORD; }

<YYINITIAL>	^"@static:"			{ return ParserTokenTypes.KEY_AT_SIGN; }
<YYINITIAL>	^"@"				{ return ParserTokenTypes.KEY_AT_SIGN; }
<YYINITIAL>	"@"					{ return ParserTokenTypes.AT_SIGN; }

<YYINITIAL>	^"#:"			    { yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_START; }
<YYINITIAL>	^"#"			    { yybegin(LINE_COMMENT); yypushback(1); }

<YYINITIAL>	"^"					{ return ParserTokenTypes.HAT_SIGN; }
<YYINITIAL>	"\$"				{ return ParserTokenTypes.DOLLAR; }
<YYINITIAL>	";"					{ return ParserTokenTypes.SEMICOLON; }
<YYINITIAL>	"("					{ return ParserTokenTypes.LPAR; }
<YYINITIAL>	")"					{ return ParserTokenTypes.RPAR; }
<YYINITIAL>	"["					{ return ParserTokenTypes.LBRACKET; }
<YYINITIAL>	"]"					{ return ParserTokenTypes.RBRACKET; }
<YYINITIAL>	"{"					{ return ParserTokenTypes.LBRACE; }
<YYINITIAL>	"}"					{ return ParserTokenTypes.RBRACE; }
<YYINITIAL>	"."					{ return ParserTokenTypes.DOT; }
<YYINITIAL>	":"					{ return ParserTokenTypes.COLON; }

<YYINITIAL>	[,=#\"'?]			{ return ParserTokenTypes.USELESS_CHAR; }
<YYINITIAL>	{RUSSIAN_LETTERS}	{ return ParserTokenTypes.USELESS_CHAR; }

<YYINITIAL>	{STRING_LITERAL}	{ return ParserTokenTypes.STRING_LITERAL; }

<YYINITIAL>	{IDENTIFIER}		{ return ParserTokenTypes.IDENTIFIER; }

<YYINITIAL>	"switch"			{ return ParserTokenTypes.SWITCH_KEYWORD; }
<YYINITIAL>	"case"				{ return ParserTokenTypes.CASE_KEYWORD; }
<YYINITIAL>	"for"				{ return ParserTokenTypes.FOR_KEYWORD; }
<YYINITIAL>	"if"				{ return ParserTokenTypes.IF_KEYWORD; }
<YYINITIAL>	"is"				{ return ParserTokenTypes.IS_KEYWORD; }
<YYINITIAL>	"result"			{ return ParserTokenTypes.RESULT_KEYWORD; }
<YYINITIAL>	"self"				{ return ParserTokenTypes.SELF_KEYWORD; }
<YYINITIAL>	"throw"				{ return ParserTokenTypes.THROW_KEYWORD; }
<YYINITIAL>	"try"				{ return ParserTokenTypes.TRY_KEYWORD; }
<YYINITIAL>	"untaint"			{ return ParserTokenTypes.UNTAINT_KEYWORD; }
<YYINITIAL>	"taint"				{ return ParserTokenTypes.TAINT_KEYWORD; }
<YYINITIAL>	"apply-taint"		{ return ParserTokenTypes.APPLY_TAINT_KEYWORD; }
<YYINITIAL>	"rem"				{ return ParserTokenTypes.REM_KEYWORD; }
<YYINITIAL>	"while"				{ return ParserTokenTypes.WHILE_KEYWORD; }
<YYINITIAL>	"def"				{ return ParserTokenTypes.DEF_KEYWORD; }
<YYINITIAL>	"caller"			{ return ParserTokenTypes.CALLER_KEYWORD; }
<YYINITIAL>	"sleep"				{ return ParserTokenTypes.SLEEP_KEYWORD; }
//<YYINITIAL>	"in"			{}

<YYINITIAL>	"CLASS"				{ return ParserTokenTypes.CLASS_STATIC_KEYWORD; }
<YYINITIAL>	"DEFAULT"			{ return ParserTokenTypes.DEFAULT_KEYWORD; }

<YYINITIAL>	"<"					{ return ParserTokenTypes.LT; }
<YYINITIAL>	">"					{ return ParserTokenTypes.GT; }
<YYINITIAL>	"<="				{ return ParserTokenTypes.LE; }
<YYINITIAL>	">="				{ return ParserTokenTypes.GE; }
<YYINITIAL>	"=="				{ return ParserTokenTypes.EQEQ; }
<YYINITIAL>	"!="				{ return ParserTokenTypes.NE; }

<YYINITIAL>	"+"					{ return ParserTokenTypes.PLUS; }
<YYINITIAL>	"-"					{ return ParserTokenTypes.MINUS; }
<YYINITIAL>	"*"					{ return ParserTokenTypes.MULT; }
<YYINITIAL>	"%"					{ return ParserTokenTypes.PERC; }

<YYINITIAL>	"<<"				{ return ParserTokenTypes.LTLT; }
<YYINITIAL>	">>"				{ return ParserTokenTypes.GTGT; }

<YYINITIAL>	"&"					{ return ParserTokenTypes.AND; }
<YYINITIAL>	"|"					{ return ParserTokenTypes.OR; }
<YYINITIAL>	"!|"				{ return ParserTokenTypes.XOR; }
<YYINITIAL>	"!"					{ return ParserTokenTypes.EXCL; }
<YYINITIAL>	"~"					{ return ParserTokenTypes.TILDE; }
<YYINITIAL>	"&&"				{ return ParserTokenTypes.ANDAND; }
<YYINITIAL>	"||"				{ return ParserTokenTypes.OROR; }
<YYINITIAL>	"/"					{ return ParserTokenTypes.DIV; }
<YYINITIAL>	"\\"				{ return ParserTokenTypes.DIR; }

<YYINITIAL>	"eq"				{ return ParserTokenTypes.LITEQ; }
<YYINITIAL>	"ne"				{ return ParserTokenTypes.LITNE; }
<YYINITIAL>	"le"				{ return ParserTokenTypes.LITLE; }
<YYINITIAL>	"ge"				{ return ParserTokenTypes.LITGE; }
<YYINITIAL>	"lt"				{ return ParserTokenTypes.LITLT; }
<YYINITIAL>	"gt"				{ return ParserTokenTypes.LITGT; }

//<YYINITIAL> "true" { }
//<YYINITIAL> "false" { }

<YYINITIAL>	.					{ return ParserTokenTypes.BAD_CHARACTER; }