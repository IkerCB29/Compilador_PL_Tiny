import controller.Controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import view.ConsolePrinter;

public class DomJudge{
   public static void main(String[] args) throws Exception {
      //Este es para el DOMJUDGE
      Reader input  = new InputStreamReader(System.in);
      //Este es para probar nosotros
      //Reader input = new BufferedReader(new FileReader("files/sample1a.in"));
      Controller c = new Controller();
      char type = (char) input.read();
      if(type == 'd'){
         c.analisisSintacticoCC(input, new ConsolePrinter());
      }
      else if(type == 'a'){
         c.analisisSintacticoCUP(input, new ConsolePrinter());
      }
      else throw new RuntimeException("Tipo invalido");
   }
}
