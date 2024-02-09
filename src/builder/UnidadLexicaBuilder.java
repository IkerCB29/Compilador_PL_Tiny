package builder;

import model.lexico.AnalizadorLexico;
import model.lexico.ClaseLexica;
import model.lexico.UnidadLexica;

public abstract class UnidadLexicaBuilder {
	protected AnalizadorLexico AL;
	
	public UnidadLexicaBuilder(AnalizadorLexico AL) {
		this.AL = AL;
	}
	
	public abstract UnidadLexica construirUL(ClaseLexica clase);
}
