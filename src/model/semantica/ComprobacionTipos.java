package model.semantica;

import exceptions.TipadoInvalidoExcepcion;
import java.io.IOException;
import model.Procesamiento;
import model.sintaxis.SintaxisAbstracta.Exp;
import model.sintaxis.SintaxisAbstracta.Exps;
import model.sintaxis.SintaxisAbstracta.LParam;
import model.sintaxis.SintaxisAbstracta.Tipo;
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

public class ComprobacionTipos implements Procesamiento {
    @Override
    public void procesa(Prog prog) throws IOException {
        prog.bloque().procesa(this);
        prog.setTipo(prog.bloque().getTipo());
    }

    @Override
    public void procesa(Bloque bloque) throws IOException {
        bloque.decsOpt().procesa(this);
        bloque.instrsOpt().procesa(this);
        bloque.setTipo(
            bloque.decsOpt().getTipo() && bloque.instrsOpt().getTipo()
        );
    }

    @Override
    public void procesa(Si_decs decs) throws IOException {
        decs.decs().procesa(this);
        decs.setTipo(decs.decs().getTipo());
    }

    @Override
    public void procesa(No_decs decs) throws IOException {
        decs.setTipo(true);
    }

    @Override
    public void procesa(L_decs decs) throws IOException {
        decs.decs().procesa(this);
        decs.dec().procesa(this);
        decs.setTipo(
            decs.decs().getTipo() && decs.dec().getTipo()
        );
    }

    @Override
    public void procesa(Una_dec decs) throws IOException {
        decs.dec().procesa(this);
        decs.setTipo(decs.dec().getTipo());
    }

    @Override
    public void procesa(T_dec dec) throws IOException {
        dec.setTipo(true);
    }

    @Override
    public void procesa(V_dec dec) throws IOException {
        dec.setTipo(true);
    }

    @Override
    public void procesa(P_dec dec) throws IOException {
        dec.bloque().procesa(this);
        dec.setTipo(dec.bloque().getTipo());
    }

    @Override
    public void procesa(A_tipo tipo) throws IOException {}

    @Override
    public void procesa(P_tipo tipo) throws IOException {}

    @Override
    public void procesa(In_tipo tipo) throws IOException {}

    @Override
    public void procesa(R_tipo tipo) throws IOException {}

    @Override
    public void procesa(B_tipo tipo) throws IOException {}

    @Override
    public void procesa(String_tipo tipo) throws IOException {}

    @Override
    public void procesa(Id_tipo tipo) throws IOException { }

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
    public void procesa(Un_param lParam) throws IOException { }

    @Override
    public void procesa(Param_simple param) throws IOException {}

    @Override
    public void procesa(Param_ref param) throws IOException {}

    @Override
    public void procesa(Si_instrs instrs) throws IOException {
        instrs.instrs().procesa(this);
        instrs.setTipo(instrs.instrs().getTipo());
    }

    @Override
    public void procesa(No_instrs instrs) throws IOException {
        instrs.setTipo(true);
    }

    @Override
    public void procesa(L_instrs instrs) throws IOException {
        instrs.instr().procesa(this);
        instrs.instr().procesa(this);
        instrs.setTipo(
            instrs.instrs().getTipo() && instrs.instr().getTipo()
        );
    }

    @Override
    public void procesa(Una_instr instrs) throws IOException {
        instrs.instr().procesa(this);
        instrs.setTipo(instrs.instr().getTipo());
    }

    @Override
    public void procesa(Eva instr) throws IOException {
        instr.exp().procesa(this);
        instr.setTipo(true);
    }

    @Override
    public void procesa(If_instr instr) throws IOException {
        instr.exp().procesa(this);
        if(!claseDe(ref(instr.exp().getTipo()), B_tipo.class)){
            throw new TipadoInvalidoExcepcion(ref(instr.exp().getTipo()).getClass(), B_tipo.class);
        }
        instr.bloque().procesa(this);
        instr.setTipo(instr.bloque().getTipo());
    }

