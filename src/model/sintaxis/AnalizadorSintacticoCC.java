package model.sintaxis;

import java.io.IOException;
import java.io.Reader;
import view.Printer;

public class AnalizadorSintacticoCC extends ConstructorASTsCC {
    private final Printer output;
    private void imprime(Token t) {
        try {
            String lexema;
            switch (t.kind) {
                case And:
                    lexema = "<and>";
                    break;
                case Or:
                    lexema = "<or>";
                    break;
                case Not:
                    lexema = "<not>";
                    break;
                case Entero:
                    lexema = "<int>";
                    break;
                case Real:
                    lexema = "<real>";
                    break;
                case Boolean:
                    lexema = "<bool>";
                    break;
                case String:
                    lexema = "<string>";
                    break;
                case Null:
                    lexema = "<null>";
                    break;
                case LiteralFalse:
                    lexema = "<false>";
                    break;
                case LiteralTrue:
                    lexema = "<true>";
                    break;
                case Proc:
                    lexema = "<proc>";
                    break;
                case If:
                    lexema = "<if>";
                    break;
                case Else:
                    lexema = "<else>";
                    break;
                case While:
                    lexema = "<while>";
                    break;
                case Struct:
                    lexema = "<struct>";
                    break;
                case New:
                    lexema = "<new>";
                    break;
                case Delete:
                    lexema = "<delete>";
                    break;
                case Read:
                    lexema = "<read>";
                    break;
                case Write:
                    lexema = "<write>";
                    break;
                case Nl:
                    lexema = "<nl>";
                    break;
                case Type:
                    lexema = "<type>";
                    break;
                case Call:
                    lexema = "<call>";
                    break;
                case EOF:
                    lexema = "<EOF>";
                    break;
                default:
                    lexema = t.image;
            }
            output.write(AsinCCTokenManager.tokenImage[t.kind],t.beginLine, t.beginColumn, lexema);
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public AnalizadorSintacticoCC(Reader r, Printer output) {
        super(r);
        this.output = output;
        disable_tracing();
    }
    final protected void trace_token(Token t, String where) {
        imprime(t);
    }
}