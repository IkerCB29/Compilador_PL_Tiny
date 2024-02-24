package controller;

import java.io.*;

import exceptions.LexicoException;
import model.lexico.AnalizadorLexico;
import model.lexico.ClaseLexica;
import model.lexico.UnidadLexica;
import view.Printer;

public class Controller {
    public void analisisLexico(Reader input, Printer output) throws Exception {
        AnalizadorLexico AL = new AnalizadorLexico(input);
        UnidadLexica unidad = null;
        do {
            try {
                unidad = AL.yylex();
                output.write(unidad);
            }
            catch(LexicoException e) {
                output.writeException(e.getMessage());
            }
        }
        while (unidad == null || unidad.clase() != ClaseLexica.EOF);
        output.close();
    }
}

