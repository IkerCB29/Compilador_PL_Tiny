package launcher;

import controller.Controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import view.ConsolePrinter;

public class Main {
    public static void main(String[] args) throws Exception {
        Reader input = new BufferedReader(new FileReader(args[0]));
        Controller c = new Controller();
        input.read();
        if(args[1].equals("asc")){
            c.analisisSintacticoCUP(input, new ConsolePrinter(), args[2]);
        }
        else if(args[1].equals("desc")){
            c.analisisSintacticoCC(input, new ConsolePrinter(), args[2]);
        }
        else throw new Exception("Invalid parameters");
    }
}
