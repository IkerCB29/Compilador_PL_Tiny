package launcher;

import controller.Controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import view.FilePrinter;

public class ProbarProcesamiento {
    public static void main(String[] args) {
        try {
            Controller c = new Controller();
            Reader input = new BufferedReader(new FileReader("files/sample7a.in"));
            c.procesamiento(input, new FilePrinter("files/sample7a.out_test"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
