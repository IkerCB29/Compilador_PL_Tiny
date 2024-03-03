package launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import controller.Controller;
import view.SelectorPrinter;

public class Selector {
    public static void main(String[] args) throws Exception{
        Reader input = new BufferedReader(new FileReader(args[0]));
        Controller c = new Controller();
        if(args[1].equals("desc"))
            c.analisisSintacticoCC(input, new SelectorPrinter());
        else if(args[1].equals("asc"))
            c.analisisSintacticoCUP(input, new SelectorPrinter());
        else
            throw new Exception("Opcion invalida");
        input.close();
    }
}