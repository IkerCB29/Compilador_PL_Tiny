package launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import controller.Controller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import utils.Pair;
import view.FilePrinter;

public class Tester {
	private final static List<Pair<String, String>> FILES_INPUT = fileList();

	private static List<Pair<String, String>> fileList(){
		List<Pair<String, String>> list = new ArrayList<>();
		list.add(new Pair<>("files/sample1a.in", "files/sample1a.out"));
		list.add(new Pair<>("files/sample1d.in", "files/sample1d.out"));
		list.add(new Pair<>("files/sample2a.in", "files/sample2a.out"));
		list.add(new Pair<>("files/sample2d.in", "files/sample2d.out"));
		list.add(new Pair<>("files/sample3a.in", "files/sample3a.out"));
		list.add(new Pair<>("files/sample3d.in", "files/sample3d.out"));
		list.add(new Pair<>("files/sample4a.in", "files/sample4a.out"));
		list.add(new Pair<>("files/sample4d.in", "files/sample4d.out"));
		list.add(new Pair<>("files/sample5a.in", "files/sample5a.out"));
		list.add(new Pair<>("files/sample5d.in", "files/sample5d.out"));
		return list;
	};

	public static void main(String[] args) {
		Controller c = new Controller();

		for(Pair<String, String> files : FILES_INPUT) {
			try {
				Reader input = new BufferedReader(new FileReader(files.getFirst()));
				char type = (char) input.read();
				if(type == 'd'){
					c.analisisSintacticoCC(input, new FilePrinter(files.getSecond() + "_test"));
					input.close();
					Path filePathTest = Path.of(files.getSecond());
					Path filePathOut = Path.of(files.getSecond() + "_test");
					if(Files.mismatch(filePathTest, filePathOut) != -1){
						throw new RuntimeException("The ouput of file "+ files.getSecond() + "_test is not the expected one. Check the "
							+ "output file and compare it with " + files.getSecond());
					}
				}
				else if(type == 'a'){
					c.analisisSintacticoCUP(input, new FilePrinter(files.getSecond() + "_test"));
					input.close();
					Path filePathTest = Path.of(files.getSecond());
					Path filePathOut = Path.of(files.getSecond() + "_test");
					if(Files.mismatch(filePathTest, filePathOut) != -1){
						throw new RuntimeException("The ouput of file "+ files.getSecond() + "_test is not the expected one. Check the "
							+ "output file and compare it with " + files.getSecond());
					}
				}
				else throw new RuntimeException("Tipo invalido");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}