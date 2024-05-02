package model.maquinaP;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        public ValorReal(int valor) { this.valor = valor; }
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
        public ValorString(String valor) { this.valor = valor; }
        public String valorString() {return valor;}
        public String toString() { return valor; }
    }

   private final List<Instruccion> codigoP;
   private final Stack<Valor> pilaEvaluacion;
   private final Valor[] datos;
   private int pc;

   public interface Instruccion {
      void ejecuta();  
   }

    public Instruccion apila_int(int valor) { return null; }
    public Instruccion apila_real(double valor) { return null; }
    public Instruccion apila_bool(boolean valor) { return null; }
    public Instruccion apila_string(String valor) { return null; }
    public Instruccion desapila() { return null; }
    public Instruccion apila_ind() { return null; }
    public Instruccion desapila_ind() { return null; }
    public Instruccion apilad(int nivel) { return null; }
    public Instruccion desapilad(int nivel) { return null; }
    public Instruccion copia(int size) { return null; }
    public Instruccion copia_transformando() { return null; }
    public Instruccion ir_a(int dir) { return null; }
    public Instruccion ir_f(int dir) { return null; }
    public Instruccion ir_ind() { return null; }
    public Instruccion alloc(int size) { return null; }
    public Instruccion dealloc(int size) { return null; }
    public Instruccion activa(int nivel, int size, int retorno) { return null; }
    public Instruccion desactiva(int nivel, int size) { return null; }
    public Instruccion dup() { return null; }
    public Instruccion stop() { return null; }
    public Instruccion read_int() { return null; }
    public Instruccion read_real() { return null; }
    public Instruccion read_bool() { return null; }
    public Instruccion read_string() { return null; }
    public Instruccion write() { return null; }
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
   public MaquinaP(int tamdatos, int tampila, int tamheap, int ndisplays) {
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
}
