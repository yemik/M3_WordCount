package com.m3s.ko;

import java.util.*;

class WordCounter { //implements com.m3s.ko.WordCount {
//    private Map<String, Integer> wordMap = new HashMap<> ();
    private final int noOfFrequentWords;
    static FindFrequentWords frequentWords;

    WordCounter(int noOfFrequentWords) {
        frequentWords = new FindFrequentWords(noOfFrequentWords);
        this.noOfFrequentWords = noOfFrequentWords;
    }

//    void getTopWordCounts() {
//        getTopWords(frequentWords);
//        outputResults(frequentWords);
//    }

//    void addWords(String[] words) {
//        for (String word:words) {
//            if (!wordMap.containsKey(word)) {
//                wordMap.put(word, 1);
//            } else {
//                wordMap.put(word, wordMap.get(word) + 1);
//            }
//        }
//        if (wordMap.containsKey("")) {
//            wordMap.remove("");
//        }
//    }

//    private void getTopWords(FindFrequentWords frequentWords) {
//        for (Map.Entry<String, Integer> wordCount : wordMap.entrySet()) {
//            frequentWords.add(new WordCount(wordCount.getKey(), wordCount.getValue()));
//        }
//    }

    static class FindFrequentWords {
        PriorityQueue<WordCount> minHeap;
        private int noOfFrequentWords;

        FindFrequentWords(int noOfFrequentWords) {
            this.noOfFrequentWords = noOfFrequentWords;
            this.minHeap = new PriorityQueue<>(Comparator.comparingInt(wc -> wc.wordCount));
        }

        void addWord(WordCount newWord) {
            if (minHeap.size() < noOfFrequentWords) {
                minHeap.offer(newWord); // Insert the new word
            } else if (minHeap.peek().wordCount < newWord.wordCount) { // compare the word with smallest frequency to the new word
                minHeap.poll(); // Remove the least frequent word
                minHeap.offer(newWord); // Insert the new word
            }
        }

        void outputResults() {
            PriorityQueue<WordCount> maxHeap = new PriorityQueue<>(Comparator.comparingInt((WordCount wc) -> wc.wordCount).reversed());
            maxHeap.addAll(minHeap);
            System.out.println("The top " + noOfFrequentWords + " most occurring words (word:count)\n");
            while (!maxHeap.isEmpty()) {
                System.out.println(maxHeap.poll());
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
            return word + ":\t" + wordCount + " occurrences.";
        }
    }
}
