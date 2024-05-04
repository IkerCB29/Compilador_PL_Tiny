package model.maquinaP;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import model.sintaxis.SintaxisAbstracta;
import view.Printer;

public class MaquinaP {
    public static class EAccesoIlegitimo extends RuntimeException {}
    public static class EAccesoAMemoriaNoInicializada extends RuntimeException {
        public EAccesoAMemoriaNoInicializada(int pc,int dir) {
         super("pinst:"+pc+" dir:"+dir);
      }
    }
    public static class EAccesoFueraDeRango extends RuntimeException {}

    private GestorMemoriaDinamica gestorMemoriaDinamica;
    private GestorPilaActivaciones gestorPilaActivaciones;

    private static class Valor {
       public int valorInt() {throw new EAccesoIlegitimo();}
       public double valorReal() {throw new EAccesoIlegitimo();}
       public boolean valorBool() {throw new EAccesoIlegitimo();}
       public String valorString() {throw new EAccesoIlegitimo();}
    }
    private static class ValorInt extends Valor {
        private final int valor;
        public ValorInt(int valor) { this.valor = valor; }
        public int valorInt() { return valor; }
        public String toString() { return String.valueOf(valor); }
    }
    private static class ValorReal extends Valor {
        private final double valor;
        public ValorReal(double valor) { this.valor = valor; }
        public double valorReal() {return valor;}
        public String toString() { return String.valueOf(valor); }
    }
    private static class ValorBool extends Valor {
        private final boolean valor;
        public ValorBool(boolean valor) { this.valor = valor; }
        public boolean valorBool() {return valor;}
        public String toString() { return String.valueOf(valor); }
    }
    private static class ValorString extends Valor {
        private final String valor;
        public ValorString(String valor) {
            if(valor.length() >= 2)
                this.valor = valor.substring(1, valor.length() - 1);
            else
                this.valor = valor;
        }
        public String valorString() {return valor;}
        public String toString() { return String.valueOf(valor); }
    }

    private final List<Instruccion> codigoP;
    private final Stack<Valor> pilaEvaluacion;
    private final Valor[] datos;
    private int pc;

    public interface Instruccion {
        void ejecuta();
    }

    private class IApilaInt implements Instruccion {
        private int valor;
        public IApilaInt(int valor) {
            this.valor = valor;
        }
        public void ejecuta() {
            pilaEvaluacion.push(new ValorInt(valor));
            pc++;
        }
        public String toString() {return "apila-int("+valor+")";};
    }

    private class IApilaReal implements Instruccion {
        private double valor;
        public IApilaReal(double valor) {
            this.valor = valor;
        }
        public void ejecuta() {
            pilaEvaluacion.push(new ValorReal(valor));
            pc++;
        }
        public String toString() {return "apila-real("+valor+")";};
    }

    private class IApilaBool implements Instruccion {
        private boolean valor;
        public IApilaBool(boolean valor) {
            this.valor = valor;
        }
        public void ejecuta() {
            pilaEvaluacion.push(new ValorBool(valor));
            pc++;
        }
        public String toString() {return "apila-bool("+valor+")";};
    }

    private class IApilaString implements Instruccion {
        private String valor;
        public IApilaString(String valor) {
            this.valor = valor;
        }
        public void ejecuta() {
            pilaEvaluacion.push(new ValorString(valor));
            pc++;
        }
        public String toString() {return "apila-string("+valor+")";};
    }

    private IDesapila IDESAPILA;
    private class IDesapila implements Instruccion {
        public IDesapila() {}
        public void ejecuta() {
            pilaEvaluacion.pop();
            pc++;
        }
        public String toString() {
            return "desapila";
        }
    }

    private IApilaInd IAPILAIND;
    private class IApilaInd implements Instruccion {
        public void ejecuta() {
            int dir = pilaEvaluacion.pop().valorInt();
            if (dir >= datos.length) throw new EAccesoFueraDeRango();
            if (datos[dir] == null)  throw new EAccesoAMemoriaNoInicializada(pc,dir);
            pilaEvaluacion.push(datos[dir]);
            pc++;
        }
        public String toString() {return "apila-ind";};
    }

