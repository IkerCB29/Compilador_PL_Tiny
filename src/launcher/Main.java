package launcher;

import controller.Controller;
import java.io.FileInputStream;
import java.io.Reader;
import model.lexico.BISReader;
import view.ConsolePrinter;
import view.Printer;

public class Main {
    public static void main(String[] args) {
        try {
            Controller c = new Controller();
            Reader input = new BISReader(new FileInputStream(args[0]));
            if(args[1].equals("d")){
                Printer filePrinter = new ConsolePrinter();
                c.procesamiento(
                    c.analisisSintacticoCC(input, filePrinter, false),
                    input,
                    filePrinter
                );
            }
            else if(args[1].equals("a")){
                Printer filePrinter = new ConsolePrinter();
                c.procesamiento(
                    c.analisisSintacticoCUP(input, filePrinter, false),
                    input,
                    filePrinter
                );
            }
            else throw new RuntimeException("Tipo invalido");
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
