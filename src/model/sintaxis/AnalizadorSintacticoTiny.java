/* Generated By:JavaCC: Do not edit this line. AnalizadorSintacticoTiny.java */
package model.sintaxis;

public class AnalizadorSintacticoTiny implements AnalizadorSintacticoTinyConstants {
            protected   void newToken(Token t) {}

  final public void analiza() throws ParseException {
    trace_call("analiza");
    try {
      programa();
      jj_consume_token(0);
    } finally {
      trace_return("analiza");
    }
  }

  final public void programa() throws ParseException {
    trace_call("programa");
    try {
      bloque();
    } finally {
      trace_return("programa");
    }
  }

  final public void bloque() throws ParseException {
    trace_call("bloque");
    try {
      jj_consume_token(LlaveApertura);
      declaraciones_opt();
      instrucciones_opt();
      jj_consume_token(LlaveCierre);
    } finally {
      trace_return("bloque");
    }
  }

  final public void declaraciones_opt() throws ParseException {
    trace_call("declaraciones_opt");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Entero:
      case Real:
      case Boolean:
      case String:
      case Puntero:
      case Proc:
      case Struct:
      case Type:
      case Identificador:
        declaraciones();
        jj_consume_token(FinDeclaracion);
        break;
      default:
        jj_la1[0] = jj_gen;

      }
    } finally {
      trace_return("declaraciones_opt");
    }
  }

  final public void declaraciones() throws ParseException {
    trace_call("declaraciones");
    try {
      declaración();
      r_declaraciones();
    } finally {
      trace_return("declaraciones");
    }
  }

  final public void r_declaraciones() throws ParseException {
    trace_call("r_declaraciones");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PyC:
        jj_consume_token(PyC);
        declaración();
        r_declaraciones();
        break;
      default:
        jj_la1[1] = jj_gen;

      }
    } finally {
      trace_return("r_declaraciones");
    }
  }

  final public void declaración() throws ParseException {
    trace_call("declaración");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Entero:
      case Real:
      case Boolean:
      case String:
      case Puntero:
      case Struct:
      case Identificador:
        dec_variable();
        break;
      case Type:
        dec_tipo();
        break;
      case Proc:
        dec_procedimiento();
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("declaración");
    }
  }

  final public void dec_tipo() throws ParseException {
    trace_call("dec_tipo");
    try {
      jj_consume_token(Type);
      tipo0();
      jj_consume_token(Identificador);
    } finally {
      trace_return("dec_tipo");
    }
  }

  final public void dec_variable() throws ParseException {
    trace_call("dec_variable");
    try {
      tipo0();
      jj_consume_token(Identificador);
    } finally {
      trace_return("dec_variable");
    }
  }

  final public void tipo0() throws ParseException {
    trace_call("tipo0");
    try {
      tipo1();
      r_tipo0();
    } finally {
      trace_return("tipo0");
    }
  }

  final public void r_tipo0() throws ParseException {
    trace_call("r_tipo0");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CorcheteApertura:
        jj_consume_token(CorcheteApertura);
        jj_consume_token(LiteralEntero);
        jj_consume_token(CorcheteCierro);
        r_tipo0();
        break;
      default:
        jj_la1[3] = jj_gen;

      }
    } finally {
      trace_return("r_tipo0");
    }
  }

  final public void tipo1() throws ParseException {
    trace_call("tipo1");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Puntero:
        jj_consume_token(Puntero);
        tipo1();
        break;
      case Entero:
      case Real:
      case Boolean:
      case String:
      case Struct:
      case Identificador:
        tipo2();
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("tipo1");
    }
  }

  final public void tipo2() throws ParseException {
    trace_call("tipo2");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Entero:
        jj_consume_token(Entero);
        break;
      case Real:
        jj_consume_token(Real);
        break;
      case Boolean:
        jj_consume_token(Boolean);
        break;
      case String:
        jj_consume_token(String);
        break;
      case Identificador:
        jj_consume_token(Identificador);
        break;
      case Struct:
        registro();
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("tipo2");
    }
  }

  final public void registro() throws ParseException {
    trace_call("registro");
    try {
      jj_consume_token(Struct);
      jj_consume_token(LlaveApertura);
      campos();
      jj_consume_token(LlaveCierre);
    } finally {
      trace_return("registro");
    }
  }

  final public void campos() throws ParseException {
    trace_call("campos");
    try {
      campo();
      r_campos();
    } finally {
      trace_return("campos");
    }
  }

  final public void r_campos() throws ParseException {
    trace_call("r_campos");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Coma:
        jj_consume_token(Coma);
        campo();
        r_campos();
        break;
      default:
        jj_la1[6] = jj_gen;

      }
    } finally {
      trace_return("r_campos");
    }
  }

  final public void campo() throws ParseException {
    trace_call("campo");
    try {
      dec_variable();
    } finally {
      trace_return("campo");
    }
  }

  final public void dec_procedimiento() throws ParseException {
    trace_call("dec_procedimiento");
    try {
      jj_consume_token(Proc);
      jj_consume_token(Identificador);
      parametros_formales();
      bloque();
    } finally {
      trace_return("dec_procedimiento");
    }
  }

  final public void parametros_formales() throws ParseException {
    trace_call("parametros_formales");
    try {
      jj_consume_token(ParAp);
      lista_parametros_opt();
      jj_consume_token(ParCierre);
    } finally {
      trace_return("parametros_formales");
    }
  }

  final public void lista_parametros_opt() throws ParseException {
    trace_call("lista_parametros_opt");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Entero:
      case Real:
      case Boolean:
      case String:
      case Puntero:
      case Struct:
      case Identificador:
        lista_parametros();
        break;
      default:
        jj_la1[7] = jj_gen;

      }
    } finally {
      trace_return("lista_parametros_opt");
    }
  }

  final public void lista_parametros() throws ParseException {
    trace_call("lista_parametros");
    try {
      parametro();
      r_lista_parametros();
    } finally {
      trace_return("lista_parametros");
    }
  }

  final public void r_lista_parametros() throws ParseException {
    trace_call("r_lista_parametros");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Coma:
        jj_consume_token(Coma);
        parametro();
        r_lista_parametros();
        break;
      default:
        jj_la1[8] = jj_gen;

      }
    } finally {
      trace_return("r_lista_parametros");
    }
  }

  final public void parametro() throws ParseException {
    trace_call("parametro");
    try {
      tipo0();
      r_parametro();
    } finally {
      trace_return("parametro");
    }
  }

  final public void r_parametro() throws ParseException {
    trace_call("r_parametro");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Identificador:
        jj_consume_token(Identificador);
        break;
      case Referencia:
        jj_consume_token(Referencia);
        jj_consume_token(Identificador);
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("r_parametro");
    }
  }

  final public void instrucciones_opt() throws ParseException {
    trace_call("instrucciones_opt");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LlaveApertura:
      case Evalua:
      case If:
      case While:
      case New:
      case Delete:
      case Read:
      case Write:
      case Nl:
      case Call:
        instrucciones();
        break;
      default:
        jj_la1[10] = jj_gen;

      }
    } finally {
      trace_return("instrucciones_opt");
    }
  }

  final public void instrucciones() throws ParseException {
    trace_call("instrucciones");
    try {
      instruccion();
      r_instrucciones();
    } finally {
      trace_return("instrucciones");
    }
  }

  final public void r_instrucciones() throws ParseException {
    trace_call("r_instrucciones");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PyC:
        jj_consume_token(PyC);
        instruccion();
        r_instrucciones();
        break;
      default:
        jj_la1[11] = jj_gen;

      }
    } finally {
      trace_return("r_instrucciones");
    }
  }

  final public void instruccion() throws ParseException {
    trace_call("instruccion");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Evalua:
        instruccion_eval();
        break;
      case If:
        instruccion_if();
        break;
      case While:
        instruccion_while();
        break;
      case Read:
        instruccion_read();
        break;
      case Write:
        instruccion_write();
        break;
      case Nl:
        instruccion_nl();
        break;
      case New:
        instruccion_new();
        break;
      case Delete:
        instruccion_delete();
        break;
      case Call:
        instruccion_call();
        break;
      case LlaveApertura:
        instruccion_compuesta();
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("instruccion");
    }
  }

  final public void instruccion_eval() throws ParseException {
    trace_call("instruccion_eval");
    try {
      jj_consume_token(Evalua);
      E0();
    } finally {
      trace_return("instruccion_eval");
    }
  }

  final public void instruccion_if() throws ParseException {
    trace_call("instruccion_if");
    try {
      jj_consume_token(If);
      E0();
      bloque();
      r_instruccion_if();
    } finally {
      trace_return("instruccion_if");
    }
  }

  final public void r_instruccion_if() throws ParseException {
    trace_call("r_instruccion_if");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Else:
        jj_consume_token(Else);
        bloque();
        break;
      default:
        jj_la1[13] = jj_gen;

      }
    } finally {
      trace_return("r_instruccion_if");
    }
  }

  final public void instruccion_while() throws ParseException {
    trace_call("instruccion_while");
    try {
      jj_consume_token(While);
      E0();
      bloque();
    } finally {
      trace_return("instruccion_while");
    }
  }

  final public void instruccion_read() throws ParseException {
    trace_call("instruccion_read");
    try {
      jj_consume_token(Read);
      E0();
    } finally {
      trace_return("instruccion_read");
    }
  }

  final public void instruccion_write() throws ParseException {
    trace_call("instruccion_write");
    try {
      jj_consume_token(Write);
      E0();
    } finally {
      trace_return("instruccion_write");
    }
  }

  final public void instruccion_nl() throws ParseException {
    trace_call("instruccion_nl");
    try {
      jj_consume_token(Nl);
    } finally {
      trace_return("instruccion_nl");
    }
  }

  final public void instruccion_new() throws ParseException {
    trace_call("instruccion_new");
    try {
      jj_consume_token(New);
      E0();
    } finally {
      trace_return("instruccion_new");
    }
  }

  final public void instruccion_delete() throws ParseException {
    trace_call("instruccion_delete");
    try {
      jj_consume_token(Delete);
      E0();
    } finally {
      trace_return("instruccion_delete");
    }
  }

  final public void instruccion_call() throws ParseException {
    trace_call("instruccion_call");
    try {
      jj_consume_token(Call);
      jj_consume_token(Identificador);
      jj_consume_token(ParAp);
      lista_expresiones_opt();
      jj_consume_token(ParCierre);
    } finally {
      trace_return("instruccion_call");
    }
  }

  final public void instruccion_compuesta() throws ParseException {
    trace_call("instruccion_compuesta");
    try {
      bloque();
    } finally {
      trace_return("instruccion_compuesta");
    }
  }

  final public void lista_expresiones_opt() throws ParseException {
    trace_call("lista_expresiones_opt");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LiteralEntero:
      case LiteralReal:
      case LiteralTrue:
      case LiteralFalse:
      case LiteralString:
      case Resta:
      case Not:
      case ParAp:
      case Null:
      case Identificador:
        lista_expresiones();
        break;
      default:
        jj_la1[14] = jj_gen;

      }
    } finally {
      trace_return("lista_expresiones_opt");
    }
  }

  final public void lista_expresiones() throws ParseException {
    trace_call("lista_expresiones");
    try {
      E0();
      r_lista_expresiones();
    } finally {
      trace_return("lista_expresiones");
    }
  }

  final public void r_lista_expresiones() throws ParseException {
    trace_call("r_lista_expresiones");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Coma:
        jj_consume_token(Coma);
        E0();
        r_lista_expresiones();
        break;
      default:
        jj_la1[15] = jj_gen;

      }
    } finally {
      trace_return("r_lista_expresiones");
    }
  }

  final public void E0() throws ParseException {
    trace_call("E0");
    try {
      E1();
      RE0();
    } finally {
      trace_return("E0");
    }
  }

  final public void RE0() throws ParseException {
    trace_call("RE0");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Asignacion:
        jj_consume_token(Asignacion);
        E0();
        break;
      default:
        jj_la1[16] = jj_gen;

      }
    } finally {
      trace_return("RE0");
    }
  }

  final public void E1() throws ParseException {
    trace_call("E1");
    try {
      E2();
      RE1();
    } finally {
      trace_return("E1");
    }
  }

  final public void RE1() throws ParseException {
    trace_call("RE1");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MenorQue:
      case MayorQue:
      case MenorOIgualQue:
      case MayorOIgualQue:
      case Igual:
      case Diferente:
        OP1();
        E2();
        RE1();
        break;
      default:
        jj_la1[17] = jj_gen;

      }
    } finally {
      trace_return("RE1");
    }
  }

  final public void E2() throws ParseException {
    trace_call("E2");
    try {
      E3();
      R2E2();
      R1E2();
    } finally {
      trace_return("E2");
    }
  }

  final public void R1E2() throws ParseException {
    trace_call("R1E2");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Suma:
        jj_consume_token(Suma);
        E3();
        R1E2();
        break;
      default:
        jj_la1[18] = jj_gen;

      }
    } finally {
      trace_return("R1E2");
    }
  }

  final public void R2E2() throws ParseException {
    trace_call("R2E2");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Resta:
        jj_consume_token(Resta);
        E3();
        break;
      default:
        jj_la1[19] = jj_gen;

      }
    } finally {
      trace_return("R2E2");
    }
  }

  final public void E3() throws ParseException {
    trace_call("E3");
    try {
      E4();
      RE3();
    } finally {
      trace_return("E3");
    }
  }

  final public void RE3() throws ParseException {
    trace_call("RE3");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case And:
        jj_consume_token(And);
        E3();
        break;
      case Or:
        jj_consume_token(Or);
        E4();
        break;
      default:
        jj_la1[20] = jj_gen;

      }
    } finally {
      trace_return("RE3");
    }
  }

  final public void E4() throws ParseException {
    trace_call("E4");
    try {
      E5();
      RE4();
    } finally {
      trace_return("E4");
    }
  }

  final public void RE4() throws ParseException {
    trace_call("RE4");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Mul:
      case Div:
      case Modulo:
        OP4();
        E5();
        RE4();
        break;
      default:
        jj_la1[21] = jj_gen;

      }
    } finally {
      trace_return("RE4");
    }
  }

  final public void E5() throws ParseException {
    trace_call("E5");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Resta:
      case Not:
        OP5();
        E5();
        break;
      case LiteralEntero:
      case LiteralReal:
      case LiteralTrue:
      case LiteralFalse:
      case LiteralString:
      case ParAp:
      case Null:
      case Identificador:
        E6();
        break;
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("E5");
    }
  }

  final public void E6() throws ParseException {
    trace_call("E6");
    try {
      E7();
      RE6();
    } finally {
      trace_return("E6");
    }
  }

  final public void RE6() throws ParseException {
    trace_call("RE6");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Puntero:
      case CorcheteApertura:
      case Acceso:
        OP6();
        RE6();
        break;
      default:
        jj_la1[23] = jj_gen;

      }
    } finally {
      trace_return("RE6");
    }
  }

  final public void E7() throws ParseException {
    trace_call("E7");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ParAp:
        jj_consume_token(ParAp);
        E0();
        jj_consume_token(ParCierre);
        break;
      case LiteralEntero:
      case LiteralReal:
      case LiteralTrue:
      case LiteralFalse:
      case LiteralString:
      case Null:
      case Identificador:
        operando();
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("E7");
    }
  }

  final public void OP1() throws ParseException {
    trace_call("OP1");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MayorQue:
        jj_consume_token(MayorQue);
        break;
      case MenorQue:
        jj_consume_token(MenorQue);
        break;
      case MenorOIgualQue:
        jj_consume_token(MenorOIgualQue);
        break;
      case MayorOIgualQue:
        jj_consume_token(MayorOIgualQue);
        break;
      case Igual:
        jj_consume_token(Igual);
        break;
      case Diferente:
        jj_consume_token(Diferente);
        break;
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("OP1");
    }
  }

  final public void OP4() throws ParseException {
    trace_call("OP4");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Mul:
        jj_consume_token(Mul);
        break;
      case Div:
        jj_consume_token(Div);
        break;
      case Modulo:
        jj_consume_token(Modulo);
        break;
      default:
        jj_la1[26] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("OP4");
    }
  }

  final public void OP5() throws ParseException {
    trace_call("OP5");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Resta:
        jj_consume_token(Resta);
        break;
      case Not:
        jj_consume_token(Not);
        break;
      default:
        jj_la1[27] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("OP5");
    }
  }

  final public void OP6() throws ParseException {
    trace_call("OP6");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CorcheteApertura:
        jj_consume_token(CorcheteApertura);
        E0();
        jj_consume_token(CorcheteCierro);
        break;
      case Acceso:
        acceso_registro();
        break;
      case Puntero:
        jj_consume_token(Puntero);
        break;
      default:
        jj_la1[28] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("OP6");
    }
  }

  final public void acceso_registro() throws ParseException {
    trace_call("acceso_registro");
    try {
      jj_consume_token(Acceso);
      jj_consume_token(Identificador);
    } finally {
      trace_return("acceso_registro");
    }
  }

  final public void operando() throws ParseException {
    trace_call("operando");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LiteralReal:
        jj_consume_token(LiteralReal);
        break;
      case LiteralEntero:
        jj_consume_token(LiteralEntero);
        break;
      case LiteralTrue:
        jj_consume_token(LiteralTrue);
        break;
      case LiteralFalse:
        jj_consume_token(LiteralFalse);
        break;
      case LiteralString:
        jj_consume_token(LiteralString);
        break;
      case Identificador:
        jj_consume_token(Identificador);
        break;
      case Null:
        jj_consume_token(Null);
        break;
      default:
        jj_la1[29] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("operando");
    }
  }

  /** Generated Token Manager. */
  public AnalizadorSintacticoTinyTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[30];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1f00,0x0,0x1f00,0x0,0x1f00,0xf00,0x0,0x1f00,0x0,0x2000,0x0,0x0,0x0,0x0,0x417c000,0x0,0x0,0xf8000000,0x80000,0x100000,0x3000000,0xe00000,0x417c000,0x1000,0x7c000,0xf8000000,0xe00000,0x4100000,0x1000,0x7c000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x5044000,0x4,0x5044000,0x20,0x4040000,0x4040000,0x400,0x4040000,0x400,0x4000000,0x2fa9080,0x4,0x2fa9080,0x10000,0x4002008,0x400,0x2,0x1,0x0,0x0,0x0,0x0,0x4002008,0x220,0x4002008,0x1,0x0,0x0,0x220,0x4002000,};
   }

  /** Constructor with InputStream. */
  public AnalizadorSintacticoTiny(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public AnalizadorSintacticoTiny(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public AnalizadorSintacticoTiny(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public AnalizadorSintacticoTiny(AnalizadorSintacticoTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AnalizadorSintacticoTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      trace_token(token, "");
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
      trace_token(token, " (in getNextToken)");
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[61];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 30; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 61; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  private int trace_indent = 0;
  private boolean trace_enabled = true;

/** Enable tracing. */
  final public void enable_tracing() {
    trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
    trace_enabled = false;
  }

  private void trace_call(String s) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Call:   " + s);
    }
    trace_indent = trace_indent + 2;
  }

  private void trace_return(String s) {
    trace_indent = trace_indent - 2;
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Return: " + s);
    }
  }

  protected void trace_token(Token t, String where) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Consumed token: <" + tokenImage[t.kind]);
      if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
        System.out.print(": \"" + t.image + "\"");
      }
      System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
    }
  }

  private void trace_scan(Token t1, int t2) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Visited token: <" + tokenImage[t1.kind]);
      if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
        System.out.print(": \"" + t1.image + "\"");
      }
      System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
    }
  }

}
