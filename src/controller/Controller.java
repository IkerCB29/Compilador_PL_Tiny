package controller;

import exceptions.SintaxisException;
import java.io.*;

import exceptions.LexicoException;
import model.lexico.AnalizadorLexico;
import model.semantica.Vinculacion;
import model.sintaxis.AnalizadorSintacticoCC;
import model.sintaxis.AnalizadorSintacticoCUP;
import model.sintaxis.ConstructorASTsCC;
import model.sintaxis.ConstructorASTsCUP;
import model.sintaxis.ParseException;
import model.sintaxis.SintaxisAbstracta.Prog;
import model.sintaxis.TokenMgrError;
import model.sintaxis.impresionRecursiva.ImpresionBonitaRecursiva;
import model.sintaxis.impresionVisitante.ImpresionBonitaVisitante;
import view.Printer;

public class Controller {
    public void analisisSintacticoCC(Reader input, Printer output) throws Exception {
        try {
            output.write("CONSTRUCCION AST DESCENDENTE\n");
            ConstructorASTsCC asin = new AnalizadorSintacticoCC(input, output);
            asin.disable_tracing();
            Prog prog = asin.analiza();
            output.write("IMPRESION RECURSIVA\n");
            ImpresionBonitaRecursiva impresionBonitaRecursiva = new ImpresionBonitaRecursiva(output);
            impresionBonitaRecursiva.imprime(prog);
            output.write("IMPRESION INTERPRETE\n");
            prog.imprime(output);
            output.write("IMPRESION VISITANTE\n");
            ImpresionBonitaVisitante impresionBonitaVisitante = new ImpresionBonitaVisitante(output);
            prog.procesa(impresionBonitaVisitante);
        }
        catch(TokenMgrError e) {
            output.write("ERROR_LEXICO\n");
        }
        catch(ParseException e) {
            output.write("ERROR_SINTACTICO\n");
        }
        output.close();
    }

    public void analisisSintacticoCC(Reader input, Printer output, String outputOption) throws Exception {
        try{
            ConstructorASTsCC asin = new ConstructorASTsCC(input);
            asin.disable_tracing();
            switch (outputOption) {
                case "rec":
                    ImpresionBonitaRecursiva impresionBonitaRecursiva = new ImpresionBonitaRecursiva(output);
                    impresionBonitaRecursiva.imprime(asin.analiza());
                    break;
                case "int":
                    asin.analiza().imprime(output);
                    break;
                case "vis":
                    ImpresionBonitaVisitante impresionBonitaVisitante = new ImpresionBonitaVisitante(output);
                    asin.analiza().procesa(impresionBonitaVisitante);
                    break;
                default: throw new RuntimeException("Invalid parameters");
            }
        }
        catch(TokenMgrError e) {
            output.write("ERROR_LEXICO\n");
        }
        catch(ParseException e) {
            output.write("ERROR_SINTACTICO\n");
        }
        output.close();
    }

    public void analisisSintacticoCUP(Reader input, Printer output) throws Exception {
        try {
            output.write("CONSTRUCCION AST ASCENDENTE\n");
            AnalizadorLexico alex = new AnalizadorLexico(input);
            ConstructorASTsCUP asin = new AnalizadorSintacticoCUP(alex, output);
            Prog prog = (Prog) asin.debug_parse().value;
            output.write("IMPRESION RECURSIVA\n");
            ImpresionBonitaRecursiva impresionBonitaRecursiva = new ImpresionBonitaRecursiva(output);
            impresionBonitaRecursiva.imprime(prog);
            output.write("IMPRESION INTERPRETE\n");
            prog.imprime(output);
            output.write("IMPRESION VISITANTE\n");
            ImpresionBonitaVisitante impresionBonitaVisitante = new ImpresionBonitaVisitante(output);
            prog.procesa(impresionBonitaVisitante);
        }
        catch (LexicoException e){
            output.write("ERROR_LEXICO\n");
        }
        catch (SintaxisException e){
            output.write("ERROR_SINTACTICO\n");
        }
        output.close();
    }

    public void analisisSintacticoCUP(Reader input, Printer output, String outputOption) throws Exception {
        try {
            AnalizadorLexico alex = new AnalizadorLexico(input);
            ConstructorASTsCUP asin = new ConstructorASTsCUP(alex);
            switch (outputOption) {
                case "rec":
                    ImpresionBonitaRecursiva impresionBonitaRecursiva = new ImpresionBonitaRecursiva(output);
                    impresionBonitaRecursiva.imprime((Prog) asin.debug_parse().value);
                    break;
                case "int":
                    ((Prog) asin.debug_parse().value).imprime(output);
                    break;
                case "vis":
                    ImpresionBonitaVisitante impresionBonitaVisitante = new ImpresionBonitaVisitante(output);
                    ((Prog) asin.debug_parse().value).procesa(impresionBonitaVisitante);
                    break;
                default: throw new Exception("Invalid parameters");
            }

        }
        catch (LexicoException e){
            output.write("ERROR_LEXICO\n");
        }
        catch (SintaxisException e){
            output.write("ERROR_SINTACTICO\n");
        }
        output.close();
    }

    public void procesamiento(Reader input, Printer output) throws Exception {
        try {
            AnalizadorLexico alex = new AnalizadorLexico(input);
            ConstructorASTsCUP asin = new ConstructorASTsCUP(alex);
            Vinculacion v = new Vinculacion();
            Prog prog = ((Prog) asin.debug_parse().value);
            prog.procesa(v);
            return;
        }
        catch (LexicoException e){
            output.write("ERROR_LEXICO\n");
        }
        catch (SintaxisException e){
            output.write("ERROR_SINTACTICO\n");
        }
        output.close();
    }
}

