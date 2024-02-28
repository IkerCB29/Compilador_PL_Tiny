package model.sintaxis;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class AnalizadorSintacticoCUP extends AnalizadorSintactico {
    public void debug_message(String msg) {}
    public void debug_shift(Symbol token) {
        System.out.println(token.value);
    }
    public AnalizadorSintacticoCUP(Scanner alex) {
        super(alex);
    }
}

