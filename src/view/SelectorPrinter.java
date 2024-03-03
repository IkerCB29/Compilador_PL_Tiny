package view;

import java.io.IOException;

public class SelectorPrinter implements Printer{
    @Override
    public void write(String clase, int fila, int columna, String lexema) throws IOException {}

    @Override
    public void writeLexicoException(String message) {
        System.out.println(message);
    }

    @Override
    public void writeSintaxisException(String message) {
        System.out.println(message);
    }

    @Override
    public void close() {
        System.out.println("OK");
    }
}
