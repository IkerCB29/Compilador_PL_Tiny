package exceptions;

public class SizeInvalidoExcepcion extends RuntimeException {
    private final String size;

    public SizeInvalidoExcepcion(String size) {
        this.size = size;
    }

    @Override
    public String getMessage(){
        return "No se puede hacer un array de tamaño " + size;
    }
}
