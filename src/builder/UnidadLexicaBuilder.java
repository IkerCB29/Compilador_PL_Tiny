package builder;

import model.lexico.AnalizadorLexico;
import model.lexico.UnidadLexica;

public class UnidadLexicaBuilder {
	protected AnalizadorLexico AL;
	
	public UnidadLexicaBuilder(AnalizadorLexico AL) {
		this.AL = AL;
	}
	
	public UnidadLexica construirUL(int clase, String lexema){
		return new UnidadLexica(
			AL.getFila(),
			AL.getColumna(),
			clase,
			lexema
		);
	}
}
