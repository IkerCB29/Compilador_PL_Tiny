package model.lexico;

import java_cup.runtime.Symbol;

public abstract class UnidadLexica extends Symbol {
    private final ClaseLexica clase;
    private final int fila;
    private final int columna;

    //TODO cambiar el constructor para seguir el modelo del profe y poder unirlo al analizador sintactico
    public UnidadLexica(int fila, int columna, ClaseLexica clase) {
        super(0, "");
        this.fila = fila;
        this.columna = columna;
        this.clase = clase;
    }

    public ClaseLexica clase () {
        return clase;
    }

    public abstract String lexema();

    public int fila() {
        return fila;
    }

    public int columna() {
        return columna;
    }
}