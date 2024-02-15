package launcher;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import controller.Controller;
import model.lexico.UnidadLexica;

public class Main {
	private final static String[] FILES = {
		"files/codigo1.tiny",
		"files/codigo2.tiny",
		"files/codigo3.tiny",
		"files/codigo4.tiny"
	};
	
	public static void main(String[] args) {
        Controller c = new Controller();
        for(String file : FILES) {
    		try {
				Reader input = new InputStreamReader(new FileInputStream(file));
                List<UnidadLexica> lexico = c.analisisLexico(input);
                if(lexico != null)
                	c.guardarSalida(lexico, file + "output");
    	    } 
    		catch (Exception e) {
    			e.printStackTrace();
    	    }

        }
    }
}