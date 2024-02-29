package controller;

import exceptions.SintaxisException;
import java.io.*;

import exceptions.LexicoException;
import model.lexico.AnalizadorLexico;
import model.lexico.UnidadLexica;
import model.sintaxis.AnalizadorSintacticoCUP;
import model.sintaxis.ClaseLexica;
import view.DomJudgePrinter;
import view.Printer;

public class Controller {
    public void analisisLexico(Reader input, Printer output) throws Exception {
        AnalizadorLexico alex = new AnalizadorLexico(input);
        UnidadLexica unidad = null;
        do {
            try {
                unidad = alex.next_token();
                output.write(unidad);
            }
            catch(LexicoException e) {
                output.writeLexicoException(e.getMessage());
            }
        }
        while (unidad == null || unidad.clase() != ClaseLexica.EOF);
        output.close();
    }

    public void analisisSintacticoCUP(Reader input) throws Exception {
        try {
            AnalizadorLexico alex = new AnalizadorLexico(input);
            AnalizadorSintacticoCUP asin = new AnalizadorSintacticoCUP(alex);
            asin.parse();
            System.out.println("Analisis sintáctico sin problemas");
        }
        catch (LexicoException e){
            System.out.println(e.getMessage());
        }
        catch (SintaxisException e){
            System.out.println(e.getMessage());
        }
    }

    public void analisisSintacticoCUPDebug(Reader input) throws Exception {
        Printer output = new DomJudgePrinter();
        try {
            AnalizadorLexico alex = new AnalizadorLexico(input);
            AnalizadorSintacticoCUP asin = new AnalizadorSintacticoCUP(alex);
            asin.debug_parse();
        }
        catch (LexicoException e){
            output.writeLexicoException(e.getMessage());
        }
        catch (SintaxisException e){
            output.writeSintaxisException(e.getMessage());
        }
    }
}

