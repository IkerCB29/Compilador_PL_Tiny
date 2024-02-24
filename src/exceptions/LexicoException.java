package exceptions;

public class LexicoException extends Exception{
    private final int fila;
    private final int columna;
    private final String lexema;

    public LexicoException(int fila, int columna, String lexema){
        this.fila = fila;
        this.columna = columna;
        this.lexema = lexema;
    }

    @Override
    public String getMessage(){
        return "[ERROR: "+"fila:"+fila+", col:"+columna+", Caracter inexperado: "+lexema+"]";
    }
}
