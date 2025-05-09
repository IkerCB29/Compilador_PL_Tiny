package model.semantica;

import java.io.IOException;
import java.util.Stack;
import model.Procesamiento;
import model.maquinaP.MaquinaP;
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
import model.sintaxis.SintaxisAbstracta.Exps;
import model.sintaxis.SintaxisAbstracta.False;
import model.sintaxis.SintaxisAbstracta.Id_tipo;
import model.sintaxis.SintaxisAbstracta.Iden;
import model.sintaxis.SintaxisAbstracta.If_el;
import model.sintaxis.SintaxisAbstracta.If_instr;
import model.sintaxis.SintaxisAbstracta.Ig;
import model.sintaxis.SintaxisAbstracta.In_tipo;
import model.sintaxis.SintaxisAbstracta.Indexacion;
import model.sintaxis.SintaxisAbstracta.Indireccion;
import model.sintaxis.SintaxisAbstracta.LParam;
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
import model.sintaxis.SintaxisAbstracta.Tipo;
import model.sintaxis.SintaxisAbstracta.True;
import model.sintaxis.SintaxisAbstracta.Un_campo;
import model.sintaxis.SintaxisAbstracta.Un_param;
import model.sintaxis.SintaxisAbstracta.Una_dec;
import model.sintaxis.SintaxisAbstracta.Una_exp;
import model.sintaxis.SintaxisAbstracta.Una_instr;
import model.sintaxis.SintaxisAbstracta.V_dec;
import model.sintaxis.SintaxisAbstracta.Wh;
import model.sintaxis.SintaxisAbstracta.Wr;

public class GeneracionCodigo implements Procesamiento {
    private final Stack<P_dec> procs_pendientes;
    private final MaquinaP m;

    public GeneracionCodigo(MaquinaP maquina){
        procs_pendientes = new Stack<>();
        this.m = maquina;
    }

    @Override
    public void procesa(Prog prog) throws IOException {
        prog.bloque().procesa(this);
        m.emit(m.stop());
        while(!procs_pendientes.empty()){
            P_dec top = procs_pendientes.pop();
            m.emit(m.desapilad(top.getNivel()));
            top.bloque().procesa(this);
            m.emit(m.desactiva(top.getNivel(), top.getTam()));
            m.emit(m.ir_ind());
        }
    }

    @Override
    public void procesa(Bloque bloque) throws IOException {
        bloque.decsOpt().procesa(this);
        bloque.instrsOpt().procesa(this);
    }

    @Override
    public void procesa(Si_decs decs) throws IOException {
        decs.decs().procesa(this);
    }

    @Override
    public void procesa(No_decs decs) throws IOException {}

    @Override
    public void procesa(L_decs decs) throws IOException {
        decs.decs().procesa(this);
        decs.dec().procesa(this);
    }

    @Override
    public void procesa(Una_dec decs) throws IOException {
        decs.dec().procesa(this);
    }

    @Override
    public void procesa(T_dec dec) throws IOException { }

    @Override
    public void procesa(V_dec dec) throws IOException {}

    @Override
    public void procesa(P_dec dec) throws IOException {
        procs_pendientes.push(dec);
    }

    @Override
    public void procesa(A_tipo tipo) throws IOException {}

    @Override
    public void procesa(P_tipo tipo) throws IOException { }

    @Override
    public void procesa(In_tipo tipo) throws IOException {}

    @Override
    public void procesa(R_tipo tipo) throws IOException {}

    @Override
    public void procesa(B_tipo tipo) throws IOException {}

    @Override
    public void procesa(String_tipo tipo) throws IOException {}

    @Override
    public void procesa(Id_tipo tipo) throws IOException {}

    @Override
    public void procesa(Struct_tipo tipo) throws IOException {}

    @Override
    public void procesa(L_campos campos) throws IOException {}

    @Override
    public void procesa(Un_campo campos) throws IOException {}

    @Override
    public void procesa(Camp campo) throws IOException {}

