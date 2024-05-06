package model.semantica.errores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.sintaxis.SintaxisAbstracta.Nodo;
import view.Printer;

public class Errores{
    private static class Error implements Comparable<Error>{
        private final String error;
        private final int fila;
        private final int col;

        public Error(String error, int fila, int col){
            this.error = error;
            this.fila = fila;
            this.col = col;
        }

        @Override
        public String toString(){
            return error;
        }

        @Override
        public int compareTo(Error o) {
            if(this.fila == o.fila){
                return Integer.compare(this.col, o.col);
            }
            else return Integer.compare(this.fila, o.fila);
        }
    }
    protected List<Error> erroresVinculacion;
    protected List<Error> erroresPretipado;
    protected List<Error> erroresTipado;

    public Errores(){
        erroresVinculacion = new ArrayList<>();
        erroresPretipado = new ArrayList<>();
        erroresTipado = new ArrayList<>();
    }

    public void addErrorVinculacion(Nodo nodo){
        erroresVinculacion.add(new Error(
            String.format("Errores_vinculacion fila:%d col:%d", nodo.leeFila(), nodo.leeCol()),
            nodo.leeFila(),
            nodo.leeCol()
        ));
    }

    public void addErrorPretipado(Nodo nodo){
        erroresVinculacion.add(new Error(
            String.format("Errores_pretipado fila:%d col:%d", nodo.leeFila(), nodo.leeCol()),
            nodo.leeFila(),
            nodo.leeCol()
        ));
    }

    public void addErrorTipado(Nodo nodo){
        erroresTipado.add(new Error(
            String.format("Errores_tipado fila:%d col:%d", nodo.leeFila(), nodo.leeCol()),
            nodo.leeFila(),
            nodo.leeCol()
        ));
    }

    public boolean hayErroresVinculacion(){
        return !erroresVinculacion.isEmpty();
    }

    public boolean hayErroresPretipado(){
        return !erroresPretipado.isEmpty();
    }

    public boolean hayErroresTipado(){
        return !erroresTipado.isEmpty();
    }

    public void printErroresVinculacion(Printer output) throws IOException {
        Collections.sort(erroresVinculacion);
        for(Error error: erroresVinculacion){
            output.write(error + "\n");
        }
    }

    public void printErroresPretipado(Printer output) throws IOException {
        Collections.sort(erroresPretipado);
        for(Error error: erroresPretipado){
            output.write(error + "\n");
        }
    }

    public void printErroresTipado(Printer output) throws IOException {
        Collections.sort(erroresTipado);
        for(Error error: erroresTipado){
            output.write(error + "\n");
        }
    }
}
