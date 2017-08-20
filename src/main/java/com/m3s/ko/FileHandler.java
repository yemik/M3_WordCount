package com.m3s.ko;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.stream.Collectors;

class FileHandler {// implements com.m3s.ko.FileHandling {
    private final String path;
    private final int noOfOutputWords;
    private final WordCounter wc;
    private static final String acceptedChars = "[^\\w\\s-']";
    private static final String wordSplitTerm = "\\s+";

    FileHandler(String path) throws FileNotFoundException {
        this.path = path;
        Log.logger.info("Currently working on file: " + this.path);
        this.noOfOutputWords = 3;
        Log.logger.trace("Number of top words set to 3.");
        wc = new WordCounter(this.noOfOutputWords);
    }

    FileHandler(String path, int noOfOutputWords) throws FileNotFoundException {
        this.path = path;
        Log.logger.info("Currently working on file: " + path);
        this.noOfOutputWords = noOfOutputWords;
        Log.logger.trace("Number of top words set to " + noOfOutputWords);
        wc = new WordCounter(this.noOfOutputWords);
    }

    void readFileStream() throws IOException {
        Log.logger.trace("Reading the input file: " + path);
        try {
            FileInputStream inputFile = new FileInputStream(new File(path));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile, Charset.forName("UTF-8").newDecoder().onMalformedInput(CodingErrorAction.IGNORE)));
            Log.logger.trace("Streaming the current input lines in parallel");
            reader.lines()
                .parallel()
                .map(this::cleanLine)
                .flatMap(Arrays::stream)
//                .flatMap(line -> Arrays.asList(cleanLine(line)).stream())
                .collect(Collectors.toConcurrentMap(word->word, w -> 1, Integer::sum))
                .forEach((word, count) -> WordCounter.frequentWords.addWord(new WordCounter.WordCount(word, count)));
            Log.logger.trace("Closing the buffered reader after successful stream processing");
            reader.close();
        } catch (IOException e) {
            Log.logger.error("Threw an IOException in MyClass::MyMethod, full stack trace follows:", e);
        }
    }

    void displayWordCounts() {
        Log.logger.trace("Outputting results");
        WordCounter.frequentWords.outputResults();
    }

    private String[] cleanLine(String line) {
        Log.logger.trace("Cleaning the line [" + line + "] of foreign characters and splitting into words");
        return line.replaceAll(acceptedChars,"").toLowerCase().trim().split(wordSplitTerm);
    }
}
