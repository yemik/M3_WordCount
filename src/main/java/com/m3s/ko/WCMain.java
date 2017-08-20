package com.m3s.ko;

import java.io.IOException;

public class WCMain {
    static long averageTime;
    public static void main(String[] args) throws IOException {
        Log.initialiseLogging();
        Log.logger.info("Beginning program");
        long startTime;
        FileHandler wordCountFile = new FileHandler("resources/aLargeFile",3);
        startTime = System.currentTimeMillis();
        Log.logger.debug("Setting start time to be " + startTime + "ms");
        Log.logger.trace("Reading the file and adding the top word counts");
        wordCountFile.readFileStream();
        Log.logger.trace("Outputting the top word counts");
        wordCountFile.displayWordCounts();
        averageTime = System.currentTimeMillis()-startTime;
        System.out.println("Average Runtime: " + averageTime/1000f + " seconds");
        Log.logger.debug("Average Runtime: " + averageTime/1000f + " seconds");
        Log.logger.info("Successful termination of program");
    }
}
