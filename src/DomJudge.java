import controller.Controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import model.lexico.BISReader;
import view.ConsolePrinter;

public class DomJudge{
   public static void main(String[] args) throws Exception {
      //Este es para el DOMJUDGE
      Reader input  = new BISReader(System.in);
      //Este es para probar nosotros
      //Reader input = new BISReader(new FileInputStream("files/Procesamiento/09ejemplo_a.in"));
      Controller c = new Controller();
      char type = (char) input.read();
      if(type == 'd'){
         c.procesamientoCC(input, new ConsolePrinter());
      }
      else if(type == 'a'){
         c.procesamientoCUP(input, new ConsolePrinter());
      }
      else throw new RuntimeException("Tipo invalido");
   }
}
