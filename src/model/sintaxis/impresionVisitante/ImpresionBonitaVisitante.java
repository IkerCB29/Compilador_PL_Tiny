package model.sintaxis.impresionVisitante;

import java.io.OutputStream;
import model.sintaxis.SintaxisAbstracta.A_tipo;
import model.sintaxis.SintaxisAbstracta.Acceso;
import model.sintaxis.SintaxisAbstracta.And;
import model.sintaxis.SintaxisAbstracta.Asig;
import model.sintaxis.SintaxisAbstracta.B_tipo;
import model.sintaxis.SintaxisAbstracta.Bloque;
import model.sintaxis.SintaxisAbstracta.Bq_instr;
import model.sintaxis.SintaxisAbstracta.Camp;
import model.sintaxis.SintaxisAbstracta.Cl;
import model.sintaxis.SintaxisAbstracta.Dif;
import model.sintaxis.SintaxisAbstracta.Div;
import model.sintaxis.SintaxisAbstracta.Dl;
import model.sintaxis.SintaxisAbstracta.Entero;
import model.sintaxis.SintaxisAbstracta.Eva;
import model.sintaxis.SintaxisAbstracta.Exp;
import model.sintaxis.SintaxisAbstracta.False;
import model.sintaxis.SintaxisAbstracta.Id_tipo;
import model.sintaxis.SintaxisAbstracta.Iden;
import model.sintaxis.SintaxisAbstracta.If_el;
import model.sintaxis.SintaxisAbstracta.If_instr;
import model.sintaxis.SintaxisAbstracta.Ig;
import model.sintaxis.SintaxisAbstracta.In_tipo;
import model.sintaxis.SintaxisAbstracta.Indexacion;
import model.sintaxis.SintaxisAbstracta.Indireccion;
import model.sintaxis.SintaxisAbstracta.L_campos;
import model.sintaxis.SintaxisAbstracta.L_decs;
import model.sintaxis.SintaxisAbstracta.L_exps;
import model.sintaxis.SintaxisAbstracta.L_instrs;
import model.sintaxis.SintaxisAbstracta.L_param;
import model.sintaxis.SintaxisAbstracta.Menos_unario;
import model.sintaxis.SintaxisAbstracta.Mn;
import model.sintaxis.SintaxisAbstracta.Mnig;
import model.sintaxis.SintaxisAbstracta.Mod;
import model.sintaxis.SintaxisAbstracta.Mul;
import model.sintaxis.SintaxisAbstracta.My;
import model.sintaxis.SintaxisAbstracta.Myig;
import model.sintaxis.SintaxisAbstracta.Nl_instr;
import model.sintaxis.SintaxisAbstracta.No_decs;
import model.sintaxis.SintaxisAbstracta.No_exps;
import model.sintaxis.SintaxisAbstracta.No_instrs;
import model.sintaxis.SintaxisAbstracta.No_param;
import model.sintaxis.SintaxisAbstracta.Not;
import model.sintaxis.SintaxisAbstracta.Null_exp;
import model.sintaxis.SintaxisAbstracta.Nw;
import model.sintaxis.SintaxisAbstracta.Or;
import model.sintaxis.SintaxisAbstracta.P_dec;
import model.sintaxis.SintaxisAbstracta.P_tipo;
import model.sintaxis.SintaxisAbstracta.Param_ref;
import model.sintaxis.SintaxisAbstracta.Param_simple;
import model.sintaxis.SintaxisAbstracta.Prog;
import model.sintaxis.SintaxisAbstracta.R_tipo;
import model.sintaxis.SintaxisAbstracta.Rd;
import model.sintaxis.SintaxisAbstracta.Real;
import model.sintaxis.SintaxisAbstracta.Resta;
import model.sintaxis.SintaxisAbstracta.Si_decs;
import model.sintaxis.SintaxisAbstracta.Si_exps;
import model.sintaxis.SintaxisAbstracta.Si_instrs;
import model.sintaxis.SintaxisAbstracta.Si_param;
import model.sintaxis.SintaxisAbstracta.String_exp;
import model.sintaxis.SintaxisAbstracta.String_tipo;
import model.sintaxis.SintaxisAbstracta.Struct_tipo;
import model.sintaxis.SintaxisAbstracta.Suma;
import model.sintaxis.SintaxisAbstracta.T_dec;
import model.sintaxis.SintaxisAbstracta.True;
import model.sintaxis.SintaxisAbstracta.Un_campo;
import model.sintaxis.SintaxisAbstracta.Un_param;
import model.sintaxis.SintaxisAbstracta.Una_dec;
import model.sintaxis.SintaxisAbstracta.Una_exp;
import model.sintaxis.SintaxisAbstracta.Una_instr;
import model.sintaxis.SintaxisAbstracta.V_dec;
import model.sintaxis.SintaxisAbstracta.Wh;
import model.sintaxis.SintaxisAbstracta.Wr;