    @Override
    public void procesa(If_el instr) throws IOException {
        instr.exp().procesa(this);
        if(!claseDe(ref(instr.exp().getTipo()), B_tipo.class)){
            throw new TipadoInvalidoExcepcion(ref(instr.exp().getTipo()).getClass(), B_tipo.class);
        }
        instr.bloque().procesa(this);
        instr.bloqueElse().procesa(this);
        instr.setTipo(
            instr.bloque().getTipo() && instr.bloqueElse().getTipo()
        );
    }

    @Override
    public void procesa(Wh instr) throws IOException {
        instr.exp().procesa(this);
        if(!claseDe(ref(instr.exp().getTipo()), B_tipo.class)){
            throw new TipadoInvalidoExcepcion(ref(instr.exp().getTipo()).getClass(), B_tipo.class);
        }
        instr.bloque().procesa(this);
        instr.setTipo(instr.bloque().getTipo());
    }

    @Override
    public void procesa(Rd instr) throws IOException {
        instr.exp().procesa(this);
        if(!esDesignador(instr.exp())){
            throw new RuntimeException("Se esperaba un designador");
        }
        if(claseDe(ref(instr.exp().getTipo()), In_tipo.class)){
            instr.setTipo(true);
        }
        else if(claseDe(ref(instr.exp().getTipo()), R_tipo.class)){
            instr.setTipo(true);
        }
        else if(claseDe(ref(instr.exp().getTipo()), B_tipo.class)){
            instr.setTipo(true);
        }
        else if(claseDe(ref(instr.exp().getTipo()), String_tipo.class)){
            instr.setTipo(true);
        }
        else throw new TipadoInvalidoExcepcion(ref(instr.exp().getTipo()).getClass(), In_tipo.class, R_tipo.class,
            B_tipo.class, String_tipo.class);
    }

    @Override
    public void procesa(Wr instr) throws IOException {
        instr.exp().procesa(this);
        if(claseDe(ref(instr.exp().getTipo()), In_tipo.class)){
            instr.setTipo(true);
        }
        else if(claseDe(ref(instr.exp().getTipo()), R_tipo.class)){
            instr.setTipo(true);
        }
        else if(claseDe(ref(instr.exp().getTipo()), B_tipo.class)){
            instr.setTipo(true);
        }
        else if(claseDe(ref(instr.exp().getTipo()), String_tipo.class)){
            instr.setTipo(true);
        }
        else throw new TipadoInvalidoExcepcion(ref(instr.exp().getTipo()).getClass(), In_tipo.class, R_tipo.class,
            B_tipo.class, String_tipo.class);
    }

    @Override
    public void procesa(Nw instr) throws IOException {
        instr.exp().procesa(this);
        if(!claseDe(ref(instr.exp().getTipo()), P_tipo.class))
            throw new TipadoInvalidoExcepcion(ref(instr.exp().getTipo()).getClass(), P_tipo.class);
        instr.setTipo(true);
    }

    @Override
    public void procesa(Dl instr) throws IOException {
        instr.exp().procesa(this);
        if(!claseDe(ref(instr.exp().getTipo()), P_tipo.class))
            throw new TipadoInvalidoExcepcion(ref(instr.exp().getTipo()).getClass(), P_tipo.class);
        instr.setTipo(true);
    }

    @Override
    public void procesa(Nl_instr instr) throws IOException {
        instr.setTipo(true);
    }

    @Override
    public void procesa(Cl instr) throws IOException {
        P_dec pDec = (P_dec) instr.getVinculo();
        if(claseDe(pDec.lParamOpt(), Si_param.class) && claseDe(instr.expsOpt(), Si_exps.class)){
            instr.setTipo(compruebaParametros(pDec.lParamOpt().lParam(), instr.expsOpt().exps()));
        }
        else if (claseDe(pDec.lParamOpt(), No_param.class) && claseDe(instr.expsOpt(), No_exps.class)){
            instr.setTipo(true);
        }
        else if(claseDe(pDec.lParamOpt(), Si_param.class))
            throw new RuntimeException("Se esperaban parámetros pero no se ha pasado ni uno");
        else
            throw new RuntimeException("No esperaban parámetros pero se ha pasado alguno");
    }

