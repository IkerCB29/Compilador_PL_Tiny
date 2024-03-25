package controller;

import exceptions.SintaxisException;
import java.io.*;

import exceptions.LexicoException;
import model.lexico.AnalizadorLexico;
import model.lexico.UnidadLexica;
import model.sintaxis.AnalizadorSintacticoCC;
import model.sintaxis.AnalizadorSintacticoCUP;
import model.sintaxis.ClaseLexica;
import model.sintaxis.ParseException;
import model.sintaxis.TokenMgrError;
import view.Printer;

public class Controller {
    public void analisisLexico(Reader input, Printer output) throws Exception {
        AnalizadorLexico alex = new AnalizadorLexico(input);
        UnidadLexica unidad = null;
        do {
            try {
                unidad = alex.next_token();
                output.write(UnidadLexica.terminalNames[unidad.clase()], unidad.fila(), unidad.columna(), unidad.lexema());
            }
            catch(LexicoException e) {
                output.writeLexicoException(e.getMessage());
            }
        }
        while (unidad == null || unidad.clase() != ClaseLexica.EOF);
        output.close();
    }

    public void analisisSintacticoCC(Reader input, Printer output) throws Exception {
        try{
            AnalizadorSintacticoCC asint = new AnalizadorSintacticoCC(input, output);
            asint.analiza();
        }
        catch(TokenMgrError e) {
            output.writeLexicoException(e.getMessage());
        }
        catch(ParseException e) {
            output.writeSintaxisException(e.getMessage());
        }
        output.close();
    }

    public void analisisSintacticoCUP(Reader input, Printer output) throws Exception {
        try {
            AnalizadorLexico alex = new AnalizadorLexico(input);
            AnalizadorSintacticoCUP asin = new AnalizadorSintacticoCUP(alex, output);
            asin.debug_parse();
        }
        catch (LexicoException e){
            output.writeLexicoException(e.getMessage());
        }
        catch (SintaxisException e){
            output.writeSintaxisException(e.getMessage());
        }
        output.close();
    }
}

