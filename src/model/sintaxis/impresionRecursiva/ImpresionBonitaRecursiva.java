package model.sintaxis.impresionRecursiva;

import java.io.IOException;
import model.sintaxis.SintaxisAbstracta;
import view.Printer;

public class ImpresionBonitaRecursiva extends SintaxisAbstracta {
    private final Printer output;
    public ImpresionBonitaRecursiva(Printer output){
        this.output = output;
    }

    public void imprime(Prog prog) throws IOException {
        imprime(prog.bloque());
    }

    private void imprime(Bloque bq) throws IOException {
        output.write("{\n");
        imprime(bq.decsOpt());
        imprime(bq.instrsOpt());
        output.write("}\n");
    }

    private void imprime(Decs_opt decsOpt) throws IOException {
        if(claseDe(decsOpt, Si_decs.class)){
            imprime(decsOpt.decs());
            output.write("&&\n");
        }
    }

    private void imprime(Decs decs) throws IOException{
        if(claseDe(decs, L_decs.class)) {
            imprime(decs.decs());
            output.write(";\n");
            imprime(decs.dec());
        }
        else{
            imprime(decs.dec());
        }
    }

    private void imprime(Dec dec) throws IOException{
        if(claseDe(dec, T_dec.class)){
            output.write("<type>\n");
            imprime(dec.tipo());
            output.write(dec.iden() + "$f:" + dec.leeFila() + ",c:" + dec.leeCol() + "$\n");
        }
        else if(claseDe(dec, V_dec.class)){
            imprime(dec.tipo());
            output.write(dec.iden() + "$f:" + dec.leeFila() + ",c:" + dec.leeCol() + "$\n");
        }
        else{
            output.write("<proc>\n");
            output.write(dec.iden() + "$f:" + dec.leeFila() + ",c:" + dec.leeCol() + "$\n");
            output.write("(\n");
            imprime(dec.lParamOpt());
            output.write(")\n");
            imprime(dec.bloque());
        }
    }

    private void imprime(Tipo tipo) throws IOException{
        if(claseDe(tipo, A_tipo.class)){
            imprime(tipo.tipo());
            output.write("[\n");
            output.write((tipo.capacidad() + "\n"));
            output.write("]"+"$f:" + tipo.leeFila() + ",c:" + tipo.leeCol() + "$\n");
        }
        else if(claseDe(tipo, P_tipo.class)){
            output.write("^\n");
            imprime(tipo.tipo());
        }
        else if(claseDe(tipo, In_tipo.class)){
            output.write("<int>\n");
        }
        else if(claseDe(tipo, R_tipo.class)){
            output.write("<real>\n");
        }
        else if(claseDe(tipo, B_tipo.class)){
            output.write("<bool>\n");
        }
        else if(claseDe(tipo, String_tipo.class)){
            output.write("<string>\n");
        }
        else if(claseDe(tipo, Id_tipo.class)){
            output.write(tipo.iden() + "$f:" + tipo.leeFila() + ",c:" + tipo.leeCol() + "$\n");
        }
        else {
            output.write("<struct>\n");
            output.write("{\n");
            imprime(tipo.campos());
            output.write("}\n");
        }
    }

    private void imprime(Campos campos) throws IOException{
        if(claseDe(campos, L_campos.class)) {
            imprime(campos.campos());
            output.write(",\n");
            imprime(campos.campo());
        }
        else{
            imprime(campos.campo());
        }
    }

    private void imprime(Campo campo) throws IOException{
        imprime(campo.tipo());
        output.write(campo.iden() + "$f:" + campo.leeFila() + ",c:" + campo.leeCol() + "$\n");
    }

    private void imprime(LParam_opt lParamOpt) throws IOException{
        if(claseDe(lParamOpt, Si_param.class)){
            imprime(lParamOpt.lParam());
        }
    }

    private void imprime(LParam lParam) throws IOException{
        if(claseDe(lParam, L_param.class)){
            imprime(lParam.lParam());
            output.write(",\n");
            imprime(lParam.param());
        }
        else{
            imprime(lParam.param());
        }
    }

