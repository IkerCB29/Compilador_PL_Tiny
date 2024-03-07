package view;

import java.io.IOException;

public interface Printer {
    void write(String clase, int fila, int columna, String lexema) throws IOException;
    void writeLexicoException(String message) throws IOException;
    void writeSintaxisException(String message) throws IOException;
    void close() throws IOException;
}
