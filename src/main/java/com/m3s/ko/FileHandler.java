package com.m3s.ko;

import java.io.*;

public class FileHandler {// implements com.m3s.ko.FileHandling {
    private final String path;
    private final int noOfOutputWords;
    final WordCounter wc;

//    FileInputStream inputStream;
//    Scanner streamer;

    FileHandler(String path) throws FileNotFoundException {
        this.path = path;
        this.noOfOutputWords = 3;
        wc = new WordCounter(noOfOutputWords);
    }

    FileHandler(String path, int noOfOutputWords) throws FileNotFoundException {
        this.path = path;
        this.noOfOutputWords = noOfOutputWords;
        wc = new WordCounter(noOfOutputWords);
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
            String inputLine;
            String[] words;
            while ((inputLine = reader.readLine()) != null) {
                words = cleanLine(inputLine);
                wc.addWords(words);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayWordCounts() {
        wc.getTopWordCounts();
    }

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
