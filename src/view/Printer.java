package view;

import model.lexico.UnidadLexica;

import java.io.IOException;

public interface Printer {
    void write(String message) throws IOException;
    void close() throws IOException;
}
