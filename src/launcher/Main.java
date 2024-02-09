package launcher;

import java.util.List;

import controller.Controller;
import model.lexico.UnidadLexica;

public class Main {
	private final static String[] FILES = {
		"files/codigo1.tiny",
		"files/codigo2.tiny",
		"files/codigo3.tiny"
	};
	
	public static void main(String[] args) {
        Controller c = new Controller();
        
        for(String file : FILES) {
    		try {
                List<UnidadLexica> lexico = c.analisisLexico(file);
                if(lexico != null)
                	c.guardarSalida(lexico, file + "output");
    	    } 
    		catch (Exception e) {
    			e.printStackTrace();
    	    }

        }
    }
}