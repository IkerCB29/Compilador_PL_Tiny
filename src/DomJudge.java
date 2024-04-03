import controller.Controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import view.ConsolePrinter;
import view.FilePrinter;
import view.Printer;

public class DomJudge{
   public static void main(String[] args) throws Exception {
      //Este es para el DOMJUDGE
      //Reader input  = new InputStreamReader(System.in);
      //Este es para probar nosotros
      Reader input = new BufferedReader(new FileReader("files/sample4a.in"));
      Controller c = new Controller();
      char type = (char) input.read();
      if(type == 'a'){
         Printer output = new ConsolePrinter();
         output.write("CONSTRUCCION AST ASCENDENTE\n");
         c.analisisSintacticoCC(input, new ConsolePrinter());
      }
      else if(type == 'd'){
         Printer output = new ConsolePrinter();
         output.write("CONSTRUCCION AST DESCENDENTE\n");
         c.analisisSintacticoCUP(input, new ConsolePrinter());
      }
      else throw new RuntimeException("Tipo invalido");
   }
}
