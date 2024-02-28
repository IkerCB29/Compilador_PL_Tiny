package launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import controller.Controller;
import view.FullPrinter;
import view.Printer;

public class Main {
	private final static String[] FILES = {
			"files/codigo1.tiny",
			"files/codigo2.tiny",
			"files/codigo3.tiny",
			"files/codigo4.tiny",
			"files/codigo5.tiny",
			"files/codigo6.tiny"
	};

	public static void main(String[] args) {
		Controller c = new Controller();

		for(String file : FILES) {
			try {
				Reader input = new BufferedReader(new FileReader(file));
				c.analisisLexico(input, new FullPrinter(file + "output"));
				input.close();

				input = new BufferedReader(new FileReader(file));
				c.analisisSintactico(input);
				input.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}