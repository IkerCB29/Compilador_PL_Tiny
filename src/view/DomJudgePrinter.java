package view;

import model.lexico.UnidadLexica;

import java.io.IOException;

public class DomJudgePrinter implements Printer{
    @Override
    public void write(UnidadLexica unidad) {
        switch(unidad.clase()) {
            case IDENTIFICADOR: case LITERAL_ENTERO: case LITERAL_REAL: case LITERAL_STRING: System.out.println(unidad.lexema()); break;
            default: System.out.println(unidad.clase().getImage());
        }
    }

    @Override
    public void writeException(String message) {
        System.out.println("ERROR");
    }

    @Override
    public void close() {}
}
