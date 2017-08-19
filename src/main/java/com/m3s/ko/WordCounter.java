package com.m3s.ko;

import java.util.*;

public class WordCounter { //implements com.m3s.ko.WordCount {
    private Map<String, Integer> wordMap = new HashMap<String, Integer> ();
    private int noOfFrequentWords;

    public WordCounter(int noOfFrequentWords) {
        this.noOfFrequentWords = noOfFrequentWords;
    }
    public Map<String, Integer> sortFile(String path) {
        return null;
    }


    public void getTopWordCounts() {
        FindFrequentWords frequentWords = new FindFrequentWords(noOfFrequentWords);
        getTopWords(frequentWords);
        outputResults(frequentWords);
    }

    public void addWords(String[] words) {
        for (String word:words) {
            if (!wordMap.containsKey(word)) {
                wordMap.put(word, 1);
            } else {
                wordMap.put(word, wordMap.get(word) + 1);
            }
        }
        if (wordMap.containsKey("")) {
            wordMap.remove("");
        }
    }

    private void getTopWords(FindFrequentWords frequentWords) {
        for (Map.Entry<String, Integer> wordCount : wordMap.entrySet()) {
            frequentWords.add(new WordCount(wordCount.getKey(), wordCount.getValue()));
        }
    }

    private void outputResults(FindFrequentWords frequentWords) {
        PriorityQueue<WordCount> maxHeap = new PriorityQueue<>(Comparator.comparingInt((WordCount wc) -> wc.wordCount).reversed());
        maxHeap.addAll(frequentWords.minHeap);
        System.out.println("The top " + noOfFrequentWords + " most occurring words (word:count)\n");
//        for (com.m3s.ko.WordCount wc:frequentWords.minHeap) {
//            System.out.println(wc);
//        }
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }

    static class FindFrequentWords {
        PriorityQueue<WordCount> minHeap;
        private int noOfFrequentWords;

        public FindFrequentWords(int noOfFrequentWords) {
            this.noOfFrequentWords = noOfFrequentWords;
            this.minHeap = new PriorityQueue<>(Comparator.comparingInt(wc -> wc.wordCount));
        }

        public void add(WordCount newWord) {
            if (minHeap.size() < noOfFrequentWords) {
                minHeap.offer(newWord); // Insert the new word
            } else if (minHeap.peek().wordCount < newWord.wordCount) { // compare the word with smallest frequency to the new word
                minHeap.poll(); // Remove the least frequent word
                minHeap.offer(newWord); // Insert the new word
            }
        }
    }

    static class WordCount {
        private final String word;
        private final int wordCount;

        WordCount(String word, int count) {
            this.word = word;
            this.wordCount = count;
        }

        @Override
        public String toString() {
            return word + ": " + wordCount + " occurrences.";
        }
    }
}
