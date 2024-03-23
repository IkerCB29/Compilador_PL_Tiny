package model.sintaxis;

public class SintaxisAbstracta {
    public static abstract class Nodo {
        public Nodo() {
            fila=col=-1;
        }
        private int fila;
        private int col;
        public Nodo ponFila(int fila) {
            this.fila = fila;
            return this;
        }
        public Nodo ponCol(int col) {
            this.col = col;
            return this;
        }
        public int leeFila() {
            return fila;
        }
        public int leeCol() {
            return col;
        }
    }

    /*
      Géneros
    */
    public static abstract class Decs_opt extends Nodo {
        public Decs_opt() { super(); }
    }

    public static abstract class Decs extends Nodo {
        public Decs() { super(); }
    }

    public static abstract class Dec extends Nodo {
        public Dec() { super(); }
    }

    public static abstract class Tipo extends Nodo {
        public Tipo() { super(); }
    }

    public static abstract class Campos extends Nodo {
        public Campos() { super(); }
    }

    public static abstract class Campo extends Nodo {
        public Campo() { super(); }
    }

    public static abstract class LParam_opt extends Nodo {
        public LParam_opt() { super(); }
    }

    public static abstract class LParam extends Nodo {
        public LParam() { super(); }
    }

    public static abstract class Param extends Nodo {
        public Param() { super(); }
    }

    public static abstract class Instrs_opt extends Nodo {
        public Instrs_opt() { super(); }
    }

    public static abstract class Instrs extends Nodo {
        public Instrs() { super(); }
    }

    public static abstract class Instr extends Nodo {
        public Instr() { super(); }
    }

    public static abstract class Exps_opt extends Nodo {
        public Exps_opt() { super(); }
    }

    public static abstract class LExps extends Nodo {
        public LExps() { super(); }
    }

    public static abstract class Exp extends Nodo {
        public Exp() { super(); }
    }

    /*
      Constructores
    */
    public static class Prog extends Nodo {
        private Bloque bloque;
        public Prog(Bloque bloque) {
            super();
            this.bloque = bloque;
        }
        public String toString() {
            return "prog("+bloque+")";
        }
    }

    public static class Bloque extends Nodo {
        private Decs_opt decs;
        private Instrs_opt instrs;
        public Bloque(Decs_opt decs, Instrs_opt instrs) {
            super();
            this.decs = decs;
            this.instrs = instrs;
        }
        public String toString() {
            return "bloq("+decs+","+instrs+")";
        }
    }

    public static class Si_decs extends Decs_opt {
        private Decs decs;
        public Si_decs(Decs decs) {
            super();
            this.decs = decs;
        }
        public String toString() {
            return "si_decs("+decs+")";
        }
    }

    public static class No_decs extends Decs_opt {
        public No_decs() {
            super();
        }
        public String toString() {
            return "no_decs()";
        }
    }

    public static class L_decs extends Decs {
        private Decs decs;
        private Dec dec;
        public L_decs(Decs decs, Dec dec) {
            super();
            this.decs = decs;
            this.dec = dec;
        }
        public String toString() {
            return "l_decs("+decs+","+dec+")";
        }
    }

    public static class Una_dec extends Decs {
        private Dec dec;
        public Una_dec(Dec dec) {
            super();
            this.dec = dec;
        }
        public String toString() {
            return "una_dec("+dec+")";
        }
    }

    public static class T_dec extends Dec {
        private Tipo tipo;
        private String iden;
        public T_dec(Tipo tipo, String iden) {
            super();
            this.tipo = tipo;
            this.iden = iden;
        }
        public String toString() {
            return "t_decs("+tipo+","+iden+")";
        }
    }

    public static class V_dec extends Dec {
        private Tipo tipo;
        private String iden;
        public V_dec(Tipo tipo, String iden) {
            super();
            this.tipo = tipo;
            this.iden = iden;
        }
        public String toString() {
            return "v_decs("+tipo+","+iden+")";
        }
    }

