import controller.Controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import model.lexico.AnalizadorLexico;
import model.sintaxis.ConstructorASTsCC;
import model.sintaxis.ConstructorASTsCUP;
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
      Reader input = new InputStreamReader(new FileInputStream("files/codigo1.tiny"));
      ConstructorASTsCC asint = new ConstructorASTsCC(input);
      asint.disable_tracing();
      String str = asint.analiza().toString();
      int numTabs = 0;
      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if(ch == ')') {
            numTabs--;
            System.out.print('\n');
            for (int j = 0; j < numTabs; j++) {
               System.out.print('\t');
            }
         }
         System.out.print(ch);
         if(ch == '(' ) {
            numTabs++;
            if(str.charAt(i + 1) != ')') {
               System.out.print('\n');
               for (int j = 0; j < numTabs; j++) {
                  System.out.print('\t');
               }
            }
         }
      }
   }

   public static void ASTS_CUP() throws Exception{
      Reader input = new InputStreamReader(new FileInputStream("files/codigo1.tiny"));
      AnalizadorLexico alex = new AnalizadorLexico(input);
      ConstructorASTsCUP asint = new ConstructorASTsCUP(alex);
      String str = asint.parse().value.toString();
      int numTabs = 0;
      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if(ch == ')') {
            numTabs--;
            System.out.print('\n');
            for (int j = 0; j < numTabs; j++) {
               System.out.print('\t');
            }
         }
         System.out.print(ch);
         if(ch == '(' ) {
            numTabs++;
            if(str.charAt(i + 1) != ')') {
               System.out.print('\n');
               for (int j = 0; j < numTabs; j++) {
                  System.out.print('\t');
               }
            }
         }
      }
   }
}
