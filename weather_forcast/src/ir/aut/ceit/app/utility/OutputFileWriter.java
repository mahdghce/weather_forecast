package ir.aut.ceit.app.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Date;

public class OutputFileWriter {
    public void writeInFile(String printInFile) throws IOException {
        System.currentTimeMillis();
        String path = "C:\\users\\assassins\\desktop\\weather.txt";// + new Date(System.currentTimeMillis()).toString() + ".txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        bufferedWriter.write(printInFile);
        bufferedWriter.close();

    }
}
