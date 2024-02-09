package model.lexico;
import builder.UnidadLexicaMultivaluadaBuilder;
import builder.UnidadLexicaUnivaluadaBuilder;

%%
%line
%column
%class AnalizadorLexico
%type UnidadLexica
%unicode

%{
  private UnidadLexicaUnivaluadaBuilder uniULBuilder;
  private UnidadLexicaMultivaluadaBuilder multiULBuilder;
  public String getLexema() {return yytext();}
  public int getFila() {return yyline+1;}
  public int getColumna() {return yycolumn+1;}
%}

%eofval{
  return uniULBuilder.construirUL(ClaseLexica.EOF);
%eofval}

%init{
  uniULBuilder = new UnidadLexicaUnivaluadaBuilder(this);
  multiULBuilder = new UnidadLexicaMultivaluadaBuilder(this);
%init}

letra = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = {digitoPositivo}|0
parteEntera = (\+|\-)?(({digitoPositivo}{digito}*)|0)
parteDecimal = \.(({digito}*{digitoPositivo})|0)
parteExponencial = (e|E)(\+|\-)?{parteEntera}
comboPartes = {parteDecimal}{parteExponencial}

entero = (i|I)(n|N)(t|T)
real = (r|R)(e|E)(a|A)(l|L)
boolean = (b|B)(o|O)(o|O)(l|L)
string = (s|S)(t|T)(r|R)(i|I)(n|N)(g|G)
true = (t|T)(r|R)(u|U)(e|E)
false = (f|F)(a|A)(l|L)(s|S)(e|E)
null = (n|N)(u|U)(l|L)(l|L)
proc = (p|P)(r|R)(o|O)(c|C)
if = (i|I) (f|F)
else = (e|E)(l|L)(s|S)(e|E)
while = (w|W)(h|H)(i|I)(l|L)(e|E)
struct = (s|S)(t|T)(r|R)(u|U)(c|C)(t|T)
new = (n|N)(e|E )(w|W)
delete = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)
read = (r|R)(e|E)(a|A)(d|D)
write = (w|W)(r|R)(i|I)(t|T)(e|E)
nl = (n|N )(l|L)
type = (t|T)(y|Y)(p|P)(e|E)
call = (c|C)(a|A)(l|L)(l|L)
and = (a|A)(n|N)(d|D)
or = (o|O)(r|R)
not = (n|N)(o|O)(t|T)
identificador = ({letra}|_)({letra}|{digito}|_)*
numeroEntero = {parteEntera}
numeroReal = {parteEntera}({parteDecimal}|{parteExponencial}|{comboPartes})
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
comentario = ##[^\n]*

%%
{entero}				{return uniULBuilder.construirUL(ClaseLexica.ENTERO);}
{real}					{return uniULBuilder.construirUL(ClaseLexica.REAL);}
{boolean}				{return uniULBuilder.construirUL(ClaseLexica.BOOLEAN);}
{string}				{return uniULBuilder.construirUL(ClaseLexica.STRING);}
{true}					{return uniULBuilder.construirUL(ClaseLexica.LITERAL_TRUE);}
{false}					{return uniULBuilder.construirUL(ClaseLexica.LITERAL_FALSE);}
{null}					{return uniULBuilder.construirUL(ClaseLexica.NULL);}
{proc}					{return uniULBuilder.construirUL(ClaseLexica.PROC);}
{if}					{return uniULBuilder.construirUL(ClaseLexica.IF);}
{else}					{return uniULBuilder.construirUL(ClaseLexica.ELSE);}
{while}					{return uniULBuilder.construirUL(ClaseLexica.WHILE);}
{struct}				{return uniULBuilder.construirUL(ClaseLexica.STRUCT);}
{new}					{return uniULBuilder.construirUL(ClaseLexica.NEW);}
{delete}				{return uniULBuilder.construirUL(ClaseLexica.DELETE);}
{read}					{return uniULBuilder.construirUL(ClaseLexica.READ);}
{write}					{return uniULBuilder.construirUL(ClaseLexica.WRITE);}
{nl}					{return uniULBuilder.construirUL(ClaseLexica.NL);}
{type}					{return uniULBuilder.construirUL(ClaseLexica.TYPE);}
{call}					{return uniULBuilder.construirUL(ClaseLexica.CALL);}
{and}					{return uniULBuilder.construirUL(ClaseLexica.AND);}
{or}					{return uniULBuilder.construirUL(ClaseLexica.OR);}
{not}					{return uniULBuilder.construirUL(ClaseLexica.NOT);}
{identificador}			{return multiULBuilder.construirUL(ClaseLexica.IDENTIFICADOR);}
{numeroEntero}			{return multiULBuilder.construirUL(ClaseLexica.LITERAL_ENTERO);}
{numeroReal}			{return multiULBuilder.construirUL(ClaseLexica.LITERAL_REAL);}
{literalString}			{return multiULBuilder.construirUL(ClaseLexica.LITERAL_STRING);}
{suma}					{return uniULBuilder.construirUL(ClaseLexica.SUMA);}
{resta}					{return uniULBuilder.construirUL(ClaseLexica.RESTA);}
{mul}					{return uniULBuilder.construirUL(ClaseLexica.MULTIPLICACION);}
{div}					{return uniULBuilder.construirUL(ClaseLexica.DIVISION);}
{modulo}				{return uniULBuilder.construirUL(ClaseLexica.MODULO);}
{menorQue}				{return uniULBuilder.construirUL(ClaseLexica.MENOR);}
{mayorQue}				{return uniULBuilder.construirUL(ClaseLexica.MAYOR);}
{menorOIgualQue}		{return uniULBuilder.construirUL(ClaseLexica.MENOR_IGUAL);}
{mayorOIgualQue}		{return uniULBuilder.construirUL(ClaseLexica.MAYOR_IGUAL);}
{igual} 				{return uniULBuilder.construirUL(ClaseLexica.IGUAL);}
{diferente} 			{return uniULBuilder.construirUL(ClaseLexica.DIFERENTE);}
{asignacion}			{return uniULBuilder.construirUL(ClaseLexica.ASIGNACION);}
{pyc}					{return uniULBuilder.construirUL(ClaseLexica.PYC);}
{parApertura}			{return uniULBuilder.construirUL(ClaseLexica.PAR_APERTURA);}
{parCierre}				{return uniULBuilder.construirUL(ClaseLexica.PAR_CIERRE);}
{corcheteApertura}		{return uniULBuilder.construirUL(ClaseLexica.COR_APERTURA);}
{corcheteCierre}		{return uniULBuilder.construirUL(ClaseLexica.COR_CIERRE);}
{llaveApertura}			{return uniULBuilder.construirUL(ClaseLexica.LLAVE_APERTURA);}
{llaveCierre}			{return uniULBuilder.construirUL(ClaseLexica.LLAVE_CIERRE);}
{acceso} 				{return uniULBuilder.construirUL(ClaseLexica.ACCESO);}
{coma} 					{return uniULBuilder.construirUL(ClaseLexica.COMA);}
{finDeclaracion} 		{return uniULBuilder.construirUL(ClaseLexica.CAMBIO_SEC);}
{evalua}				{return uniULBuilder.construirUL(ClaseLexica.EVALUA);}
{puntero}				{return uniULBuilder.construirUL(ClaseLexica.PUNTERO);}
{referencia}			{return uniULBuilder.construirUL(ClaseLexica.REFERENCIA);}
{separador}         	{}
{comentario}        	{}
[^]                 	{throw new IllegalStateException("Caracter invalido");}  


