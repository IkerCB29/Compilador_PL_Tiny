import model.sintaxis.AnalizadorSintacticoTiny;
import model.sintaxis.AnalizadorSintacticoTinyDJ;
import model.sintaxis.ParseException;
import model.sintaxis.TokenMgrError;
import java.io.FileReader;
import java.io.InputStreamReader;

public class DomJudge{
   public static void main(String[] args) throws Exception {
     try{
      AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTinyDJ(new InputStreamReader(System.in));
      asint.analiza();
     }
     catch(ParseException e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
     catch(TokenMgrError e) {
        System.out.println("ERROR_LEXICO"); 
     }
   }
}