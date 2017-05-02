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
NAME_CHAR=[a-zA-Z0-9_\/-]

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

//STRING_LITERAL={DOUBLE_STRING_LITERAL}|{SINGLE_STRING_LITERAL}
//DOUBLE_STRING_LITERAL=\"[^\^$\"\n\r]*\"
//SINGLE_STRING_LITERAL=\'[^\^$\'\n\r]*\'

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

<YYINITIAL> {

	{ESCAPE}			{ return ParserTokenTypes.ESCAPE; }
	{WHITE_SPACE_CHAR}+	{ return ParserTokenTypes.WHITE_SPACE; }
	^[ \t]+				{ return ParserTokenTypes.NEW_LINE_INDENT; }

	^"@USE"		$		{ return ParserTokenTypes.USE_KEYWORD; }
	^"@CLASS"	$		{ return ParserTokenTypes.CLASS_KEYWORD; }
	^"@BASE"	$		{ return ParserTokenTypes.BASE_KEYWORD; }
	^"@OPTIONS"	$		{ return ParserTokenTypes.OPTIONS_KEYWORD; }

	^"locals"	$		{ return ParserTokenTypes.LOCALS_KEYWORD; }
	^"partial"	$		{ return ParserTokenTypes.PARTIAL_KEYWORD; }
	^"dynamic"	$		{ return ParserTokenTypes.DYNAMIC_KEYWORD; }
	^"static"	$		{ return ParserTokenTypes.STATIC_KEYWORD; }

	^"@static:"			{ return ParserTokenTypes.KEY_AT_SIGN; }
	^"@"				{ return ParserTokenTypes.KEY_AT_SIGN; }
	"@"					{ return ParserTokenTypes.AT_SIGN; }

	^"#:"			    { yybegin(PARSERDOC); return ParserTokenTypes.PARSERDOC_START; }
	^"#"			    { yybegin(LINE_COMMENT); yypushback(1); }

	"^"					{ return ParserTokenTypes.HAT_SIGN; }
	"\$"				{ return ParserTokenTypes.DOLLAR; }
	";"					{ return ParserTokenTypes.SEMICOLON; }
	"("					{ return ParserTokenTypes.LPAR; }
	")"					{ return ParserTokenTypes.RPAR; }
	"["					{ return ParserTokenTypes.LBRACKET; }
	"]"					{ return ParserTokenTypes.RBRACKET; }
	"{"					{ return ParserTokenTypes.LBRACE; }
	"}"					{ return ParserTokenTypes.RBRACE; }
	"."					{ return ParserTokenTypes.DOT; }
	":"					{ return ParserTokenTypes.COLON; }

	[,=#\?]			    { return ParserTokenTypes.USELESS_CHAR; }
	{RUSSIAN_LETTERS}	{ return ParserTokenTypes.USELESS_CHAR; }

	[\']				{ return ParserTokenTypes.SQUOT; }
	[\"]				{ return ParserTokenTypes.QUOT; }

//	{STRING_LITERAL}	{ return ParserTokenTypes.STRING_LITERAL; }

	{IDENTIFIER}		{ return ParserTokenTypes.IDENTIFIER; }

	"switch"			{ return ParserTokenTypes.SWITCH_KEYWORD; }
	"case"				{ return ParserTokenTypes.CASE_KEYWORD; }
	"for"				{ return ParserTokenTypes.FOR_KEYWORD; }
	"if"				{ return ParserTokenTypes.IF_KEYWORD; }
	"is"				{ return ParserTokenTypes.IS_KEYWORD; }
	"result"			{ return ParserTokenTypes.RESULT_KEYWORD; }
	"self"				{ return ParserTokenTypes.SELF_KEYWORD; }
	"throw"				{ return ParserTokenTypes.THROW_KEYWORD; }
	"try"				{ return ParserTokenTypes.TRY_KEYWORD; }
	"untaint"			{ return ParserTokenTypes.UNTAINT_KEYWORD; }
	"taint"				{ return ParserTokenTypes.TAINT_KEYWORD; }
	"apply-taint"		{ return ParserTokenTypes.APPLY_TAINT_KEYWORD; }
	"rem"				{ return ParserTokenTypes.REM_KEYWORD; }
	"while"				{ return ParserTokenTypes.WHILE_KEYWORD; }
	"def"				{ return ParserTokenTypes.DEF_KEYWORD; }
	"caller"			{ return ParserTokenTypes.CALLER_KEYWORD; }
	"sleep"				{ return ParserTokenTypes.SLEEP_KEYWORD; }
	"in"				{ return ParserTokenTypes.IN_KEYWORD; }
	"-f"				{ return ParserTokenTypes.MINUSF_KEYWORD; }
	"-d"				{ return ParserTokenTypes.MINUSD_KEYWORD; }

	"CLASS"				{ return ParserTokenTypes.CLASS_STATIC_KEYWORD; }
	"DEFAULT"			{ return ParserTokenTypes.DEFAULT_KEYWORD; }

	"<"					{ return ParserTokenTypes.LT; }
	">"					{ return ParserTokenTypes.GT; }
	"<="				{ return ParserTokenTypes.LE; }
	">="				{ return ParserTokenTypes.GE; }
	"=="				{ return ParserTokenTypes.EQEQ; }
	"!="				{ return ParserTokenTypes.NE; }

	"+"					{ return ParserTokenTypes.PLUS; }
	"-"					{ return ParserTokenTypes.MINUS; }
	"*"					{ return ParserTokenTypes.MULT; }
	"%"					{ return ParserTokenTypes.PERC; }

	"<<"				{ return ParserTokenTypes.LTLT; }
	">>"				{ return ParserTokenTypes.GTGT; }

	"&"					{ return ParserTokenTypes.AND; }
	"|"					{ return ParserTokenTypes.OR; }
	"!|"				{ return ParserTokenTypes.XOR; }
	"!"					{ return ParserTokenTypes.EXCL; }
	"~"					{ return ParserTokenTypes.TILDE; }
	"&&"				{ return ParserTokenTypes.ANDAND; }
	"||"				{ return ParserTokenTypes.OROR; }
	"/"					{ return ParserTokenTypes.DIV; }
	"\\"				{ return ParserTokenTypes.DIR; }

	"eq"				{ return ParserTokenTypes.LITEQ; }
	"ne"				{ return ParserTokenTypes.LITNE; }
	"le"				{ return ParserTokenTypes.LITLE; }
	"ge"				{ return ParserTokenTypes.LITGE; }
	"lt"				{ return ParserTokenTypes.LITLT; }
	"gt"				{ return ParserTokenTypes.LITGT; }

//	"true" { }
//	"false" { }

}

.						{ return ParserTokenTypes.BAD_CHARACTER; }