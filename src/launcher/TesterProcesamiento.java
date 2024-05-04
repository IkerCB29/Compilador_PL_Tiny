package launcher;

import controller.Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.lexico.BISReader;
import utils.Pair;
import view.FilePrinter;

public class TesterProcesamiento {
    private final static String DIFFERENCES_FILE = "files/Procesamiento/differences.log";
    private final static List<Pair<String, String>> FILES_INPUT = fileList();

    private static List<Pair<String, String>> fileList(){
        List<Pair<String, String>> list = new ArrayList<>();
        /*list.add(new Pair<>("files/Procesamiento/01hw_a.in", "files/Procesamiento/01hw.out"));
        list.add(new Pair<>("files/Procesamiento/01hw_d.in", "files/Procesamiento/01hw.out"));
        list.add(new Pair<>("files/Procesamiento/02basico_a.in", "files/Procesamiento/02basico.out"));
        list.add(new Pair<>("files/Procesamiento/02basico_d.in", "files/Procesamiento/02basico.out"));
        list.add(new Pair<>("files/Procesamiento/03basico_io_a.in", "files/Procesamiento/03basico_io.out"));
        list.add(new Pair<>("files/Procesamiento/03basico_io_d.in", "files/Procesamiento/03basico_io.out"));
        list.add(new Pair<>("files/Procesamiento/04control_a.in", "files/Procesamiento/04control.out"));
        list.add(new Pair<>("files/Procesamiento/04control_d.in", "files/Procesamiento/04control.out"));
        list.add(new Pair<>("files/Procesamiento/05tipos_1_a.in", "files/Procesamiento/05tipos_1.out"));
        list.add(new Pair<>("files/Procesamiento/05tipos_1_d.in", "files/Procesamiento/05tipos_1.out"));
        list.add(new Pair<>("files/Procesamiento/06procs1_a.in", "files/Procesamiento/06procs1.out"));
        list.add(new Pair<>("files/Procesamiento/06procs1_d.in", "files/Procesamiento/06procs1.out"));
        list.add(new Pair<>("files/Procesamiento/07tipos_2_a.in", "files/Procesamiento/07tipos_2.out"));
        list.add(new Pair<>("files/Procesamiento/07tipos_2_d.in", "files/Procesamiento/07tipos_2.out"));
        list.add(new Pair<>("files/Procesamiento/08procs2_a.in", "files/Procesamiento/08procs2.out"));
        list.add(new Pair<>("files/Procesamiento/08procs2_d.in", "files/Procesamiento/08procs2.out"));*/
        list.add(new Pair<>("files/Procesamiento/09ejemplo_d.in", "files/Procesamiento/09ejemplo.out"));
        return list;
    };

    public static void main(String[] args) {
        Controller c = new Controller();
        boolean append = false;

        for(Pair<String, String> files : FILES_INPUT) {
            try {
                Reader input = new BISReader(new FileInputStream(files.getFirst()));
                char type = (char) input.read();
                if(type == 'd'){
                    c.procesamientoCC(input, new FilePrinter(files.getSecond() + "_test"));
                }
                else if(type == 'a'){
                    c.procesamientoCUP(input, new FilePrinter(files.getSecond() + "_test"));
                }
                else throw new RuntimeException("Tipo invalido");
                input.close();
                checkDifferences(files.getSecond() ,files.getSecond() + "_test", append);
                append = true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkDifferences(String outputFileRoute, String testFileRoute, boolean append) throws IOException {
        int lineNumber = 1;
        boolean equalFile = true;
        Scanner output = new Scanner(new File(outputFileRoute));
        Scanner test = new Scanner(new File(testFileRoute));
        FileWriter differences = new FileWriter(DIFFERENCES_FILE, append);
        differences.write(outputFileRoute + " --- " + testFileRoute + "\n");
        while(test.hasNextLine() && output.hasNextLine()){
            String outputLine = output.nextLine();
            String testLine = test.nextLine();
            if(!outputLine.equals(testLine)){
                differences.write("Line " + lineNumber + "\n");
                differences.write("\tOutput: " + outputLine +"\n");
                differences.write("\tFile to compare: " + testLine +"\n");
                equalFile = false;
            }
            lineNumber++;
        }
        while(test.hasNextLine()){
            differences.write("Line " + lineNumber + "\n");
            differences.write("\tFile to compare: " + test.nextLine() +"\n");
            equalFile = false;
            lineNumber++;
        }
        while(output.hasNextLine()){
            differences.write("Line " + lineNumber + "\n");
            differences.write("\tOutput: " + output.nextLine() +"\n");
            equalFile = false;
            lineNumber++;
        }
        if(equalFile){
            differences.write("No differences were found\n");
        }
        differences.write("--------------------------------------\n");
        output.close();
        test.close();
        differences.close();
    }
}
