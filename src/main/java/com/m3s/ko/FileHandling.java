package com.m3s.ko;

public interface FileHandling {
    String path = "";
    void readFile(String filePath);
    String readLine(String filePath);
    String[] splitFile(String largefilePath);
    int findNoSplits(String largeFilePath);
}
