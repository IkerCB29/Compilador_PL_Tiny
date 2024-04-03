package view;

import java.io.IOException;

public class ConsolePrinter implements Printer{
    @Override
    public void write(String message) { System.out.print(message); }
    @Override
    public void close() {}
}
