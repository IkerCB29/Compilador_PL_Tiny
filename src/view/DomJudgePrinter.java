package view;

import model.lexico.UnidadLexica;

public class DomJudgePrinter implements Printer{
    @Override
    public void write(UnidadLexica unidad) {
        System.out.println(unidad.lexema());
    }

    @Override
    public void writeLexicoException(String message) {
        System.out.println("ERROR_LEXICO");
    }

    @Override
    public void writeSintaxisException(String message) {
        System.out.println("ERROR_SINTACTICO");
    }

    @Override
    public void close() {}
}
