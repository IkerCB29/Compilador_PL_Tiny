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
import view.Printer;

public class TesterProcesamiento extends Tester{
    private final static String DIFFERENCES_FILE = "files/Procesamiento/differences.log";
    private final static List<Pair<String, String>> FILES_INPUT = fileList();

    private static List<Pair<String, String>> fileList(){
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("files/Procesamiento/01hw_a.in", "files/Procesamiento/01hw.out"));
        list.add(new Pair<>("files/Procesamiento/01hw_d.in", "files/Procesamiento/01hw.out"));
        list.add(new Pair<>("files/Procesamiento/02basico_a.in", "files/Procesamiento/02basico.out"));
        list.add(new Pair<>("files/Procesamiento/02basico_d.in", "files/Procesamiento/02basico.out"));
        list.add(new Pair<>("files/Procesamiento/03basics_io_a.in", "files/Procesamiento/03basico_io.out"));
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
        list.add(new Pair<>("files/Procesamiento/08procs2_d.in", "files/Procesamiento/08procs2.out"));
        list.add(new Pair<>("files/Procesamiento/09ejemplo_a.in", "files/Procesamiento/09ejemplo.out"));
        list.add(new Pair<>("files/Procesamiento/09ejemplo_d.in", "files/Procesamiento/09ejemplo.out"));
        list.add(new Pair<>("files/Procesamiento/10procs3_a.in", "files/Procesamiento/10procs3.out"));
        list.add(new Pair<>("files/Procesamiento/01_errores_vinculacion_a.in", "files/Procesamiento/01_errores_vinculacion.out"));
        list.add(new Pair<>("files/Procesamiento/01_errores_vinculacion_d.in", "files/Procesamiento/01_errores_vinculacion.out"));
        list.add(new Pair<>("files/Procesamiento/02_errores_pretipado_a.in", "files/Procesamiento/02_errores_pretipado.out"));
        list.add(new Pair<>("files/Procesamiento/02_errores_pretipado_d.in", "files/Procesamiento/02_errores_pretipado.out"));
        list.add(new Pair<>("files/Procesamiento/03_errores_tipado1_a.in", "files/Procesamiento/03_errores_tipado1.out"));
        list.add(new Pair<>("files/Procesamiento/03_errores_tipado1_d.in", "files/Procesamiento/03_errores_tipado1.out"));
        list.add(new Pair<>("files/Procesamiento/04_errores_tipado2_a.in", "files/Procesamiento/04_errores_tipado2.out"));
        list.add(new Pair<>("files/Procesamiento/04_errores_tipado2_d.in", "files/Procesamiento/04_errores_tipado2.out"));
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
                    Printer filePrinter = new FilePrinter(files.getSecond() + "_test");
                    c.procesamiento(
                        c.analisisSintacticoCC(input, filePrinter, false),
                        input,
                        filePrinter
                    );
                }
                else if(type == 'a'){
                    Printer filePrinter = new FilePrinter(files.getSecond() + "_test");
                    c.procesamiento(
                        c.analisisSintacticoCUP(input, filePrinter, false),
                        input,
                        filePrinter
                    );
                }
                else throw new RuntimeException("Tipo invalido");
                input.close();
                checkDifferences(DIFFERENCES_FILE, files.getSecond() ,files.getSecond() + "_test", append);
                append = true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
