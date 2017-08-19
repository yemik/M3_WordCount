package com.m3s.ko;

import java.io.FileNotFoundException;

public class WCMain {
    public static void main(String[] args) throws FileNotFoundException {
        long averageTime = 0;
        long startTime;
        FileHandler wordCountFile = new FileHandler("resources/aSample");
        for (int i = 0; i < 100; i++) {
            startTime = System.nanoTime();
            wordCountFile.readFile();
            wordCountFile.displayWordCounts();
            averageTime += System.nanoTime()-startTime;
        }
//        averageTime = averageTime/100;
//        System.out.println("Average Runtime: " + averageTime);
    }
}
