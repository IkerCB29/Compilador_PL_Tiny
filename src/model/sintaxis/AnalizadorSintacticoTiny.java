/* Generated By:JavaCC: Do not edit this line. AnalizadorSintacticoTiny.java */
package model.sintaxis;

public class AnalizadorSintacticoTiny implements AnalizadorSintacticoTinyConstants {
            protected   void newToken(Token t) {}

  final public void programa() throws ParseException {
    bloque();
  }

  final public void bloque() throws ParseException {
    jj_consume_token(LlaveApertura);
    declaraciones_opt();
    instrucciones_opt();
    jj_consume_token(LlaveCierre);
  }

  final public void declaraciones_opt() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Entero:
    case Real:
    case Boolean:
    case String:
    case Identificador:
    case Puntero:
    case Proc:
    case Struct:
    case Type:
      declaraciones();
      jj_consume_token(FinDeclaracion);
      break;
    default:
      jj_la1[0] = jj_gen;

    }
  }

  final public void declaraciones() throws ParseException {
    declaración();
    r_declaraciones();
  }

  final public void r_declaraciones() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PyC:
      jj_consume_token(PyC);
      declaración();
      r_declaraciones();
      break;
    default:
      jj_la1[1] = jj_gen;

    }
  }

  final public void declaración() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Entero:
    case Real:
    case Boolean:
    case String:
    case Identificador:
    case Puntero:
    case Struct:
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
  }

  final public void dec_tipo() throws ParseException {
    jj_consume_token(Type);
    tipo0();
    jj_consume_token(Identificador);
  }

  final public void dec_variable() throws ParseException {
    tipo0();
    jj_consume_token(Identificador);
  }

  final public void tipo0() throws ParseException {
    tipo1();
    r_tipo0();
  }

  final public void r_tipo0() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CorcheteApertura:
      jj_consume_token(CorcheteApertura);
      jj_consume_token(LiteralEntero);
      jj_consume_token(CorcheteCierro);
      break;
    default:
      jj_la1[3] = jj_gen;

    }
  }

  final public void tipo1() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Puntero:
      jj_consume_token(Puntero);
      tipo1();
      break;
    case Entero:
    case Real:
    case Boolean:
    case String:
    case Identificador:
    case Struct:
      tipo2();
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void tipo2() throws ParseException {
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
  }

  final public void registro() throws ParseException {
    jj_consume_token(Struct);
    jj_consume_token(LlaveApertura);
    campos();
    jj_consume_token(LlaveCierre);
  }

  final public void campos() throws ParseException {
    campo();
    r_campos();
  }

  final public void r_campos() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Coma:
      jj_consume_token(Coma);
      campo();
      r_campos();
      break;
    default:
      jj_la1[6] = jj_gen;

    }
  }

  final public void campo() throws ParseException {
    dec_variable();
  }

  final public void dec_procedimiento() throws ParseException {
    jj_consume_token(Proc);
    jj_consume_token(Identificador);
    parametros_formales();
    bloque();
  }

  final public void parametros_formales() throws ParseException {
    jj_consume_token(ParAp);
    lista_parametros_opt();
    jj_consume_token(ParCierre);
  }

  final public void lista_parametros_opt() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Entero:
    case Real:
    case Boolean:
    case String:
    case Identificador:
    case Puntero:
    case Struct:
      lista_parametros();
      break;
    default:
      jj_la1[7] = jj_gen;

    }
  }

  final public void lista_parametros() throws ParseException {
    parametro();
    r_lista_parametros();
  }

  final public void r_lista_parametros() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PyC:
      jj_consume_token(PyC);
      parametro();
      r_lista_parametros();
      break;
    default:
      jj_la1[8] = jj_gen;

    }
  }

  final public void parametro() throws ParseException {
    tipo0();
    r_parametro();
  }

  final public void r_parametro() throws ParseException {
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
  }

  final public void instrucciones_opt() throws ParseException {
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
  }

  final public void instrucciones() throws ParseException {
    instruccion();
    r_instrucciones();
  }

  final public void r_instrucciones() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PyC:
      jj_consume_token(PyC);
      instruccion();
      r_instrucciones();
      break;
    default:
      jj_la1[11] = jj_gen;

    }
  }

  final public void instruccion() throws ParseException {
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
  }

  final public void instruccion_eval() throws ParseException {
    jj_consume_token(Evalua);
    E0();
  }

  final public void instruccion_if() throws ParseException {
    jj_consume_token(If);
    E0();
    bloque();
    r_instruccion_if();
  }

  final public void r_instruccion_if() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Else:
      jj_consume_token(Else);
      bloque();
      break;
    default:
      jj_la1[13] = jj_gen;

    }
  }

  final public void instruccion_while() throws ParseException {
    jj_consume_token(While);
    E0();
    bloque();
  }

  final public void instruccion_read() throws ParseException {
    jj_consume_token(Read);
    E0();
  }

  final public void instruccion_write() throws ParseException {
    jj_consume_token(Write);
    E0();
  }

  final public void instruccion_nl() throws ParseException {
    jj_consume_token(Nl);
  }

  final public void instruccion_new() throws ParseException {
    jj_consume_token(New);
    E0();
  }

  final public void instruccion_delete() throws ParseException {
    jj_consume_token(Delete);
    E0();
  }

  final public void instruccion_call() throws ParseException {
    jj_consume_token(Call);
    jj_consume_token(Identificador);
    jj_consume_token(ParAp);
    lista_expresiones_opt();
    jj_consume_token(ParCierre);
  }

  final public void instruccion_compuesta() throws ParseException {
    bloque();
  }

  final public void lista_expresiones_opt() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Identificador:
    case LiteralEntero:
    case LiteralReal:
    case LiteralTrue:
    case LiteralFalse:
    case LiteralString:
    case Resta:
    case Not:
    case ParAp:
    case Null:
      lista_expresiones();
      break;
    default:
      jj_la1[14] = jj_gen;

    }
  }

  final public void lista_expresiones() throws ParseException {
    E0();
    r_lista_expresiones();
  }

  final public void r_lista_expresiones() throws ParseException {
    E0();
    r_lista_expresiones();
  }

  final public void E0() throws ParseException {
    E1();
    RE0();
  }

  final public void RE0() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Asignacion:
      jj_consume_token(Asignacion);
      E0();
      break;
    default:
      jj_la1[15] = jj_gen;

    }
  }

  final public void E1() throws ParseException {
    E2();
    RE1();
  }

  final public void RE1() throws ParseException {
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
      jj_la1[16] = jj_gen;

    }
  }

  final public void E2() throws ParseException {
    E3();
    R2E2();
    R1E2();
  }

  final public void R1E2() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Suma:
      jj_consume_token(Suma);
      E3();
      R1E2();
      break;
    default:
      jj_la1[17] = jj_gen;

    }
  }

  final public void R2E2() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Resta:
      jj_consume_token(Resta);
      E3();
      break;
    default:
      jj_la1[18] = jj_gen;

    }
  }

  final public void E3() throws ParseException {
    E4();
    RE3();
  }

  final public void RE3() throws ParseException {
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
      jj_la1[19] = jj_gen;

    }
  }

  final public void E4() throws ParseException {
    E5();
    RE4();
  }

  final public void RE4() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Mul:
    case Div:
    case Modulo:
      OP4();
      E5();
      RE4();
      break;
    default:
      jj_la1[20] = jj_gen;

    }
  }

  final public void E5() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Resta:
    case Not:
      OP5();
      E5();
      break;
    case Identificador:
    case LiteralEntero:
    case LiteralReal:
    case LiteralTrue:
    case LiteralFalse:
    case LiteralString:
    case ParAp:
    case Null:
      E6();
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void E6() throws ParseException {
    E7();
    RE6();
  }

  final public void RE6() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Puntero:
    case CorcheteApertura:
    case Acceso:
      OP6();
      RE6();
      break;
    default:
      jj_la1[22] = jj_gen;

    }
  }

  final public void E7() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ParAp:
      jj_consume_token(ParAp);
      E0();
      jj_consume_token(ParCierre);
      break;
    case Identificador:
    case LiteralEntero:
    case LiteralReal:
    case LiteralTrue:
    case LiteralFalse:
    case LiteralString:
    case Null:
      operando();
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void OP1() throws ParseException {
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
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void OP4() throws ParseException {
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
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void OP5() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Resta:
      jj_consume_token(Resta);
      break;
    case Not:
      jj_consume_token(Not);
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void OP6() throws ParseException {
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
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void acceso_registro() throws ParseException {
    jj_consume_token(Acceso);
    jj_consume_token(Identificador);
  }

  final public void operando() throws ParseException {
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
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
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
  final private int[] jj_la1 = new int[29];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x3f00,0x0,0x3f00,0x0,0x3f00,0x1f00,0x0,0x3f00,0x0,0x5000,0x0,0x0,0x0,0x0,0x82f9000,0x0,0xf0000000,0x100000,0x200000,0x6000000,0x1c00000,0x82f9000,0x2000,0xf9000,0xf0000000,0x1c00000,0x8200000,0x2000,0xf9000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x2088000,0x8,0x2088000,0x40,0x80000,0x80000,0x800,0x80000,0x8,0x0,0x5f52100,0x8,0x5f52100,0x20000,0x4010,0x4,0x3,0x0,0x0,0x0,0x0,0x4010,0x440,0x4010,0x3,0x0,0x0,0x440,0x4000,};
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
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public AnalizadorSintacticoTiny(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public AnalizadorSintacticoTiny(AnalizadorSintacticoTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AnalizadorSintacticoTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
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
    for (int i = 0; i < 29; i++) {
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

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
