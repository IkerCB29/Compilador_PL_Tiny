package view;

import model.lexico.UnidadLexica;

import java.io.IOException;

public interface Printer {
    void write(UnidadLexica UL) throws IOException;
    void writeException(String message) throws IOException;
    void close() throws IOException;
}
