package model.sintaxis;

import java.io.IOException;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

import model.lexico.UnidadLexica;
import view.Printer;

public class AnalizadorSintacticoCUP extends AsinCUP {
    private final Printer output;
    public void debug_message(String msg) {}
    public void debug_shift(Symbol token) {
        try {
            UnidadLexica aux = (UnidadLexica) token;
            output.write(UnidadLexica.terminalNames[aux.clase()], aux.fila(), aux.columna(), aux.lexema());
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public AnalizadorSintacticoCUP(Scanner alex, Printer output) {
        super(alex);
        this.output = output;
    }
}