    public static class P_dec extends Dec {
        private Tipo tipo;
        private LParam_opt param;
        private Bloque bloque;
        public P_dec(Tipo tipo, LParam_opt param, Bloque bloque) {
            super();
            this.tipo = tipo;
            this.param = param;
            this.bloque = bloque;
        }
        public String toString() {
            return "p_decs("+tipo+","+param+","+bloque+")";
        }
    }

    public static class A_tipo extends Tipo {
        private Tipo tipo;
        private String capacidad;
        public A_tipo(Tipo tipo, String capacidad) {
            super();
            this.tipo = tipo;
            this.capacidad = capacidad;
        }
        public String toString() {
            return "a_tipo("+tipo+","+capacidad+")";
        }
    }

    public static class P_tipo extends Tipo {
        private Tipo tipo;
        public P_tipo(Tipo tipo) {
            super();
            this.tipo = tipo;
        }
        public String toString() {
            return "p_tipo("+tipo+")";
        }
    }

    public static class In_tipo extends Tipo {
        public In_tipo() {
            super();
        }
        public String toString() {
            return "in_tipo()";
        }
    }

    public static class R_tipo extends Tipo {
        public R_tipo() {
            super();
        }
        public String toString() {
            return "r_tipo()";
        }
    }

    public static class B_tipo extends Tipo {
        public B_tipo() {
            super();
        }
        public String toString() {
            return "b_tipo()";
        }
    }

    public static class String_tipo extends Tipo {
        public String_tipo() {
            super();
        }
        public String toString() {
            return "string_tipo()";
        }
    }

    public static class Id_tipo extends Tipo {
        private String iden;
        public Id_tipo(String iden) {
            super();
            this.iden = iden;
        }
        public String toString() {
            return "id_tipo("+iden+")";
        }
    }

    public static class Struct_tipo extends Tipo {
        private Campos campos;
        public Struct_tipo(Campos campos) {
            super();
            this.campos = campos;
        }
        public String toString() {
            return "struct_tipo("+campos+")";
        }
    }

    public static class L_campos extends Campos {
        private Campos campos;
        private Campo campo;
        public L_campos(Campos campos, Campo campo) {
            super();
            this.campos = campos;
            this.campo = campo;
        }
        public String toString() {
            return "l_campos("+campos+","+campo+")";
        }
    }

    public static class Un_campo extends Campos {
        private Campo campo;
        public Un_campo(Campo campo) {
            super();
            this.campo = campo;
        }
        public String toString() {
            return "un_campo("+campo+")";
        }
    }

    public static class Camp extends Campo {
        private Tipo tipo;
        private String iden;
        public Camp(Tipo tipo, String iden) {
            super();
            this.tipo = tipo;
            this.iden = iden;
        }
        public String toString() {
            return "camp("+tipo+","+iden+")";
        }
    }

    public static class Si_param extends LParam_opt {
        private LParam lParam;
        public Si_param(LParam lParam) {
            super();
            this.lParam = lParam;
        }
        public String toString() {
            return "si_param("+lParam+")";
        }
    }

    public static class No_param extends LParam_opt {
        public No_param() {
            super();
        }
        public String toString() {
            return "no_param()";
        }
    }

    public static class L_param extends LParam {
        private L_param lParam;
        private Param param;
        public L_param(L_param lParam, Param param) {
            super();
            this.lParam = lParam;
            this.param = param;
        }
        public String toString() {
            return "l_param("+lParam+","+param+")";
        }
    }

    public static class Un_param extends LParam {
        private Param param;
        public Un_param(Param param) {
            super();
            this.param = param;
        }
        public String toString() {
            return "un_param("+param+")";
        }
    }

    public static class Param_simple extends Param{
        private Tipo tipo;
        private String iden;
        public Param_simple(Tipo tipo, String iden) {
            super();
            this.tipo = tipo;
            this.iden = iden;
        }
        public String toString() {
            return "param_simple("+tipo+","+iden+")";
        }
    }

