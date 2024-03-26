import controller.Controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import model.lexico.AnalizadorLexico;
import model.sintaxis.ConstructorASTsCC;
import model.sintaxis.ConstructorASTsCUP;
import model.sintaxis.SintaxisAbstracta.Prog;
import model.sintaxis.impresionRecursiva.ImpresionBonitaRecursiva;
import model.sintaxis.impresionVisitante.ImpresionBonitaVisitante;
import view.DomJudgePrinter;

public class DomJudge{
   public static void main(String[] args) throws Exception {
      ASTS_CC();
   }

   public static void DomJUDGE() throws Exception{
      //Este es para el DOMJUDGE
      //Reader input  = new InputStreamReader(System.in);
      //Este es para probar nosotros
      Reader input = new BufferedReader(new FileReader("files/codigo11.tiny"));
      Controller c = new Controller();
      c.analisisSintacticoCC(input, new DomJudgePrinter());
   }

   public static void ASTS_CC() throws Exception{
      Reader input = new InputStreamReader(new FileInputStream("files/codigo11.tiny"));
      ImpresionBonitaVisitante output = new ImpresionBonitaVisitante(System.out);
      ImpresionBonitaRecursiva output2 = new ImpresionBonitaRecursiva(System.out);
      ConstructorASTsCC asint = new ConstructorASTsCC(input);
      asint.disable_tracing();
      //output.procesa(asint.analiza());
      output2.imprime(asint.analiza());
   }

   public static void ASTS_CUP() throws Exception{
      Reader input = new InputStreamReader(new FileInputStream("files/codigo10.tiny"));
      ImpresionBonitaRecursiva output = new ImpresionBonitaRecursiva(System.out);
      AnalizadorLexico alex = new AnalizadorLexico(input);
      ConstructorASTsCUP asint = new ConstructorASTsCUP(alex);
      output.imprime((Prog)asint.parse().value);
   }
}
