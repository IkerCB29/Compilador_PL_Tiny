package model.lexico;

public class UnidadLexicaErronea extends UnidadLexica {
	private final String lexema;
	
	public UnidadLexicaErronea(int fila, int columna, ClaseLexica clase, String info) {
		super(fila, columna, clase);
		this.lexema = info;
	}

	@Override
	public String lexema() {
		return lexema;
	}
	
    public String toString() {
        return "[clase:"+clase()+",fila:"+fila()+",col:"+columna()+","+lexema()+"]";
    }
}