    public static class Param_ref extends Param{
        private Tipo tipo;
        private String iden;
        public Param_ref(Tipo tipo, String iden) {
            super();
            this.tipo = tipo;
            this.iden = iden;
        }
        public String toString() {
            return "param_ref("+tipo+","+iden+")";
        }
    }

    public static class Si_instrs extends Instrs_opt {
        private Instrs instrs;
        public Si_instrs(Instrs instrs) {
            super();
            this.instrs = instrs;
        }
        public String toString() {
            return "si_instrs("+instrs+")";
        }
    }

    public static class No_instrs extends Instrs_opt {
        public No_instrs() {
            super();
        }
        public String toString() {
            return "no_instrs()";
        }
    }

    public static class L_instrs extends Instrs {
        private Instrs instrs;
        private Instr instr;
        public L_instrs(Instrs instrs, Instr instr) {
            super();
            this.instrs = instrs;
            this.instr = instr;
        }
        public String toString() {
            return "l_instrs("+instrs+","+instr+")";
        }
    }

    public static class Una_instr extends Instrs {
        private Instr instr;
        public Una_instr(Instr instr) {
            super();
            this.instr = instr;
        }
        public String toString() {
            return "una_instr("+instr+")";
        }
    }

    public static class Eva extends Instr {
        private Exp exp;
        public Eva(Exp exp) {
            super();
            this.exp = exp;
        }
        public String toString() {
            return "eva("+exp+")";
        }
    }

    public static class If_instr extends Instr {
        private Exp exp;
        private Bloque bloque;
        public If_instr(Exp exp, Bloque bloque) {
            super();
            this.exp = exp;
            this.bloque = bloque;
        }
        public String toString() {
            return "if("+exp+","+bloque+")";
        }
    }

    public static class If_el extends Instr {
        private Exp exp;
        private Bloque bloqueIf;
        private Bloque bloqueElse;
        public If_el(Exp exp, Bloque bloqueIf, Bloque bloqueElse) {
            super();
            this.exp = exp;
            this.bloqueIf = bloqueIf;
            this.bloqueElse = bloqueElse;
        }
        public String toString() {
            return "if_el("+exp+","+bloqueIf+","+bloqueElse+")";
        }
    }

    public static class Wh extends Instr {
        private Exp exp;
        private Bloque bloque;
        public Wh(Exp exp, Bloque bloque) {
            super();
            this.exp = exp;
            this.bloque = bloque;
        }
        public String toString() {
            return "wh("+exp+","+bloque+")";
        }
    }

    public static class Rd extends Instr {
        private Exp exp;
        public Rd(Exp exp) {
            super();
            this.exp = exp;
        }
        public String toString() {
            return "rd("+exp+")";
        }
    }

    public static class Wr extends Instr {
        private Exp exp;
        public Wr(Exp exp) {
            super();
            this.exp = exp;
        }
        public String toString() {
            return "wr("+exp+")";
        }
    }

    public static class Nw extends Instr {
        private Exp exp;
        public Nw(Exp exp) {
            super();
            this.exp = exp;
        }
        public String toString() {
            return "nw("+exp+")";
        }
    }

    public static class Dl extends Instr {
        private Exp exp;
        public Dl(Exp exp) {
            super();
            this.exp = exp;
        }
        public String toString() {
            return "dl("+exp+")";
        }
    }

    public static class Nl_instr extends Instr {
        public Nl_instr() {
            super();
        }
        public String toString() {
            return "nl()";
        }
    }

    public static class Cl extends Instr {
        private String iden;
        private Exps_opt exps;
        public Cl(String iden, Exps_opt exps) {
            super();
            this.iden = iden;
            this.exps = exps;
        }
        public String toString() {
            return "cl("+iden+","+exps+")";
        }
    }

    public static class Bq_instr extends Instr {
        private Bloque bloque;
        public Bq_instr(Bloque bloque) {
            super();
            this.bloque = bloque;
        }
        public String toString() {
            return "bq_instr("+bloque+")";
        }
    }


}