    private IDesapilaInd IDESAPILAIND;
    private class IDesapilaInd implements Instruccion {
        public void ejecuta() {
            Valor valor = pilaEvaluacion.pop();
            int dir = pilaEvaluacion.pop().valorInt();
            if (dir >= datos.length) throw new EAccesoFueraDeRango();
            datos[dir] = valor;
            pc++;
        }
        public String toString() {return "desapila-ind";};
    }

    private class IApilaD implements Instruccion {
        private int nivel;
        public IApilaD(int nivel) {
            this.nivel = nivel;
        }
        public void ejecuta() {
            pilaEvaluacion.push(new ValorInt(gestorPilaActivaciones.display(nivel)));
            pc++;
        }
        public String toString() {
            return "apilad("+nivel+")";
        }

    }

    private class IDesapilad implements Instruccion {
        private int nivel;
        public IDesapilad(int nivel) {
            this.nivel = nivel;
        }
        public void ejecuta() {
            gestorPilaActivaciones.fijaDisplay(nivel,pilaEvaluacion.pop().valorInt());
            pc++;
        }
        public String toString() {
            return "desapilad("+nivel+")";
        }
    }

    private class ICopia implements Instruccion {
        private int tam;
        public ICopia(int tam) {
            this.tam = tam;
        }
        public void ejecuta() {
            int dirOrigen = pilaEvaluacion.pop().valorInt();
            int dirDestino = pilaEvaluacion.pop().valorInt();
            if ((dirOrigen + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            if ((dirDestino + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            for (int i=0; i < tam; i++)
                datos[dirDestino+i] = datos[dirOrigen+i];
            pc++;
        }
        public String toString() {return "copia("+tam+")";};
    }

    private class ICopiaTransformando implements Instruccion {

        public void ejecuta() {
            int dirOrigen = pilaEvaluacion.pop().valorInt();
            int dirDestino = pilaEvaluacion.pop().valorInt();
            if ((dirOrigen) >= datos.length)
                throw new EAccesoFueraDeRango();
            if ((dirDestino) >= datos.length)
                throw new EAccesoFueraDeRango();
            datos[dirDestino] = new ValorReal((double) datos[dirOrigen].valorInt());
            pc++;
        }
        public String toString() {return "copia-transformado()";};
    }

    private class IIrA implements Instruccion {
        private int dir;
        public IIrA(int dir) {
            this.dir = dir;
        }
        public void ejecuta() {
            pc=dir;
        }
        public String toString() {return "ir-a("+dir+")";};
    }

    private class IIrF implements Instruccion {
        private int dir;
        public IIrF(int dir) {
            this.dir = dir;
        }
        public void ejecuta() {
            if(!pilaEvaluacion.pop().valorBool()) {
                pc=dir;
            }
            else {
                pc++;
            }
        }
        public String toString() {return "ir-f("+dir+")";};
    }

    private Instruccion IIRIND;
    private class IIrind implements Instruccion {
        public void ejecuta() {
            pc = pilaEvaluacion.pop().valorInt();
        }
        public String toString() {
            return "ir-ind";
        }
    }

    private class IAlloc implements Instruccion {
        private int tam;
        public IAlloc(int tam) {
            this.tam = tam;
        }
        public void ejecuta() {
            int inicio = gestorMemoriaDinamica.alloc(tam);
            pilaEvaluacion.push(new ValorInt(inicio));
            pc++;
        }
        public String toString() {return "alloc("+tam+")";};
    }

    private class IDealloc implements Instruccion {
        private int tam;
        public IDealloc(int tam) {
            this.tam = tam;
        }
        public void ejecuta() {
            int inicio = pilaEvaluacion.pop().valorInt();
            gestorMemoriaDinamica.free(inicio,tam);
            pc++;
        }
        public String toString() {return "dealloc("+tam+")";};
    }

    private class IActiva implements Instruccion {
        private int nivel;
        private int tamdatos;
        private int dirretorno;
        public IActiva(int nivel, int tamdatos, int dirretorno) {
            this.nivel = nivel;
            this.tamdatos = tamdatos;
            this.dirretorno = dirretorno;
        }
        public void ejecuta() {
            int base = gestorPilaActivaciones.creaRegistroActivacion(tamdatos);
            datos[base] = new ValorInt(dirretorno);
            datos[base+1] = new ValorInt(gestorPilaActivaciones.display(nivel));
            pilaEvaluacion.push(new ValorInt(base+2));
            pc++;
        }
        public String toString() {
            return "activa("+nivel+","+tamdatos+","+dirretorno+")";
        }
    }

    private class IDesactiva implements Instruccion {
        private int nivel;
        private int tamdatos;
        public IDesactiva(int nivel, int tamdatos) {
            this.nivel = nivel;
            this.tamdatos = tamdatos;
        }
        public void ejecuta() {
            int base = gestorPilaActivaciones.liberaRegistroActivacion(tamdatos);
            gestorPilaActivaciones.fijaDisplay(nivel,datos[base+1].valorInt());
            pilaEvaluacion.push(datos[base]);
            pc++;
        }
        public String toString() {
            return "desactiva("+nivel+","+tamdatos+")";
        }
    }

    private IDup IDUP;
    private class IDup implements Instruccion {
        public void ejecuta() {
            pilaEvaluacion.push(pilaEvaluacion.peek());
            pc++;
        }
        public String toString() {
            return "dup";
        }
    }

    private IStop ISTOP;
    private class IStop implements Instruccion {
        public void ejecuta() {
            pc = codigoP.size();
        }
        public String toString() {
            return "stop";
        }
    }

    private IReadInt IREADINT;
    private class IReadInt implements Instruccion{
        public void ejecuta() {
            pilaEvaluacion.push(new ValorInt(input.nextInt()));
            pc++;
        }
        public String toString() {
            return "read-int";
        }
    }

    private IReadReal IREADREAL;
    private class IReadReal implements Instruccion{
        public void ejecuta() {
            pilaEvaluacion.push(new ValorReal(input.nextDouble()));
            pc = codigoP.size();
        }
        public String toString() {
            return "read-real";
        }
    }

    private IReadBool IREADBOOL;
    private class IReadBool implements Instruccion{
        public void ejecuta() {
            pilaEvaluacion.push(new ValorBool(input.nextBoolean()));
            pc++;
        }
        public String toString() {
            return "read-bool";
        }
    }

    private IReadString IREADSTRING;
    private class IReadString implements Instruccion{
        public void ejecuta() {
            pilaEvaluacion.push(new ValorString(input.nextLine()));
            pc++;
        }
        public String toString() {
            return "read-string";
        }
    }

    private IWrite IWRITE;
    private class IWrite implements Instruccion{
        public void ejecuta() {
            try {
                output.write(pilaEvaluacion.pop().toString());
            } catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
            pc++;
        }
        public String toString() {
            return "write";
        }
    }

    private ITransformaInt ITRANSFORMAINT;
    private class ITransformaInt implements Instruccion{
        public void ejecuta() {
            Valor valInt = pilaEvaluacion.pop();
            pilaEvaluacion.push(new ValorReal(valInt.valorInt()));
        }
        public String toString(){
            return "transforma-int";
        }
    }

    private Imy IMY;
    private class Imy implements Instruccion{
        public void ejecuta() {
            Valor opnd2 = pilaEvaluacion.pop();
            Valor opnd1 = pilaEvaluacion.pop();
            if(claseDe(opnd1, ValorInt.class) && claseDe(opnd2, ValorInt.class))
                pilaEvaluacion.push(new ValorBool(opnd1.valorInt() > opnd2.valorInt()));
            else if(claseDe(opnd1, ValorReal.class) && claseDe(opnd2, ValorInt.class))
                throw new RuntimeException("Real > Entero Error");
            else if(claseDe(opnd1, ValorInt.class) && claseDe(opnd2, ValorReal.class))
                throw new RuntimeException("Entero > Real Error");
            else if(claseDe(opnd1, ValorReal.class) && claseDe(opnd2, ValorReal.class))
                pilaEvaluacion.push(new ValorBool(opnd1.valorReal() > opnd2.valorReal()));
            else if(claseDe(opnd1, ValorBool.class) && claseDe(opnd2, ValorBool.class)){
                if(opnd1.valorBool() && opnd2.valorBool())
                    pilaEvaluacion.push(new ValorBool(false));
                else if(opnd1.valorBool() && !opnd2.valorBool())
                    pilaEvaluacion.push(new ValorBool(true));
                else if(!opnd1.valorBool() && opnd2.valorBool())
                    pilaEvaluacion.push(new ValorBool(false));
                else
                    pilaEvaluacion.push(new ValorBool(false));
            }
            else if(claseDe(opnd1, ValorString.class) && claseDe(opnd2, ValorString.class))
                if(opnd1.valorString().compareTo(opnd2.valorString()) == 0 || opnd1.valorString().compareTo(opnd2.valorString()) > 0){
                    pilaEvaluacion.push(new ValorBool(false));
                }
                else {
                    pilaEvaluacion.push(new ValorBool(true));
                }
            pc++;
        }
        public String toString(){
            return "my";
        }
    }

    private Imn IMN;
    private class Imn implements Instruccion{
        public void ejecuta() {
            Valor opnd2 = pilaEvaluacion.pop();
            Valor opnd1 = pilaEvaluacion.pop();
            if(claseDe(opnd1, ValorInt.class) && claseDe(opnd2, ValorInt.class))
                pilaEvaluacion.push(new ValorBool(opnd1.valorInt() < opnd2.valorInt()));
            else if(claseDe(opnd1, ValorReal.class) && claseDe(opnd2, ValorInt.class))
                throw new RuntimeException("Real < Entero Error");
            else if(claseDe(opnd1, ValorInt.class) && claseDe(opnd2, ValorReal.class))
                throw new RuntimeException("Entero < Real Error");
            else if(claseDe(opnd1, ValorReal.class) && claseDe(opnd2, ValorReal.class))
                pilaEvaluacion.push(new ValorBool(opnd1.valorReal() < opnd2.valorReal()));
            else if(claseDe(opnd1, ValorBool.class) && claseDe(opnd2, ValorBool.class)){
                if(opnd1.valorBool() && opnd2.valorBool())
                    pilaEvaluacion.push(new ValorBool(false));
                else if(opnd1.valorBool() && !opnd2.valorBool())
                    pilaEvaluacion.push(new ValorBool(false));
                else if(!opnd1.valorBool() && opnd2.valorBool())
                    pilaEvaluacion.push(new ValorBool(true));
                else
                    pilaEvaluacion.push(new ValorBool(false));
            }
            else if(claseDe(opnd1, ValorString.class) && claseDe(opnd2, ValorString.class))
                pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString()) < 0));
            pc++;
        }
        public String toString(){
            return "my";
        }
    }
    //============================================================================================

