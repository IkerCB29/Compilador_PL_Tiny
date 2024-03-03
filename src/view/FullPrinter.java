package view;

import model.lexico.UnidadLexica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FullPrinter implements Printer{
    private final BufferedWriter writer;

    public FullPrinter(String output) throws IOException {
        writer = new BufferedWriter(new FileWriter(output));
    }
    @Override
    public void write(String clase, int fila, int columna, String lexema) throws IOException{
        writer.write("[clase: "+ clase +", fila: "+fila+", col: "+columna+", lexema: "+lexema+"]\n");
    }

    @Override
    public void writeLexicoException(String message) throws IOException{
        writer.write(message + "\n");
    }

    @Override
    public void writeSintaxisException(String message) throws IOException {
        writer.write(message + "\n");
    }

    @Override
    public void close() throws IOException{
        writer.close();
    }
}
