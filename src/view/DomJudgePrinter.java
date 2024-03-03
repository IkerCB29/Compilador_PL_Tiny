package view;

import java.io.IOException;
import model.lexico.UnidadLexica;

public class DomJudgePrinter implements Printer{
    @Override
    public void write(String clase, int fila, int columna, String lexema) throws IOException {
        System.out.println(lexema);
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
