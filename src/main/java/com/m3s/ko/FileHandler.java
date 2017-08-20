package com.m3s.ko;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.stream.Collectors;

class FileHandler {// implements com.m3s.ko.FileHandling {
    private final String path;
    private final int noOfOutputWords;
    final WordCounter wc;
    private static final String acceptedChars = "[^\\w\\s-']";
    private static final String wordSplitTerm = "\\s+";

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

//    void readFile() throws IOException {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(path));
//            String inputLine;
//            String[] words;
//            while ((inputLine = reader.readLine()) != null) {
//                words = inputLine.replaceAll(acceptedChars,"").toLowerCase().trim().split(wordSplitTerm);
//                wc.addWords(words);
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    void readFileStream() throws IOException {
        try {
            FileInputStream inputFile = new FileInputStream(new File(path));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile, Charset.forName("UTF-8").newDecoder().onMalformedInput(CodingErrorAction.IGNORE)));
            reader.lines()
                .parallel()
                .map(this::cleanLine)
                .flatMap(Arrays::stream)
//                .flatMap(line -> Arrays.asList(cleanLine(line)).stream())
                .collect(Collectors.toConcurrentMap(word->word, w -> 1, Integer::sum)) // Big O(n)
                .forEach((word, count) -> wc.frequentWords.addWord(new WordCounter.WordCount(word, count)));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    void readFileStream2() throws IOException {
//        try {
//            FileInputStream inputFile = new FileInputStream(new File(path));
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile, Charset.forName("UTF-8").newDecoder().onMalformedInput(CodingErrorAction.IGNORE)));
//            reader.lines()
//                .parallel()
//                .flatMap(line -> Arrays.stream(cleanLine(line)))
//                .collect(Collectors.toConcurrentMap(word->word, w -> 1, Integer::sum)) // Big O(n)
//                .forEach((word, count) -> wc.frequentWords.addWord(new WordCounter.WordCount(word, count)));
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    void displayWordCounts() {
//        wc.getTopWordCounts();
        wc.frequentWords.outputResults();
    }

    private String[] cleanLine(String line) {
        return line.replaceAll(acceptedChars,"").toLowerCase().trim().split(wordSplitTerm);
    }
}
