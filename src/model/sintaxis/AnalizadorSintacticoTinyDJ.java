package model.sintaxis;

import java.io.Reader;


public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {
    private void imprime(Token t) {
        switch(t.kind) {
            case And: System.out.println("<and>"); break;
            case Or: System.out.println("<or>"); break;
            case Not: System.out.println("<not>"); break;
            case Entero: System.out.println("<int>"); break;
            case Real: System.out.println("<real>"); break;
            case Boolean: System.out.println("<bool>"); break;
            case String: System.out.println("<string>"); break;
            case Null: System.out.println("<null>"); break;
            case LiteralFalse: System.out.println("<false>"); break;
            case LiteralTrue: System.out.println("<true>"); break;
            case Proc: System.out.println("<proc>"); break;
            case If: System.out.println("<if>"); break;
            case Else: System.out.println("<else>"); break;
            case While: System.out.println("<while>"); break;
            case Struct: System.out.println("<struct>"); break;
            case New: System.out.println("<new>"); break;
            case Delete: System.out.println("<delete>"); break;
            case Read: System.out.println("<read>"); break;
            case Write: System.out.println("<write>"); break;
            case Nl: System.out.println("<nl>"); break;
            case Type: System.out.println("<type>"); break;
            case Call: System.out.println("<call>"); break;
            case EOF: System.out.println("<EOF>"); break;
            default: System.out.println(t.image);
        }
    }
    
    public AnalizadorSintacticoTinyDJ(Reader r) {
        super(r);
        disable_tracing();
    }
    final protected void trace_token(Token t, String where) {
        imprime(t);
    }
}