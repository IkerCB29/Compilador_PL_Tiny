package controller;

import exceptions.SintaxisException;
import java.io.*;

import exceptions.LexicoException;
import model.lexico.AnalizadorLexico;
import model.sintaxis.ConstructorASTsCC;
import model.sintaxis.ConstructorASTsCUP;
import model.sintaxis.ParseException;
import model.sintaxis.SintaxisAbstracta.Prog;
import model.sintaxis.TokenMgrError;
import model.sintaxis.impresionRecursiva.ImpresionBonitaRecursiva;
import model.sintaxis.impresionVisitante.ImpresionBonitaVisitante;
import view.Printer;

public class Controller {
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
                default: throw new Exception("Invalid parameters");
            }
        }
        catch(TokenMgrError e) {
            output.writeLexicoException();
        }
        catch(ParseException e) {
            output.writeSintaxisException();
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
            output.writeLexicoException();
        }
        catch (SintaxisException e){
            output.writeSintaxisException();
        }
        output.close();
    }
}

