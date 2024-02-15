import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import controller.Controller;
import model.lexico.UnidadLexica;

public class DomJudge {
	private static void imprime(UnidadLexica unidad) {
		switch(unidad.clase()) {
		   case IDENTIFICADOR: case LITERAL_ENTERO: case LITERAL_REAL: case LITERAL_STRING: System.out.println(unidad.lexema()); break;
		   default: System.out.println(unidad.clase().getImage());
		}
	}	

   public static void main(String[] args) throws Exception {
	   //Este es para el DOMJUDGE
	   Reader input  = new InputStreamReader(System.in);
	   //Este es para probar nosotros
	   //Reader input = new BufferedReader(new FileReader("files/codigo4.tiny"));
	   Controller c = new Controller();
	   for(UnidadLexica UL : c.analisisLexico(input)) {
		   imprime(UL);
	   }
   }
} 
