package model.sintaxis.impresionVisitante;

import javax.sound.sampled.Port;
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

public interface Procesamiento {
    void procesa(Prog prog) throws Exception;
    void procesa(Bloque bloque) throws Exception;
    void procesa(Si_decs decs) throws Exception;
    void procesa(No_decs decs) throws Exception;
    void procesa(L_decs decs) throws Exception;
    void procesa(Una_dec decs) throws Exception;
    void procesa(T_dec dec) throws Exception;
    void procesa(V_dec dec) throws Exception;
    void procesa(P_dec dec) throws Exception;
    void procesa(A_tipo tipo) throws Exception;
    void procesa(P_tipo tipo) throws Exception;
    void procesa(In_tipo tipo) throws Exception;
    void procesa(R_tipo tipo) throws Exception;
    void procesa(B_tipo tipo) throws Exception;
    void procesa(String_tipo tipo) throws Exception;
    void procesa(Id_tipo tipo) throws Exception;
    void procesa(Struct_tipo tipo) throws Exception;
    void procesa(L_campos campos) throws Exception;
    void procesa(Un_campo campos) throws Exception;
    void procesa(Camp campo) throws Exception;
    void procesa(Si_param lParam) throws Exception;
    void procesa(No_param lParam) throws Exception;
    void procesa(L_param lParam) throws Exception;
    void procesa(Un_param lParam) throws Exception;
    void procesa(Param_simple param) throws Exception;
    void procesa(Param_ref param) throws Exception;
    void procesa(Si_instrs instrs) throws Exception;
    void procesa(No_instrs instrs) throws Exception;
    void procesa(L_instrs instrs) throws Exception;
    void procesa(Una_instr instrs) throws Exception;
    void procesa(Eva instr) throws Exception;
    void procesa(If_instr instr) throws Exception;
    void procesa(If_el instr) throws Exception;
    void procesa(Wh instr) throws Exception;
    void procesa(Rd instr) throws Exception;
    void procesa(Wr instr) throws Exception;
    void procesa(Nw instr) throws Exception;
    void procesa(Dl instr) throws Exception;
    void procesa(Nl_instr instr) throws Exception;
    void procesa(Cl instr) throws Exception;
    void procesa(Bq_instr instr) throws Exception;
    void procesa(Si_exps exps) throws Exception;
    void procesa(No_exps exps) throws Exception;
    void procesa(L_exps exps) throws Exception;
    void procesa(Una_exp exps) throws Exception;
    void procesa(Asig exp) throws Exception;
    void procesa(My exp) throws Exception;
    void procesa(Mn exp) throws Exception;
    void procesa(Myig exp) throws Exception;
    void procesa(Mnig exp) throws Exception;
    void procesa(Ig exp) throws Exception;
    void procesa(Dif exp) throws Exception;
    void procesa(Suma exp) throws Exception;
    void procesa(Resta exp) throws Exception;
    void procesa(And exp) throws Exception;
    void procesa(Or exp) throws Exception;
    void procesa(Mul exp) throws Exception;
    void procesa(Div exp) throws Exception;
    void procesa(Mod exp) throws Exception;
    void procesa(Menos_unario exp) throws Exception;
    void procesa(Not exp) throws Exception;
    void procesa(Indexacion exp) throws Exception;
    void procesa(Acceso exp) throws Exception;
    void procesa(Indireccion exp) throws Exception;
    void procesa(Entero exp) throws Exception;
    void procesa(Real exp) throws Exception;
    void procesa(True exp) throws Exception;
    void procesa(False exp) throws Exception;
    void procesa(String_exp exp) throws Exception;
    void procesa(Iden exp) throws Exception;
    void procesa(Null_exp exp) throws Exception;
}