public class ImpresionBonitaVisitante implements Procesamiento{
    private final OutputStream output;
    public ImpresionBonitaVisitante(OutputStream output){
        this.output = output;
    }
    private void imprimeExpPre(Exp opnd, String op, int np) throws Exception {
        output.write((op + "\n").getBytes());
        imprimeOpnd(opnd, np);
    }

    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) throws Exception {
        imprimeOpnd(opnd0, np0);
        output.write((op + "\n").getBytes());
        imprimeOpnd(opnd1, np1);
    }

    private void imprimeOpnd(Exp opnd, int minPrior) throws Exception {
        if (opnd.prioridad() < minPrior){
            output.write("(\n".getBytes());
        }
        opnd.procesa(this);
        if (opnd.prioridad() < minPrior){
            output.write(")\n".getBytes());
        }
    }
    @Override
    public void procesa(Prog prog) throws Exception {
        prog.bloque().procesa(this);
        output.write("<EOF>\n".getBytes());
    }

    @Override
    public void procesa(Bloque bloque) throws Exception  {
        output.write("{\n".getBytes());
        bloque.decsOpt().procesa(this);
        bloque.instrsOpt().procesa(this);
        output.write("}\n".getBytes());
    }

    @Override
    public void procesa(Si_decs decs) throws Exception {
        decs.decs().procesa(this);
        output.write("&&\n".getBytes());
    }

    @Override
    public void procesa(No_decs decs) throws Exception {}

    @Override
    public void procesa(L_decs decs) throws Exception {
        decs.decs().procesa(this);
        output.write(";\n".getBytes());
        decs.dec().procesa(this);
    }

    @Override
    public void procesa(Una_dec decs) throws Exception {
        decs.dec().procesa(this);
    }

    @Override
    public void procesa(T_dec dec) throws Exception {
        output.write("<type>\n".getBytes());
        dec.tipo().procesa(this);
        output.write((dec.iden() + "\n").getBytes());
    }

    @Override
    public void procesa(V_dec dec) throws Exception {
        dec.tipo().procesa(this);
        output.write((dec.iden() + "\n").getBytes());
    }

    @Override
    public void procesa(P_dec dec) throws Exception {
        output.write("<proc>\n".getBytes());
        output.write((dec.iden() + "\n").getBytes());
        output.write("(\n".getBytes());
        dec.lParamOpt().procesa(this);
        output.write(")\n".getBytes());
        dec.bloque().procesa(this);
    }

    @Override
    public void procesa(A_tipo tipo) throws Exception {
        tipo.tipo().procesa(this);
        output.write("[\n".getBytes());
        output.write((tipo.capacidad() + "\n").getBytes());
        output.write("]\n".getBytes());
    }

    @Override
    public void procesa(P_tipo tipo) throws Exception {
        output.write("^\n".getBytes());
        tipo.tipo().procesa(this);
    }

    @Override
    public void procesa(In_tipo tipo) throws Exception {
        output.write("<int>\n".getBytes());
    }

    @Override
    public void procesa(R_tipo tipo) throws Exception {
        output.write("<real>\n".getBytes());
    }

    @Override
    public void procesa(B_tipo tipo) throws Exception {
        output.write("<bool>\n".getBytes());
    }

    @Override
    public void procesa(String_tipo tipo) throws Exception {
        output.write("<string>\n".getBytes());
    }

    @Override
    public void procesa(Id_tipo tipo) throws Exception {
        output.write((tipo.iden() + "\n").getBytes());
    }

    @Override
    public void procesa(Struct_tipo tipo) throws Exception {
        output.write("<struct>\n".getBytes());
        output.write("{\n".getBytes());
        tipo.campos().procesa(this);
        output.write("}\n".getBytes());
    }

    @Override
    public void procesa(L_campos campos) throws Exception {
        campos.campos().procesa(this);
        output.write(",\n".getBytes());
        campos.campo().procesa(this);
    }

    @Override
    public void procesa(Un_campo campos) throws Exception {
        campos.campo().procesa(this);
    }

    @Override
    public void procesa(Camp campo) throws Exception {
        campo.tipo().procesa(this);
        output.write((campo.iden() + "\n").getBytes());
    }

    @Override
    public void procesa(Si_param lParam) throws Exception {
        lParam.lParam().procesa(this);
    }

    @Override
    public void procesa(No_param lParam) throws Exception {}

    @Override
    public void procesa(L_param lParam) throws Exception {
        lParam.lParam().procesa(this);
        output.write(",\n".getBytes());
        lParam.param().procesa(this);
    }

    @Override
    public void procesa(Un_param lParam) throws Exception {
        lParam.param().procesa(this);
    }

    @Override
    public void procesa(Param_simple param) throws Exception {
        param.tipo().procesa(this);
        output.write((param.iden() + "\n").getBytes());
    }

    @Override
    public void procesa(Param_ref param) throws Exception {
        param.tipo().procesa(this);
        output.write("&\n".getBytes());
        output.write((param.iden() + "\n").getBytes());
    }

    @Override
    public void procesa(Si_instrs instrs) throws Exception {
        instrs.instrs().procesa(this);
    }

    @Override
    public void procesa(No_instrs instrs) throws Exception {}

    @Override
    public void procesa(L_instrs instrs) throws Exception {
        instrs.instrs().procesa(this);
        output.write(";\n".getBytes());
        instrs.instr().procesa(this);
    }

    @Override
    public void procesa(Una_instr instrs) throws Exception {
        instrs.instr().procesa(this);
    }

    @Override
    public void procesa(Eva instr) throws Exception {
        output.write("@\n".getBytes());
        instr.exp().procesa(this);
    }

    @Override
    public void procesa(If_instr instr) throws Exception {
        output.write("<if>\n".getBytes());
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
    }

    @Override
    public void procesa(If_el instr) throws Exception {
        output.write("<if>\n".getBytes());
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
        output.write("<else>\n".getBytes());
        instr.bloqueElse().procesa(this);
    }

    @Override
    public void procesa(Wh instr) throws Exception {
        output.write("<while>\n".getBytes());
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
    }

    @Override
    public void procesa(Rd instr) throws Exception {
        output.write("<read>\n".getBytes());
        instr.exp().procesa(this);
    }

    @Override
    public void procesa(Wr instr) throws Exception {
        output.write("<write>\n".getBytes());
        instr.exp().procesa(this);
    }

    @Override
    public void procesa(Nw instr) throws Exception {
        output.write("<new>\n".getBytes());
        instr.exp().procesa(this);
    }

    @Override
    public void procesa(Dl instr) throws Exception {
        output.write("<delete>\n".getBytes());
        instr.exp().procesa(this);
    }

    @Override
    public void procesa(Nl_instr instr) throws Exception {
        output.write("<nl>\n".getBytes());
    }

    @Override
    public void procesa(Cl instr) throws Exception {
        output.write("<call>\n".getBytes());
        output.write((instr.iden() + "\n").getBytes());
        output.write("(\n".getBytes());
        instr.expsOpt().procesa(this);
        output.write(")\n".getBytes());
    }

    @Override
    public void procesa(Bq_instr instr) throws Exception {
        instr.bloque().procesa(this);
    }

    @Override
    public void procesa(Si_exps exps) throws Exception {
        exps.exps().procesa(this);
    }

    @Override
    public void procesa(No_exps exps) throws Exception {}

    @Override
    public void procesa(L_exps exps) throws Exception {
        exps.exps().procesa(this);
        output.write(",\n".getBytes());
        exps.exp().procesa(this);
    }

    @Override
    public void procesa(Una_exp exps) throws Exception {
        exps.exp().procesa(this);
    }

    @Override
    public void procesa(Asig exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "=", exp.opnd1(), 1, 0);
    }

    @Override
    public void procesa(My exp) throws Exception {
        imprimeExpBin(exp.opnd0(), ">", exp.opnd1(), 1, 2);
    }

    @Override
    public void procesa(Mn exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "<", exp.opnd1(), 1, 2);
    }

    @Override
    public void procesa(Myig exp) throws Exception {
        imprimeExpBin(exp.opnd0(), ">=", exp.opnd1(), 1, 2);
    }

    @Override
    public void procesa(Mnig exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "<=", exp.opnd1(), 1, 2);
    }

    @Override
    public void procesa(Ig exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "==", exp.opnd1(), 1, 2);
    }

    @Override
    public void procesa(Dif exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "!=", exp.opnd1(), 1, 2);
    }

    @Override
    public void procesa(Suma exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "+", exp.opnd1(), 2, 3);
    }

    @Override
    public void procesa(Resta exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "-", exp.opnd1(), 3, 3);
    }

    @Override
    public void procesa(And exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "<and>", exp.opnd1(), 4, 3);
    }

    @Override
    public void procesa(Or exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "<or>", exp.opnd1(), 4, 4);
    }

    @Override
    public void procesa(Mul exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "*", exp.opnd1(), 4, 5);
    }

    @Override
    public void procesa(Div exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "/", exp.opnd1(), 4, 5);
    }

    @Override
    public void procesa(Mod exp) throws Exception {
        imprimeExpBin(exp.opnd0(), "%", exp.opnd1(), 4, 5);
    }

    @Override
    public void procesa(Menos_unario exp) throws Exception {
        imprimeExpPre(exp.opnd0(), "-", 5);
    }

    @Override
    public void procesa(Not exp) throws Exception {
        imprimeExpPre(exp.opnd0(), "<not>", 5);
    }

    @Override
    public void procesa(Indexacion exp) throws Exception {
        imprimeOpnd(exp.opnd0(), 6);
        output.write("[\n".getBytes());
        imprimeOpnd(exp.opnd1(), 6);
        output.write("]\n".getBytes());
    }

    @Override
    public void procesa(Acceso exp) throws Exception {
        imprimeOpnd(exp.opnd0(), 6);
        output.write(".\n".getBytes());
        output.write((exp.iden() + "\n").getBytes());
    }

    @Override
    public void procesa(Indireccion exp) throws Exception {
        imprimeOpnd(exp.opnd0(), 6);
        output.write("^\n".getBytes());
    }

    @Override
    public void procesa(Entero exp) throws Exception {
        output.write((exp.valor() + "\n").getBytes());
    }

    @Override
    public void procesa(Real exp) throws Exception {
        output.write((exp.valor() + "\n").getBytes());
    }

    @Override
    public void procesa(True exp) throws Exception {
        output.write("<true>\n".getBytes());
    }

    @Override
    public void procesa(False exp) throws Exception {
        output.write("<false>\n".getBytes());
    }

    @Override
    public void procesa(String_exp exp) throws Exception {
        output.write((exp.valor() + "\n").getBytes());
    }

    @Override
    public void procesa(Iden exp) throws Exception {
        output.write((exp.iden() + "\n").getBytes());
    }

    @Override
    public void procesa(Null_exp exp) throws Exception {
        output.write("<null>\n".getBytes());
    }
}
