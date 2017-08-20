package com.m3s.ko;

import java.util.*;


class WordCounter {
    static FindFrequentWords frequentWords;

    WordCounter(int noOfFrequentWords) {
        Log.logger.trace("Setting the number of output words to " + noOfFrequentWords);
        frequentWords = new FindFrequentWords(noOfFrequentWords);
    }

    static class FindFrequentWords {
        PriorityQueue<WordCount> minHeap;
        private int noOfFrequentWords;

        FindFrequentWords(int noOfFrequentWords) {
            Log.logger.trace("Setting the number of output words to " + noOfFrequentWords);
            this.noOfFrequentWords = noOfFrequentWords;
            Log.logger.trace("Initialising the Min Heap");
            this.minHeap = new PriorityQueue<>(Comparator.comparingInt(wc -> wc.wordCount));
        }

        void addWord(WordCount newWord) {
            Log.logger.trace("Trying to add word: " + newWord);
            // Add the new word count if the heap is not full, or if the word has a count greater than the current min
            if (minHeap.size() < noOfFrequentWords) {
                Log.logger.trace("Inserting [" + newWord + "] into heap due to free space");
                minHeap.offer(newWord); // Insert the new word
            } else if (minHeap.peek().wordCount < newWord.wordCount) { // compare the word with smallest frequency to the new word
                minHeap.poll(); // Remove the least frequent word
                Log.logger.trace("Inserting [" + newWord + "] into heap by replacing the least frequent word");
                minHeap.offer(newWord); // Insert the new word
            }
        }

        void outputResults() {
            PriorityQueue<WordCount> maxHeap = new PriorityQueue<>(Comparator.comparingInt((WordCount wc) -> wc.wordCount).reversed());
            Log.logger.trace("Adding top word counts to Max Heap");
            // Add the top words to the new max heap with an ordering in reverse of the min heap.
            maxHeap.addAll(minHeap);

            StringBuilder resultsHeader = new StringBuilder("The top ");
            resultsHeader.append(noOfFrequentWords).append(" most occurring words (word:count):\n______________________________________________");
            System.out.println(resultsHeader);
            Log.logger.trace(resultsHeader);

            // Remove and print the word count object with the greatest count in the heap
            while (!maxHeap.isEmpty()) {
                StringBuilder wc = new StringBuilder(maxHeap.poll().toString());
                Log.logger.trace(wc);
                System.out.println(wc);
            }
        }
    }

    static class WordCount {
        private final String word;
        private final int wordCount;

        WordCount(String word, int count) {
            Log.logger.trace("Initialising word count object for: " + word + ", " + count);
            this.word = word;
            this.wordCount = count;
        }

        // Override the toString() method to access private word and wordCount variables when printing
        @Override
        public String toString() {
            return word + "\t:\t" + wordCount + " occurrences.";
        }
    }
}
