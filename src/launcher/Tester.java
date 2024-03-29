package launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import controller.Controller;
import view.FilePrinter;

public class Tester {
	private final static String[] FILES = {
			"files/codigo1.tiny",
			"files/codigo2.tiny",
			"files/codigo3.tiny",
			"files/codigo4.tiny",
			"files/codigo5.tiny",
			"files/codigo6.tiny",
			"files/codigo7.tiny",
			"files/codigo8.tiny",
			"files/codigo9.tiny",
			"files/codigo10.tiny",
			"files/codigo11.tiny",
			"files/codigo12.tiny"
	};

	public static void main(String[] args) {
		Controller c = new Controller();

		for(String file : FILES) {
			try {
				Reader input = new BufferedReader(new FileReader(file));
				c.analisisSintacticoCC(input, new FilePrinter(file + "outputCC"), "rec");
				input.close();

				input = new BufferedReader(new FileReader(file));
				c.analisisSintacticoCUP(input, new FilePrinter(file + "outputCUP"), "rec");
				input.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}