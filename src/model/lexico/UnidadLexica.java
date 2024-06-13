package model.lexico;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
    public static class StringLocalizado {
        private final int fila;
        private final int col;
        private final String s;
        public StringLocalizado(String s, int fila, int col) {
            this.s = s;
            this.fila = fila;
            this.col = col;
        }
        public int fila() {return fila;}
        public int col() {return col;}
        public String str() {return s;}
    }

    public UnidadLexica(int fila, int columna, int clase, String lexema) {
        super(clase, new StringLocalizado(lexema,fila,columna));
    }

    public int clase () {return sym;}
    public int fila() {return ((StringLocalizado)value).fila();}
    public int columna() {return ((StringLocalizado)value).col();}
    public String lexema() {return ((StringLocalizado)value).str();}

    public static final String[] terminalNames = new String[] {
        "EOF",
        "error",
        "ENTERO",
        "REAL",
        "BOOLEAN",
        "STRING",
        "SUMA",
        "RESTA",
        "POR",
        "DIV",
        "MODULO",
        "AND",
        "OR",
        "NOT",
        "MENOR",
        "MAYOR",
        "MENOR_IGUAL",
        "MAYOR_IGUAL",
        "IGUAL",
        "DIFERENTE",
        "ASIGNACION",
        "PYC",
        "PAP",
        "PCIERRE",
        "LLAVE_APERTURA",
        "LLAVE_CIERRE",
        "COR_APERTURA",
        "COR_CIERRE",
        "COMA",
        "ACCESO",
        "EVALUA",
        "CAMBIO_SEC",
        "PROC",
        "IF",
        "ELSE",
        "WHILE",
        "STRUCT",
        "NEW",
        "DELETE",
        "READ",
        "WRITE",
        "NL",
        "TYPE",
        "CALL",
        "REFERENCIA",
        "PUNTERO",
        "IDENTIFICADOR",
        "LITERAL_ENTERO",
        "LITERAL_REAL",
        "LITERAL_TRUE",
        "LITERAL_FALSE",
        "LITERAL_STRING",
        "NULL"
    };
}