    private ISuma ISUMA;
    private class ISuma implements Instruccion {
        public void ejecuta() {
            Valor opnd2 = pilaEvaluacion.pop();
            Valor opnd1 = pilaEvaluacion.pop();
            if(claseDe(opnd1, ValorInt.class) && claseDe(opnd2, ValorInt.class))
                pilaEvaluacion.push(new ValorInt(opnd1.valorInt()+opnd2.valorInt()));
            else if(claseDe(opnd1, ValorReal.class) && claseDe(opnd2, ValorInt.class))
                throw new RuntimeException("Real + Entero Error");
            else if(claseDe(opnd1, ValorInt.class) && claseDe(opnd2, ValorReal.class))
                throw new RuntimeException("Entero + Real Error");
            else if(claseDe(opnd1, ValorReal.class) && claseDe(opnd2, ValorReal.class))
                pilaEvaluacion.push(new ValorReal(opnd1.valorReal()+opnd2.valorReal()));
            pc++;
        }
        public String toString() {return "suma";};
    }

    private IMul IMUL;
    private class IMul implements Instruccion {
        public void ejecuta() {
            Valor opnd2 = pilaEvaluacion.pop();
            Valor opnd1 = pilaEvaluacion.pop();
            if(claseDe(opnd1, ValorInt.class) && claseDe(opnd2, ValorInt.class))
                pilaEvaluacion.push(new ValorInt(opnd1.valorInt()*opnd2.valorInt()));
            else if(claseDe(opnd1, ValorReal.class) && claseDe(opnd2, ValorInt.class))
                throw new RuntimeException("Real * Entero Error");
            else if(claseDe(opnd1, ValorInt.class) && claseDe(opnd2, ValorReal.class))
                throw new RuntimeException("Entero * Real Error");
            else if(claseDe(opnd1, ValorReal.class) && claseDe(opnd2, ValorReal.class))
                pilaEvaluacion.push(new ValorReal(opnd1.valorReal()*opnd2.valorReal()));
            pc++;
        }
        public String toString() {return "mul";};
    }


