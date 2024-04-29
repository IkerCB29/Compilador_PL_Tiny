package exceptions;

public class ElementoRepetidoExcepcion extends RuntimeException {
    private final String id;

    public ElementoRepetidoExcepcion (String id) {
        this.id = id;
    }

    @Override
    public String getMessage(){
        return "El identificador " + id + " ya se ha usado en este ambito";
    }
}
