package exceptions;

public class VinculoInvalidoExcepcion extends RuntimeException{
    public VinculoInvalidoExcepcion(){}
    @Override
    public String getMessage(){
        return "El elemento vinculado no es de un tipo válido";
    }
}
