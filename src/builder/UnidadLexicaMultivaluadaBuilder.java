package builder;

import model.lexico.AnalizadorLexico;
import model.lexico.ClaseLexica;
import model.lexico.UnidadLexica;
import model.lexico.UnidadLexicaMultivaluada;

public class UnidadLexicaMultivaluadaBuilder extends UnidadLexicaBuilder {
	public UnidadLexicaMultivaluadaBuilder(AnalizadorLexico AL) {
		super(AL);
	}

	@Override
	public UnidadLexica construirUL(ClaseLexica clase) {
		return new UnidadLexicaMultivaluada(
			AL.getFila(),
			AL.getColumna(),
			clase,
			AL.getLexema()
		);
	}

}
