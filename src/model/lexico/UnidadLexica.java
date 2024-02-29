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

    /*
    @Override
    public String toString() {
        return "[clase: "+ ClaseLexica.terminalNames[clase()] +", fila: "+fila()+", col: "+columna()+", lexema: "+lexema()+"]";
    }*/

    @Override
    public String toString() {
        return "[clase: "+ clase() +", fila: "+fila()+", col: "+columna()+", lexema: "+lexema()+"]";
    }
}