    private void imprime(Param param) throws IOException{
        if(claseDe(param, Param_simple.class)){
            imprime(param.tipo());
            output.write(param.iden() + "$f:" + param.leeFila() + ",c:" + param.leeCol() + "$\n");
        }
        else{
            imprime(param.tipo());
            output.write("&\n");
            output.write(param.iden() + "$f:" + param.leeFila() + ",c:" + param.leeCol() + "$\n");
        }
    }

    private void imprime(Instrs_opt instrsOpt) throws IOException {
        if(claseDe(instrsOpt, Si_instrs.class)){
            imprime(instrsOpt.instrs());
        }
    }

    private void imprime(Instrs instrs) throws IOException {
        if(claseDe(instrs, L_instrs.class)){
            imprime(instrs.instrs());
            output.write(";\n");
            imprime(instrs.instr());
        }
        else{
            imprime(instrs.instr());
        }
    }

    private void imprime(Instr instr) throws IOException {
        if(claseDe(instr, Eva.class)){
            output.write("@\n");
            imprime(instr.exp());
        }
        else if(claseDe(instr, If_instr.class)){
            output.write("<if>\n");
            imprime(instr.exp());
            imprime(instr.bloque());
        }
        else if(claseDe(instr, If_el.class)){
            output.write("<if>\n");
            imprime(instr.exp());
            imprime(instr.bloque());
            output.write("<else>\n");
            imprime(instr.bloqueElse());
        }
        else if(claseDe(instr, Wh.class)){
            output.write("<while>\n");
            imprime(instr.exp());
            imprime(instr.bloque());
        }
        else if(claseDe(instr, Rd.class)){
            output.write("<read>\n");
            imprime(instr.exp());
        }
        else if(claseDe(instr, Wr.class)){
            output.write("<write>\n");
            imprime(instr.exp());
        }
        else if(claseDe(instr, Nw.class)){
            output.write("<new>\n");
            imprime(instr.exp());
        }
        else if(claseDe(instr, Dl.class)){
            output.write("<delete>\n");
            imprime(instr.exp());
        }
        else if(claseDe(instr, Nl_instr.class)){
            output.write("<nl>\n");
        }
        else if(claseDe(instr, Cl.class)){
            output.write("<call>\n");
            output.write(instr.iden() + "$f:" + instr.leeFila() + ",c:" + instr.leeCol() + "$\n");
            output.write("(\n");
            imprime(instr.expsOpt());
            output.write(")\n");
        }
        else{
            imprime(instr.bloque());
        }
    }

    private void imprime(Exps_opt expsOpt) throws IOException {
        if(claseDe(expsOpt, Si_exps.class)){
            imprime(expsOpt.exps());
        }
    }

    private void imprime(Exps exps) throws IOException {
        if(claseDe(exps, L_exps.class)){
            imprime(exps.exps());
            output.write(",\n");
            imprime(exps.exp());
        }
        else{
            imprime(exps.exp());
        }
    }

