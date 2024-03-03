package model.lexico;

import java_cup.runtime.Symbol;
import model.sintaxis.ClaseLexica;

public class UnidadLexica extends Symbol {
    private final int fila;
    private final int columna;
    public UnidadLexica(int fila, int columna, int clase, String lexema) {
        super(clase, lexema);
        this.fila = fila;
        this.columna = columna;
    }

    public int clase () {
        return sym;
    }

    public String lexema() { return (String) value;}

    public int fila() { return fila; }

    public int columna() { return columna; }

    public static final String[] terminalNames = new String[] {
        "EOF",
        "error",
        "ENTERO",
        "REAL",
        "BOOLEAN",
        "STRING",
        "IDENTIFICADOR",
        "LITERAL_ENTERO",
        "LITERAL_REAL",
        "LITERAL_TRUE",
        "LITERAL_FALSE",
        "LITERAL_STRING",
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
        "NULL",
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
        "PUNTERO"
    };
}