    @Override
    public void procesa(Si_param lParam) throws IOException {}

    @Override
    public void procesa(No_param lParam) throws IOException {}

    @Override
    public void procesa(L_param lParam) throws IOException {}

    @Override
    public void procesa(Un_param lParam) throws IOException {}

    @Override
    public void procesa(Param_simple param) throws IOException {}

    @Override
    public void procesa(Param_ref param) throws IOException {}

    @Override
    public void procesa(Si_instrs instrs) throws IOException {
        instrs.instrs().procesa(this);
    }

    @Override
    public void procesa(No_instrs instrs) throws IOException {}

    @Override
    public void procesa(L_instrs instrs) throws IOException {
        instrs.instrs().procesa(this);
        instrs.instr().procesa(this);
    }

    @Override
    public void procesa(Una_instr instrs) throws IOException {
        instrs.instr().procesa(this);
    }

    @Override
    public void procesa(Eva instr) throws IOException {
        instr.exp().procesa(this);
        m.emit(m.desapila());
    }

    @Override
    public void procesa(If_instr instr) throws IOException {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        m.emit(m.ir_f(instr.getSig()));
        instr.bloque().procesa(this);
    }

    @Override
    public void procesa(If_el instr) throws IOException {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        m.emit(m.ir_f(instr.getPrim()));
        instr.bloque().procesa(this);
        m.emit(m.ir_a(instr.getSig()));
        instr.bloqueElse().procesa(this);
    }

    @Override
    public void procesa(Wh instr) throws IOException {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        m.emit(m.ir_f(instr.getSig()));
        instr.bloque().procesa(this);
        m.emit(m.ir_a(instr.getPrim()));
    }

    @Override
    public void procesa(Rd instr) throws IOException {
        instr.exp().procesa(this);
        if(claseDe(ref(instr.exp().getTipo()), In_tipo.class))
            m.emit(m.read_int());
        else if(claseDe(ref(instr.exp().getTipo()), R_tipo.class))
            m.emit(m.read_real());
        else if(claseDe(ref(instr.exp().getTipo()), B_tipo.class))
            m.emit(m.read_bool());
        else
            m.emit(m.read_string());
        m.emit(m.desapila_ind());
    }

    @Override
    public void procesa(Wr instr) throws IOException {
        instr.exp().procesa(this);
        if(esDesignador(instr.exp())){
            m.emit(m.apila_ind());
        }
        m.emit(m.write());
    }

    @Override
    public void procesa(Nw instr) throws IOException {
        instr.exp().procesa(this);
        m.emit(m.alloc(ref(ref(instr.exp().getTipo()).tipo()).getTam()));
        m.emit(m.desapila_ind());
    }

    @Override
    public void procesa(Dl instr) throws IOException {
        instr.exp().procesa(this);
        m.emit(m.dup());
        m.emit(m.dealloc(instr.exp().getTipo().getTam()));
        m.emit(m.apila_int(-1));
        m.emit(m.desapila_ind());
    }

    @Override
    public void procesa(Nl_instr instr) throws IOException {
        m.emit(m.apila_string("\n"));
        m.emit(m.write());
    }

    @Override
    public void procesa(Cl instr) throws IOException {
        P_dec pDec = (P_dec) instr.getVinculo();
        m.emit(m.activa(pDec.getNivel(), pDec.getTam(), instr.getSig()));
        if(claseDe(pDec.lParamOpt(), Si_param.class) && claseDe(instr.expsOpt(), Si_exps.class)){
            gen_paso_parametros(pDec.lParamOpt().lParam(), instr.expsOpt().exps());
        }
        m.emit(m.ir_a(pDec.getPrim()));
    }