    private boolean compruebaParametros(LParam lParam, Exps exps) throws IOException {
        exps.exp().procesa(this);
        if(claseDe(lParam, L_param.class) && claseDe(exps, L_exps.class)){
            if(claseDe(lParam.param(), Param_simple.class) && compatible(lParam.param().tipo(), exps.exp().getTipo())){
                return compruebaParametros(lParam.lParam(), exps.exps());
            }
            else if(esDesignador(exps.exp()) && compatible(lParam.param().tipo(), exps.exp().getTipo())){
                return compruebaParametros(lParam.lParam(), exps.exps());
            }
            throw new RuntimeException("Se esperaba un designador");
        }
        else if(claseDe(lParam, Un_param.class) && claseDe(exps, Una_exp.class)){
            if(claseDe(lParam.param(), Param_simple.class) && compatible(lParam.param().tipo(), exps.exp().getTipo())){
                return true;
            }
            else if(esDesignador(exps.exp()) && compatible(lParam.param().tipo(), exps.exp().getTipo())){
                return true;
            }
            throw new RuntimeException("Se esperaba un designador");
        }
        else throw new RuntimeException("El número de argumentos no coincide con el número de parámetros");
    }

    @Override
    public void procesa(Bq_instr instr) throws IOException {
        instr.bloque().procesa(this);
        instr.setTipo(instr.bloque().getTipo());
    }

    @Override
    public void procesa(Si_exps exps) throws IOException {
        exps.exps().procesa(this);
        exps.setTipo(exps.exps().getTipo());
    }

    @Override
    public void procesa(No_exps exps) throws IOException {
        exps.setTipo(true);
    }

    @Override
    public void procesa(L_exps exps) throws IOException {
        exps.exps().procesa(this);
        exps.exp().procesa(this);
        exps.setTipo(
            exps.exps().getTipo() && exps.exp().getTipo() != null
        );
    }

    @Override
    public void procesa(Una_exp exps) throws IOException {
        exps.exp().procesa(this);
        exps.setTipo(
            exps.exp().getTipo() != null
        );
    }

    @Override
    public void procesa(Asig exp) throws IOException {

    }

    @Override
    public void procesa(My exp) throws IOException {

    }

    @Override
    public void procesa(Mn exp) throws IOException {

    }

    @Override
    public void procesa(Myig exp) throws IOException {

    }

    @Override
    public void procesa(Mnig exp) throws IOException {

    }

    @Override
    public void procesa(Ig exp) throws IOException {

    }

    @Override
    public void procesa(Dif exp) throws IOException {

    }

    @Override
    public void procesa(Suma exp) throws IOException {

    }

    @Override
    public void procesa(Resta exp) throws IOException {

    }

    @Override
    public void procesa(And exp) throws IOException {

    }

    @Override
    public void procesa(Or exp) throws IOException {

    }

    @Override
    public void procesa(Mul exp) throws IOException {

    }

    @Override
    public void procesa(Div exp) throws IOException {

    }

    @Override
    public void procesa(Mod exp) throws IOException {

    }

    @Override
    public void procesa(Menos_unario exp) throws IOException {

    }

    @Override
    public void procesa(Not exp) throws IOException {

    }

    @Override
    public void procesa(Indexacion exp) throws IOException {

    }

    @Override
    public void procesa(Acceso exp) throws IOException {

    }

    @Override
    public void procesa(Indireccion exp) throws IOException {

    }

    @Override
    public void procesa(Entero exp) throws IOException {

    }

    @Override
    public void procesa(Real exp) throws IOException {

    }

    @Override
    public void procesa(True exp) throws IOException {

    }

    @Override
    public void procesa(False exp) throws IOException {

    }

    @Override
    public void procesa(String_exp exp) throws IOException {

    }

    @Override
    public void procesa(Iden exp) throws IOException {

    }

    @Override
    public void procesa(Null_exp exp) throws IOException {
        exp.setTipo(new P_tipo(null));
    }

    private Tipo ref(Tipo t){
        if(claseDe(t, Id_tipo.class)){
            return ref(t.tipo());
        }
        else return t;
    }

    private boolean esDesignador(Exp exp){
        return claseDe(exp, Iden.class) || claseDe(exp, Acceso.class) || claseDe(exp, Indexacion.class) ||
            claseDe(exp, Asig.class);
    }

    private boolean compatible(Tipo a, Tipo b){
        return false;
    }

    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }
}
