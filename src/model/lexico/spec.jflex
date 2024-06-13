package model.lexico;

import model.sintaxis.ClaseLexica;
import builder.UnidadLexicaBuilder;

import exceptions.LexicoException;
import java_cup.runtime.Scanner;

%%
%line
%column
%class AnalizadorLexico
%public
%type UnidadLexica
%unicode
%cup

%{
  private UnidadLexicaBuilder ULBuilder;
  public String getLexema() {return yytext();}
  public int getFila() {return yyline+1;}
  public int getColumna() {return yycolumn+1;}
%}

%eofval{
  return ULBuilder.construirUL(ClaseLexica.EOF, "<EOF>");
%eofval}

%init{
  ULBuilder = new UnidadLexicaBuilder(this);
%init}

%yylexthrow{
  LexicoException
%yylexthrow}

letra = ([A-Z]|[a-z]|_)
digitoPositivo = [1-9]
digito = {digitoPositivo}|0
parteEntera = ({digitoPositivo}{digito}*|0)
parteDecimal = \.({digito}*{digitoPositivo}|0)
exponencial = (e|E)[\+,\-]?{parteEntera}
comboPartes = {parteDecimal}{exponencial}

entero = (i|I)(n|N)(t|T)
real = (r|R)(e|E)(a|A)(l|L)
boolean = (b|B)(o|O)(o|O)(l|L)
string = (s|S)(t|T)(r|R)(i|I)(n|N)(g|G)
true = (t|T)(r|R)(u|U)(e|E)
false = (f|F)(a|A)(l|L)(s|S)(e|E)
null = (n|N)(u|U)(l|L)(l|L)
proc = (p|P)(r|R)(o|O)(c|C)
if = (i|I)(f|F)
else = (e|E)(l|L)(s|S)(e|E)
while = (w|W)(h|H)(i|I)(l|L)(e|E)
struct = (s|S)(t|T)(r|R)(u|U)(c|C)(t|T)
new = (n|N)(e|E)(w|W)
delete = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)
read = (r|R)(e|E)(a|A)(d|D)
write = (w|W)(r|R)(i|I)(t|T)(e|E)
nl = (n|N)(l|L)
type = (t|T)(y|Y)(p|P)(e|E)
call = (c|C)(a|A)(l|L)(l|L)
and = (a|A)(n|N)(d|D)
or = (o|O)(r|R)
not = (n|N)(o|O)(t|T)
identificador = ({letra})({letra}|{digito})*
numeroEntero = [\+\-]?{parteEntera}
numeroReal = {numeroEntero}({parteDecimal}|{exponencial}|{comboPartes})
literalString = \"[^\"]*\"
suma = \+
resta = \-
mul = \*
div = \/
modulo = %
menorQue = \<
mayorQue = \>
menorOIgualQue = \<\=
mayorOIgualQue = \>\=
igual = \=\=
diferente = \!\=
asignacion= \=
pyc = \;
parApertura = \(
parCierre = \)
corcheteApertura = \[
corcheteCierre = \]
llaveApertura = \{
llaveCierre = \}
acceso = \.
coma = \,
finDeclaracion = \&\&
evalua = \@
puntero = \^
referencia = \&
separador = [ \t\r\b\n]
comentario = \#\#[^\n]*
eof = \$