    private void gen_paso_parametros(LParam lParam, Exps lExps) throws IOException {
        if(claseDe(lParam, L_param.class) && claseDe(lExps, L_exps.class)){
            gen_paso_parametros(lParam.lParam(), lExps.exps());
        }
        m.emit(m.dup());
        m.emit(m.apila_int(lParam.param().getDir()));
        m.emit(m.suma());
        lExps.exp().procesa(this);
        if(claseDe(lParam.param(), Param_ref.class) || !esDesignador(lExps.exp())){
            m.emit(m.desapila_ind());
        }
        else if(claseDe(ref(lParam.param().tipo()), R_tipo.class) && claseDe(ref(lExps.exp().getTipo()), In_tipo.class)){
            m.emit(m.copia_transformando());
        }
        else{
            m.emit(m.copia(lParam.param().tipo().getTam()));
        }
    }

    @Override
    public void procesa(Bq_instr instr) throws IOException {
        instr.bloque().procesa(this);
    }

    @Override
    public void procesa(Si_exps exps) throws IOException { }

    @Override
    public void procesa(No_exps exps) throws IOException { }

    @Override
    public void procesa(L_exps exps) throws IOException { }

    @Override
    public void procesa(Una_exp exps) throws IOException {}

    @Override
    public void procesa(Asig exp) throws IOException {
        exp.opnd0().procesa(this);
        m.emit(m.dup());
        exp.opnd1().procesa(this);
        if(esDesignador(exp.opnd1())){
            if(claseDe(ref(exp.opnd0().getTipo()), R_tipo.class) && claseDe(ref(exp.opnd1().getTipo()), In_tipo.class)){
                m.emit(m.copia_transformando());
            }
            else{
                m.emit(m.copia(exp.opnd0().getTipo().getTam()));
            }
        }
        else{
            if(claseDe(ref(exp.opnd0().getTipo()), R_tipo.class) && claseDe(ref(exp.opnd1().getTipo()), In_tipo.class)){
                m.emit(m.transforma_int());
                m.emit(m.desapila_ind());
            }
            else{
                m.emit(m.desapila_ind());
            }
        }
    }

