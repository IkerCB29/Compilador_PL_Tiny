package model.sintaxis;

public class ClaseSemantica extends SintaxisAbstracta {
    public ClaseSemantica() {
        super();
    }
    public Exp mkop(String op, Exp opnd1, Exp opnd2) {
        switch(op) {
            case "=": return asig(opnd1,opnd2);
            case ">": return my(opnd1,opnd2);
            case "<": return mn(opnd1,opnd2);
            case ">=": return myig(opnd1,opnd2);
            case "<=": return mnig(opnd1,opnd2);
            case "==": return ig(opnd1,opnd2);
            case "!=": return dif(opnd1,opnd2);
            case "+": return suma(opnd1,opnd2);
            case "-": return resta(opnd1,opnd2);
            case "and": return and(opnd1,opnd2);
            case "or": return or(opnd1,opnd2);
            case "*": return mul(opnd1,opnd2);
            case "/": return div(opnd1,opnd2);
            case "%": return mod(opnd1,opnd2);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
    public Exp mkop_pre(String op, Exp opnd) {
        switch(op) {
            case "-": return menos_unario(opnd);
            case "not": return not(opnd);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
}
