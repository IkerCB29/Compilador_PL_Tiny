package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Printer{
    private final BufferedWriter output;
    public FilePrinter(String outputRoute) throws IOException {
        output = new BufferedWriter(new FileWriter(outputRoute));
    }

    @Override
    public void write(String message) throws IOException {
        output.write(message);
    }

    public void writeLexicoException() throws IOException {
        output.write("ERROR_LEXICO\n");
    }

    @Override
    public void writeSintaxisException() throws IOException{
        output.write("ERROR_SINTACTICO\n");
    }

    @Override
    public void close() throws IOException {
        output.close();
    }
}