    private void imprime(Exp exp) throws IOException {
        if(claseDe(exp, Asig.class)){
            imprimeExpBin(exp.opnd0(), "=", exp.opnd1(), 1, 0, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, My.class)){
            imprimeExpBin(exp.opnd0(), ">", exp.opnd1(), 1, 2, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Mn.class)){
            imprimeExpBin(exp.opnd0(), "<", exp.opnd1(), 1, 2, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Myig.class)){
            imprimeExpBin(exp.opnd0(), ">=", exp.opnd1(), 1, 2, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Mnig.class)){
            imprimeExpBin(exp.opnd0(), "<=", exp.opnd1(), 1, 2, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Ig.class)){
            imprimeExpBin(exp.opnd0(), "==", exp.opnd1(), 1, 2, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Dif.class)){
            imprimeExpBin(exp.opnd0(), "!=", exp.opnd1(), 1, 2, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Suma.class)){
            imprimeExpBin(exp.opnd0(), "+", exp.opnd1(), 2, 3, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Resta.class)){
            imprimeExpBin(exp.opnd0(), "-", exp.opnd1(), 3, 3, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, And.class)){
            imprimeExpBin(exp.opnd0(), "<and>", exp.opnd1(), 4, 3, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Or.class)){
            imprimeExpBin(exp.opnd0(), "<or>", exp.opnd1(), 4, 4, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Mul.class)){
            imprimeExpBin(exp.opnd0(), "*", exp.opnd1(), 4, 5, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Div.class)){
            imprimeExpBin(exp.opnd0(), "/", exp.opnd1(), 4, 5, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Mod.class)){
            imprimeExpBin(exp.opnd0(), "%", exp.opnd1(), 4, 5, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Menos_unario.class)){
            imprimeExpPre(exp.opnd0(), "-", 5, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Not.class)){
            imprimeExpPre(exp.opnd0(), "<not>", 5, exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp, Indexacion.class)){
            imprimeOpnd(exp.opnd0(), 6);
            output.write("["+"$f:" + exp.leeFila() + ",c:" + exp.leeCol() + "$\n");
            imprimeOpnd(exp.opnd1(), 0);
            output.write("]\n");
        }
        else if(claseDe(exp, Acceso.class)){
            imprimeOpnd(exp.opnd0(), 6);
            output.write(".\n");
            output.write(exp.iden() + "$f:" + exp.leeFila() + ",c:" + exp.leeCol() + "$\n");
        }
        else if(claseDe(exp, Indireccion.class)){
            imprimeOpnd(exp.opnd0(), 6);
            output.write("^"+"$f:" + exp.leeFila() + ",c:" + exp.leeCol() + "$\n");
        }
        else if(claseDe(exp, Iden.class)){
            output.write(exp.iden() + "$f:" + exp.leeFila() + ",c:" + exp.leeCol() + "$\n");
        }
        else if(claseDe(exp, True.class)){
            output.write("<true>$f:" + exp.leeFila() + ",c:" + exp.leeCol() + "$\n");
        }
        else if(claseDe(exp, False.class)){
            output.write("<false>$f:" + exp.leeFila() + ",c:" + exp.leeCol() + "$\n");
        }
        else if(claseDe(exp, Null_exp.class)){
            output.write("<null>$f:" + exp.leeFila() + ",c:" + exp.leeCol() + "$\n");
        }
        else{
            output.write(exp.valor() + "$f:" + exp.leeFila() + ",c:" + exp.leeCol() + "$\n");
        }
    }

    private void imprimeExpPre(Exp opnd, String op, int np, int fila, int columna) throws IOException {
        output.write(op + "$f:" + fila + ",c:" + columna + "$\n");
        imprimeOpnd(opnd, np);
    }

    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1, int fila, int columna) throws IOException {
        imprimeOpnd(opnd0, np0);
        output.write(op + "$f:" + fila + ",c:" + columna + "$\n");
        imprimeOpnd(opnd1, np1);
    }

    private void imprimeOpnd(Exp opnd, int minPrior) throws IOException {
        if (prioridad(opnd) < minPrior){
            output.write("(\n");
        }
        imprime(opnd);
        if (prioridad(opnd) < minPrior){
            output.write(")\n");
        }
    }

    private int prioridad(Exp exp){
        if(claseDe(exp, Asig.class)) return 0;
        else if(claseDe(exp, My.class)) return 1;
        else if(claseDe(exp, Mn.class)) return 1;
        else if(claseDe(exp, Myig.class)) return 1;
        else if(claseDe(exp, Mnig.class)) return 1;
        else if(claseDe(exp, Ig.class)) return 1;
        else if(claseDe(exp, Dif.class)) return 1;
        else if(claseDe(exp, Suma.class)) return 2;
        else if(claseDe(exp, Resta.class)) return 2;
        else if(claseDe(exp, And.class)) return 3;
        else if(claseDe(exp, Or.class)) return 3;
        else if(claseDe(exp, Mul.class)) return 4;
        else if(claseDe(exp, Div.class)) return 4;
        else if(claseDe(exp, Mod.class)) return 4;
        else if(claseDe(exp, Menos_unario.class)) return 5;
        else if(claseDe(exp, Not.class)) return 5;
        else if(claseDe(exp, Indexacion.class)) return 6;
        else if(claseDe(exp, Acceso.class)) return 6;
        else if(claseDe(exp, Indireccion.class)) return 6;
        else return 7;
    }

    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }
}
