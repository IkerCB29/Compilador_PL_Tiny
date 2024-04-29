package exceptions;

import model.sintaxis.SintaxisAbstracta.P_tipo;
import model.sintaxis.SintaxisAbstracta.A_tipo;
import model.sintaxis.SintaxisAbstracta.B_tipo;
import model.sintaxis.SintaxisAbstracta.Id_tipo;
import model.sintaxis.SintaxisAbstracta.In_tipo;
import model.sintaxis.SintaxisAbstracta.R_tipo;
import model.sintaxis.SintaxisAbstracta.String_tipo;
import model.sintaxis.SintaxisAbstracta.Struct_tipo;

public class TipadoInvalidoExcepcion extends RuntimeException{
    private Class[] esperados;
    private Class recibido;

    public TipadoInvalidoExcepcion(Class recibido, Class ... esperados){
        this.esperados = esperados;
        this.recibido = recibido;
    }

    @Override
    public String getMessage(){
        return "Se esperaba " + tiposEsperados() + "pero se ha recibido" + tipoToString(recibido);
    }

    private String tiposEsperados(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Class esperado : esperados){
            stringBuilder.append(tipoToString(esperado)).append(",");
        }
        return stringBuilder.toString();
    }

    private String tipoToString(Class c){
        if(c == B_tipo.class){
            return "booleano";
        }
        else if(c == In_tipo.class){
            return "entero";
        }
        else if(c == R_tipo.class){
            return "real";
        }
        else if(c == String_tipo.class){
            return "string";
        }
        else if(c == Struct_tipo.class){
            return "struct";
        }
        else if(c == Id_tipo.class){
            return "identificador";
        }
        else if(c == A_tipo.class){
            return "array";
        }
        else if(c == P_tipo.class){
            return "puntero";
        }
        return null;
    }
}
