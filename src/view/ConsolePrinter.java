package view;

import java.io.IOException;

public class ConsolePrinter implements Printer{
    @Override
    public void write(String message) throws IOException {
        System.out.print(message);
    }

    public void writeLexicoException() {
        System.out.println("ERROR_LEXICO");
    }

    @Override
    public void writeSintaxisException() {
        System.out.println("ERROR_SINTACTICO");
    }

    @Override
    public void close() {}
}
