import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import model.lexico.UnidadLexica;
import model.lexico.UnidadLexicaErronea;
import model.lexico.AnalizadorLexico;
import model.lexico.ClaseLexica;;

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
	   AnalizadorLexico al = new AnalizadorLexico(input);
	   UnidadLexica unidad;
       do {
       	try { unidad = al.yylex(); } 
       	catch(IllegalStateException e) {
       		unidad = new UnidadLexicaErronea(
       			al.getFila(),
       			al.getColumna(),
       			ClaseLexica.ERROR,
       			e.getMessage()
       		);
       	}
       	imprime(unidad);
       }
       while (unidad.clase() != ClaseLexica.EOF);   
   }
} 
