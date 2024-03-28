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
    public void analisisSintacticoCC(Reader input, Printer output, int outputOption) throws Exception {
        try{
            ConstructorASTsCC asin = new ConstructorASTsCC(input);
            asin.disable_tracing();
            if(outputOption == 0) {
                ImpresionBonitaRecursiva impresionBonitaRecursiva = new ImpresionBonitaRecursiva(output);
                impresionBonitaRecursiva.imprime(asin.analiza());
            }
            else if(outputOption == 1){
                asin.analiza().imprime(output);
            }
            else if(outputOption == 2){
                ImpresionBonitaVisitante impresionBonitaVisitante = new ImpresionBonitaVisitante(output);
                asin.analiza().procesa(impresionBonitaVisitante);
            }
            else throw new IndexOutOfBoundsException("Output option must be a value between 0 - 2");
        }
        catch(TokenMgrError e) {
            output.writeLexicoException();
        }
        catch(ParseException e) {
            output.writeSintaxisException();
        }
        output.close();
    }

    public void analisisSintacticoCUP(Reader input, Printer output, int outputOption) throws Exception {
        try {
            AnalizadorLexico alex = new AnalizadorLexico(input);
            ConstructorASTsCUP asin = new ConstructorASTsCUP(alex);
            if(outputOption == 0) {
                ImpresionBonitaRecursiva impresionBonitaRecursiva = new ImpresionBonitaRecursiva(output);
                impresionBonitaRecursiva.imprime((Prog) asin.debug_parse().value);
            }
            else if(outputOption == 1){
                ((Prog) asin.debug_parse().value).imprime(output);
            }
            else if(outputOption == 2){
                ImpresionBonitaVisitante impresionBonitaVisitante = new ImpresionBonitaVisitante(output);
                ((Prog) asin.debug_parse().value).procesa(impresionBonitaVisitante);
            }
            else throw new IndexOutOfBoundsException("Output option must be a value between 0 - 2");

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

