package builder;

import model.lexico.AnalizadorLexico;
import model.lexico.ClaseLexica;
import model.lexico.UnidadLexica;
import model.lexico.UnidadLexicaUnivaluada;

public class UnidadLexicaUnivaluadaBuilder extends UnidadLexicaBuilder {
	public UnidadLexicaUnivaluadaBuilder(AnalizadorLexico AL) {
		super(AL);
	}

	@Override
	public UnidadLexica construirUL(ClaseLexica clase) {
		return new UnidadLexicaUnivaluada(
			AL.getFila(),
			AL.getColumna(),
			clase
		);
	}

}