    @Override
    public void procesa(My exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.my());
    }

    @Override
    public void procesa(Mn exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.mn());
    }

    @Override
    public void procesa(Myig exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.my_ig());
    }

    @Override
    public void procesa(Mnig exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.mn_ig());
    }

    @Override
    public void procesa(Ig exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.ig());
    }

    @Override
    public void procesa(Dif exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.dif());
    }

    @Override
    public void procesa(Suma exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.suma());
    }

    @Override
    public void procesa(Resta exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.resta());
    }

    @Override
    public void procesa(And exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.and());
    }

    @Override
    public void procesa(Or exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.or());
    }

    @Override
    public void procesa(Mul exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.mul());
    }

    @Override
    public void procesa(Div exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.div());
    }

    @Override
    public void procesa(Mod exp) throws IOException {
        gen_cod_opnds(exp.opnd0(), exp.opnd1(), exp);
        m.emit(m.mod());
    }

    @Override
    public void procesa(Menos_unario exp) throws IOException {
        exp.opnd0().procesa(this);
        if(esDesignador(exp.opnd0())){
            m.emit(m.apila_ind());
        }
        m.emit(m.menos_unario());
    }

    @Override
    public void procesa(Not exp) throws IOException {
        exp.opnd0().procesa(this);
        if(esDesignador(exp.opnd0())){
            m.emit(m.apila_ind());
        }
        m.emit(m.not());
    }

    @Override
    public void procesa(Indexacion exp) throws IOException {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if(esDesignador(exp.opnd1())){
            m.emit(m.apila_ind());
        }
        m.emit(m.apila_int(exp.getTipo().getTam()));
        m.emit(m.mul());
        m.emit(m.suma());
    }

    @Override
    public void procesa(Acceso exp) throws IOException {
        exp.opnd0().procesa(this);
        Struct_tipo struct = (Struct_tipo) ref(exp.opnd0().getTipo());
        m.emit(m.apila_int(struct.getDesplazamientoDe(exp.iden())));
        m.emit(m.suma());
    }

    @Override
    public void procesa(Indireccion exp) throws IOException {
        exp.opnd0().procesa(this);
        m.emit(m.apila_ind());
    }

    @Override
    public void procesa(Entero exp) throws IOException {
        m.emit(m.apila_int(Integer.parseInt(exp.valor())));
    }

    @Override
    public void procesa(Real exp) throws IOException {
        m.emit(m.apila_real(Double.parseDouble(exp.valor())));
    }

    @Override
    public void procesa(True exp) throws IOException {
        m.emit(m.apila_bool(true));
    }

    @Override
    public void procesa(False exp) throws IOException {
        m.emit(m.apila_bool(false));
    }

    @Override
    public void procesa(String_exp exp) throws IOException {
        m.emit(m.apila_string(exp.valor()));
    }

    @Override
    public void procesa(Iden exp) throws IOException {
        if(claseDe(exp.getVinculo(), V_dec.class)){
            V_dec vDec = (V_dec) exp.getVinculo();
            gen_acc_id(vDec);
        }
        else if(claseDe(exp.getVinculo(), Param_simple.class)){
            Param_simple param = (Param_simple) exp.getVinculo();
            gen_acc_id(param);
        }
        else{
            Param_ref param = (Param_ref) exp.getVinculo();
            gen_acc_id(param);
        }
    }

    @Override
    public void procesa(Null_exp exp) throws IOException {
        m.emit(m.apila_int(-1));
    }

    private boolean esDesignador(Exp exp){
        if(claseDe(exp, Indireccion.class))
            return esDesignador(exp.opnd0());
        return claseDe(exp, Iden.class) || claseDe(exp, Acceso.class) || claseDe(exp, Indexacion.class) ||
            claseDe(exp, Asig.class);
    }

    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }

    private void gen_cod_opnds(Exp opnd0, Exp opnd1, Exp exp) throws IOException {
        opnd0.procesa(this);
        gen_acc_val(opnd0);
        if(claseDe(exp, Suma.class) || claseDe(exp, Resta.class) || claseDe(exp, Mul.class) || claseDe(exp, Div.class)) {
            if(claseDe(ref(exp.getTipo()), R_tipo.class) && claseDe(ref(opnd0.getTipo()), In_tipo.class)){
                m.emit(m.transforma_int());
            }
        }
        opnd1.procesa(this);
        gen_acc_val(opnd1);
        if(claseDe(exp, Suma.class) || claseDe(exp, Resta.class) || claseDe(exp, Mul.class) || claseDe(exp, Div.class)) {
            if(claseDe(ref(exp.getTipo()), R_tipo.class) && claseDe(ref(opnd1.getTipo()), In_tipo.class)){
                m.emit(m.transforma_int());
            }
        }
    }

    private void gen_acc_id(V_dec dec) {
        if(dec.getNivel() == 0){
            m.emit(m.apila_int(dec.getDir()));
        }
        else gen_acc_var(dec);
    }

    private void gen_acc_id(Param_simple param) {
        gen_acc_var(param);
    }

    private void gen_acc_id(Param_ref param) {
        gen_acc_var(param);
        m.emit(m.apila_ind());
    }

    private void gen_acc_var(V_dec dec) {
        m.emit(m.apilad(dec.getNivel()));
        m.emit(m.apila_int(dec.getDir()));
        m.emit(m.suma());
    }

    private void gen_acc_var(Param_simple param) {
        m.emit(m.apilad(param.getNivel()));
        m.emit(m.apila_int(param.getDir()));
        m.emit(m.suma());
    }

    private void gen_acc_var(Param_ref param) {
        m.emit(m.apilad(param.getNivel()));
        m.emit(m.apila_int(param.getDir()));
        m.emit(m.suma());
    }


    private void gen_acc_val(Exp opnd) {
        if(esDesignador(opnd)){
            m.emit(m.apila_ind());
        }
    }

    private Tipo ref(Tipo t){
        if(claseDe(t, Id_tipo.class)){
            T_dec tDec = (T_dec) t.getVinculo();
            return ref(tDec.tipo());
        }
        else return t;
    }
}
