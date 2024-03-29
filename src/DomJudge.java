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
      //Reader input = new BufferedReader(new FileReader("files/codigo10.tiny"));
      Controller c = new Controller();
      c.analisisSintacticoCC(input, new ConsolePrinter(), "vis");
   }
}
