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
    public void write(UnidadLexica unidad) throws IOException{
        writer.write(unidad.toString() + "\n");
    }

    @Override
    public void writeException(String message) throws IOException{
        writer.write(message + "\n");
    }

    @Override
    public void close() throws IOException{
        writer.close();
    }
}
