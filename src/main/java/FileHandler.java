import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {// implements FileHandling {
    private final String path;

//    FileInputStream inputStream;
//    Scanner streamer;

    WordCounter wc = new WordCounter(3);

    FileHandler(String path) throws FileNotFoundException {
        this.path = path;
//        inputStream = new FileInputStream(path);
//        this.streamer = new Scanner(inputStream, "UTF-8");
    }

//    public String streamLine() {
//        if (streamer.hasNextLine()) {
//            return streamer.nextLine();
//        }
//        return "";
//    }

    public void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
//TODO reader.lines(); ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            String inputLine;
            String[] words;
            while ((inputLine = reader.readLine()) != null) {
                words = cleanLine(inputLine);
                wc.addWords(words);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayWordCounts() {
        wc.getTopWordCounts();
    }

//TODO/#############################################################################################################################
    private String[] cleanLine(String line) {
        return line.replaceAll("[^\\w\\s-']","").toLowerCase().trim().split("\\s+");
    }

    public String readLine(String path) {
        return "";
    }

    public String[] splitFile(String path) {
        return new String[0];
    }

    public int findNoSplits(String largeFilePath) {
        return 0;
    }
}
