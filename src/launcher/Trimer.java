package launcher;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Trimer {
    private final static String[] FILES = {
        "files/sample1a.out",
        "files/sample1d.out",
        "files/sample2a.out",
        "files/sample2d.out",
        "files/sample3a.out",
        "files/sample3d.out",
        "files/sample4a.out",
        "files/sample4d.out",
        "files/sample5a.out",
        "files/sample5d.out"
    };

    public static void main(String[] args) {
        for(String file : FILES) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                Scanner myReader = new Scanner(new File(file));
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    data = data.trim();
                    if(!data.isEmpty()) stringBuilder.append(data).append("\n");
                }
                myReader.close();
                FileWriter myWriter = new FileWriter(file);
                myWriter.write(stringBuilder.toString());
                myWriter.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}