    private IAnd IAND;
    private class IAnd implements Instruccion {
        public void ejecuta() {
            Valor opnd2 = pilaEvaluacion.pop();
            Valor opnd1 = pilaEvaluacion.pop();
            pilaEvaluacion.push(new ValorBool(opnd1.valorBool() && opnd2.valorBool()));
            pc++;
        }
        public String toString() {return "and";};
    }

    public Instruccion apila_int(int valor) { return new IApilaInt(valor); }
    public Instruccion apila_real(double valor) { return new IApilaReal(valor); }
    public Instruccion apila_bool(boolean valor) { return new IApilaBool(valor); }
    public Instruccion apila_string(String valor) { return new IApilaString(valor); }
    public Instruccion desapila() { return IDESAPILA; }
    public Instruccion apila_ind() { return IAPILAIND; }
    public Instruccion desapila_ind() { return IDESAPILAIND; }
    public Instruccion apilad(int nivel) { return new IApilaD(nivel); }
    public Instruccion desapilad(int nivel) { return new IDesapilad(nivel); }
    public Instruccion copia(int size) { return new ICopia(size); }
    public Instruccion copia_transformando() { return new ICopiaTransformando(); }
    public Instruccion ir_a(int dir) { return new IIrA(dir); }
    public Instruccion ir_f(int dir) { return new IIrF(dir); }
    public Instruccion ir_ind() { return IIRIND; }
    public Instruccion alloc(int size) { return new IAlloc(size); }
    public Instruccion dealloc(int size) { return new IDealloc(size); }
    public Instruccion activa(int nivel, int size, int retorno) { return new IActiva(nivel, size, retorno); }
    public Instruccion desactiva(int nivel, int size) { return new IDesactiva(nivel, size); }
    public Instruccion dup() { return IDUP; }
    public Instruccion stop() { return ISTOP; }
    public Instruccion read_int() { return IREADINT; }
    public Instruccion read_real() { return IREADREAL; }
    public Instruccion read_bool() { return IREADBOOL; }
    public Instruccion read_string() { return IREADSTRING; }
    public Instruccion write() { return IWRITE; }
    public Instruccion transforma_int() { return null; }
    public Instruccion my() { return null; }
    public Instruccion mn() { return null; }
    public Instruccion my_ig() { return null; }
    public Instruccion mn_ig() { return null; }
    public Instruccion ig() { return null; }
    public Instruccion dif() { return null; }
    public Instruccion suma() { return null; }
    public Instruccion resta() { return null; }
    public Instruccion and() { return null; }
    public Instruccion or() { return null; }
    public Instruccion mul() { return null; }
    public Instruccion div() { return null; }
    public Instruccion mod() { return null; }
    public Instruccion menos_unario() { return null; }
    public Instruccion not() { return null; }

    public void emit(Instruccion i) {
      codigoP.add(i); 
   }

    private final int tamdatos;
    private final int tamheap;
    private final int ndisplays;
    private final Scanner input;
    private final Printer output;

    public MaquinaP(Reader input, Printer output, int tamdatos, int tampila, int tamheap, int ndisplays) {
       this.input = new Scanner(input);
       this.output = output;
       this.tamdatos = tamdatos;
       this.tamheap = tamheap;
       this.ndisplays = ndisplays;
       this.codigoP = new ArrayList<>();
       pilaEvaluacion = new Stack<>();
       datos = new Valor[tamdatos+tampila+tamheap];
       this.pc = 0;
       gestorPilaActivaciones = new GestorPilaActivaciones(tamdatos,(tamdatos+tampila)-1,ndisplays);
       gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos+tampila,(tamdatos+tampila+tamheap)-1);
    }
    public void ejecuta() {
        while(pc != codigoP.size()) {
            codigoP.get(pc).ejecuta();
        }
    }

    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }
}
