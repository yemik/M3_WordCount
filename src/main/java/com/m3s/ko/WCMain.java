package com.m3s.ko;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WCMain {
    public static void main(String[] args) throws IOException {
        long averageTime;
        long startTime;
        FileHandler wordCountFile = new FileHandler("resources/aLargeFile");
        startTime = System.currentTimeMillis();
        wordCountFile.readFileStream();
        wordCountFile.displayWordCounts();
        averageTime = System.currentTimeMillis()-startTime;
        System.out.println("Average Runtime: " + averageTime/1000 + " seconds");
    }

//    public static void mainTest(String[] args) throws IOException {
//        long averageTime1 = 0;
//        long startTime1;
//        long averageTime2 = 0;
//        long startTime2;
//        long averageTime3 = 0;
//        long startTime3;
//        for (int i = 0; i < 1000; i++) {
//
//            FileHandler wordCountFile3 = new FileHandler("resources/aLargeSample");
//            startTime3 = System.nanoTime();
//            wordCountFile3.readFile();
//            wordCountFile3.displayWordCounts();
//            averageTime3 += System.nanoTime() - startTime3;
//
//            FileHandler wordCountFile1 = new FileHandler("resources/aLargeSample");
//            startTime1 = System.nanoTime();
//            wordCountFile1.readFileStream();
//            wordCountFile1.displayWordCounts();
//            averageTime1 += System.nanoTime() - startTime1;
//
//            FileHandler wordCountFile2 = new FileHandler("resources/aLargeSample");
//            startTime2 = System.nanoTime();
//            wordCountFile2.readFileStream2();
//            wordCountFile2.displayWordCounts();
//            averageTime2 += System.nanoTime() - startTime2;
//        }
//        System.out.println("Average Runtime Stream 1: " + averageTime1 / 1000 + " seconds");
//        System.out.println("Average Runtime Stream 2: " + averageTime2 / 1000 + " seconds");
//        System.out.println("Average Runtime Normal Buffer: " + averageTime3 / 1000 + " seconds");
//    }
}
