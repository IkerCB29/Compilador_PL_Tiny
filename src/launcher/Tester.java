package launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import controller.Controller;
import view.FilePrinter;

public class Tester {
	private final static String[] FILES = {
		"files/samplea1.in"
	};

	public static void main(String[] args) {
		Controller c = new Controller();

		for(String file : FILES) {
			try {
				Reader input = new BufferedReader(new FileReader(file));
				char type = (char) input.read();
				if(type == 'a'){
					c.analisisSintacticoCC(input, new FilePrinter("files/tester.out"));
				}
				else if(type == 'd'){
					c.analisisSintacticoCUP(input, new FilePrinter("files/tester.out"));
				}
				else throw new RuntimeException("Tipo invalido");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}