package launcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Tester {
    protected static void checkDifferences(String differencesFile, String outputFileRoute, String testFileRoute, boolean append) throws IOException {
        int lineNumber = 1;
        boolean equalFile = true;
        Scanner output = new Scanner(new File(outputFileRoute));
        Scanner test = new Scanner(new File(testFileRoute));
        FileWriter differences = new FileWriter(differencesFile, append);
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