%%
{entero}				{return ULBuilder.construirUL(ClaseLexica.ENTERO, "<int>");}
{real}					{return ULBuilder.construirUL(ClaseLexica.REAL, "<real>");}
{boolean}				{return ULBuilder.construirUL(ClaseLexica.BOOLEAN, "<bool>");}
{string}				{return ULBuilder.construirUL(ClaseLexica.STRING, "<string>");}
{true}					{return ULBuilder.construirUL(ClaseLexica.LITERAL_TRUE, "<true>");}
{false}					{return ULBuilder.construirUL(ClaseLexica.LITERAL_FALSE, "<false>");}
{null}					{return ULBuilder.construirUL(ClaseLexica.NULL, "<null>");}
{proc}					{return ULBuilder.construirUL(ClaseLexica.PROC, "<proc>");}
{if}					{return ULBuilder.construirUL(ClaseLexica.IF, "<if>");}
{else}					{return ULBuilder.construirUL(ClaseLexica.ELSE, "<else>");}
{while}					{return ULBuilder.construirUL(ClaseLexica.WHILE, "<while>");}
{struct}				{return ULBuilder.construirUL(ClaseLexica.STRUCT, "<struct>");}
{new}					{return ULBuilder.construirUL(ClaseLexica.NEW, "<new>");}
{delete}				{return ULBuilder.construirUL(ClaseLexica.DELETE, "<delete>");}
{read}					{return ULBuilder.construirUL(ClaseLexica.READ, "<read>");}
{write}					{return ULBuilder.construirUL(ClaseLexica.WRITE, "<write>");}
{nl}					{return ULBuilder.construirUL(ClaseLexica.NL, "<nl>");}
{type}					{return ULBuilder.construirUL(ClaseLexica.TYPE, "<type>");}
{call}					{return ULBuilder.construirUL(ClaseLexica.CALL, "<call>");}
{and}					{return ULBuilder.construirUL(ClaseLexica.AND, "<and>");}
{or}					{return ULBuilder.construirUL(ClaseLexica.OR, "<or>");}
{not}					{return ULBuilder.construirUL(ClaseLexica.NOT, "<not>");}
{identificador}			{return ULBuilder.construirUL(ClaseLexica.IDENTIFICADOR, this.getLexema());}
{numeroEntero}			{return ULBuilder.construirUL(ClaseLexica.LITERAL_ENTERO, this.getLexema());}
{numeroReal}			{return ULBuilder.construirUL(ClaseLexica.LITERAL_REAL, this.getLexema());}
{literalString}			{return ULBuilder.construirUL(ClaseLexica.LITERAL_STRING, this.getLexema());}
{suma}					{return ULBuilder.construirUL(ClaseLexica.SUMA, "+");}
{resta}					{return ULBuilder.construirUL(ClaseLexica.RESTA, "-");}
{mul}					{return ULBuilder.construirUL(ClaseLexica.POR, "*");}
{div}					{return ULBuilder.construirUL(ClaseLexica.DIV, "/");}
{modulo}				{return ULBuilder.construirUL(ClaseLexica.MODULO, "%");}
{menorQue}				{return ULBuilder.construirUL(ClaseLexica.MENOR, "<");}
{mayorQue}				{return ULBuilder.construirUL(ClaseLexica.MAYOR, ">");}
{menorOIgualQue}		{return ULBuilder.construirUL(ClaseLexica.MENOR_IGUAL, "<=");}
{mayorOIgualQue}		{return ULBuilder.construirUL(ClaseLexica.MAYOR_IGUAL, ">=");}
{igual} 				{return ULBuilder.construirUL(ClaseLexica.IGUAL, "==");}
{diferente} 			{return ULBuilder.construirUL(ClaseLexica.DIFERENTE, "!=");}
{asignacion}			{return ULBuilder.construirUL(ClaseLexica.ASIGNACION, "=");}
{pyc}					{return ULBuilder.construirUL(ClaseLexica.PYC, ";");}
{parApertura}			{return ULBuilder.construirUL(ClaseLexica.PAP, "(");}
{parCierre}				{return ULBuilder.construirUL(ClaseLexica.PCIERRE, ")");}
{corcheteApertura}		{return ULBuilder.construirUL(ClaseLexica.COR_APERTURA, "[");}
{corcheteCierre}		{return ULBuilder.construirUL(ClaseLexica.COR_CIERRE, "]");}
{llaveApertura}			{return ULBuilder.construirUL(ClaseLexica.LLAVE_APERTURA, "{");}
{llaveCierre}			{return ULBuilder.construirUL(ClaseLexica.LLAVE_CIERRE, "}");}
{acceso} 				{return ULBuilder.construirUL(ClaseLexica.ACCESO, ".");}
{coma} 					{return ULBuilder.construirUL(ClaseLexica.COMA, ",");}
{finDeclaracion} 		{return ULBuilder.construirUL(ClaseLexica.CAMBIO_SEC, "&&");}
{evalua}				{return ULBuilder.construirUL(ClaseLexica.EVALUA, "@");}
{puntero}				{return ULBuilder.construirUL(ClaseLexica.PUNTERO, "^");}
{referencia}			{return ULBuilder.construirUL(ClaseLexica.REFERENCIA, "&");}
{eof}           {return ULBuilder.construirUL(ClaseLexica.EOF, "$");}
{separador}         	{}
{comentario}        	{}
[^]                 	{throw new LexicoException(getFila(), getColumna(), getLexema());}


