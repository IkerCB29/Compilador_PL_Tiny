package controller;

import exceptions.SintaxisException;
import java.io.*;

import exceptions.LexicoException;
import model.lexico.AnalizadorLexico;
import model.maquinaP.MaquinaP;
import model.semantica.AsignacionEspacio;
import model.semantica.ComprobacionTipos;
import model.semantica.Etiquetado;
import model.semantica.GeneracionCodigo;
import model.semantica.Vinculacion;
import model.semantica.errores.Errores;
import model.sintaxis.AnalizadorSintacticoCC;
import model.sintaxis.AnalizadorSintacticoCUP;
import model.sintaxis.ConstructorASTsCC;
import model.sintaxis.ConstructorASTsCUP;
import model.sintaxis.ParseException;
import model.sintaxis.SintaxisAbstracta.Prog;
import model.sintaxis.TokenMgrError;
import model.sintaxis.impresionRecursiva.ImpresionBonitaRecursiva;
import model.sintaxis.impresionVisitante.ImpresionBonitaVisitante;
import view.ConsolePrinter;
import view.Printer;

public class Controller {
    public Prog analisisSintacticoCC(Reader input, Printer output, boolean debug) throws Exception {
        Prog prog = null;
        try{
            ConstructorASTsCC asin = debug ? new AnalizadorSintacticoCC(input, output) : new ConstructorASTsCC(input);
            asin.disable_tracing();
            prog = asin.analiza();
        }
        catch(TokenMgrError e) {
            output.write("ERROR_LEXICO\n");
        }
        catch(ParseException e) {
            output.write("ERROR_SINTACTICO\n");
        }
        return prog;
    }

    public Prog analisisSintacticoCUP(Reader input, Printer output, boolean debug) throws Exception {
        Prog prog = null;
        try {
            AnalizadorLexico alex = new AnalizadorLexico(input);
            ConstructorASTsCUP asin = debug ? new AnalizadorSintacticoCUP(alex, output) : new ConstructorASTsCUP(alex);
            prog = (Prog) asin.debug_parse().value;
        }
        catch (LexicoException e){
            output.write("ERROR_LEXICO\n");
        }
        catch (SintaxisException e){
            output.write("ERROR_SINTACTICO\n");
        }
        return prog;
    }

    public void impresionBonita(Prog prog, Printer output) throws Exception {
        if(prog == null) {
            output.close();
            return;
        }
        output.write("IMPRESION RECURSIVA\n");
        ImpresionBonitaRecursiva impresionBonitaRecursiva = new ImpresionBonitaRecursiva(output);
        impresionBonitaRecursiva.imprime(prog);
        output.write("IMPRESION INTERPRETE\n");
        prog.imprime(output);
        output.write("IMPRESION VISITANTE\n");
        ImpresionBonitaVisitante impresionBonitaVisitante = new ImpresionBonitaVisitante(output);
        prog.procesa(impresionBonitaVisitante);
        output.close();
    }

    public void procesamiento(Prog prog, Reader input, Printer output) throws Exception {
        if(prog == null) {
            output.close();
            return;
        }
        Errores errores = new Errores();
        new Vinculacion(errores).procesa(prog);
        if(errores.hayErroresVinculacion()){
            errores.printErroresVinculacion(output);
            output.close();
            return;
        }
        if(errores.hayErroresPretipado()){
            errores.printErroresPretipado(output);
            output.close();
            return;
        }
        new ComprobacionTipos(new ConsolePrinter()).procesa(prog);
        new AsignacionEspacio().procesa(prog);
        new Etiquetado().procesa(prog);
        MaquinaP maquinaP = new MaquinaP(input, output, 1000, 1000, 1000, 5);
        new GeneracionCodigo(maquinaP).procesa(prog);
        maquinaP.ejecuta();
        output.close();
    }
}

