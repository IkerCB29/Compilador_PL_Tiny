package controller;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.lexico.AnalizadorLexico;
import model.lexico.ClaseLexica;
import model.lexico.UnidadLexica;
import model.lexico.UnidadLexicaErronea;

public class Controller {
    public Controller(){}
    
    public void guardarSalida(List<UnidadLexica> lexico, String fileName) throws IOException{
        File output = new File(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        for(UnidadLexica UL : lexico) {
            writer.write(UL.toString() + "\n");
        }
        writer.close();
    }

    public List<UnidadLexica> analisisLexico(String fileName) throws Exception {
    	List<UnidadLexica> lexico = new ArrayList<UnidadLexica>(); 
        Reader input = new InputStreamReader(new FileInputStream(fileName));
        AnalizadorLexico AL = new AnalizadorLexico(input);
        UnidadLexica unidad;
        do {
        	try { unidad = AL.yylex(); } 
        	catch(IllegalStateException e) {
        		unidad = new UnidadLexicaErronea(
        			AL.getFila(),
        			AL.getColumna(),
        			ClaseLexica.ERROR,
        			e.getMessage()
        		);
        	}
        	lexico.add(unidad);
        }
        while (unidad.clase() != ClaseLexica.EOF);        
    	return lexico;
    }
}

