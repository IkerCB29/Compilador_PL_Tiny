package exceptions;

import model.lexico.UnidadLexica;

public class SintaxisException extends RuntimeException{
    private final UnidadLexica UL;

    public SintaxisException(UnidadLexica UL){
        this.UL = UL;
    }

    @Override
    public String getMessage(){
        return "[ERROR: fila: "+UL.fila()+", columna: "+UL.columna()+", Elemento inexperado: "+UL.lexema() + "]";
    }
}
