package com.m3s.ko;

import org.junit.*;
import org.junit.rules.TestName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FindFrequenciesTest {
    private WordCounter wc = null;
    private final int noOfFrequentWords = 3;
    private WordCounter.WordCount the;
    private WordCounter.WordCount of;
    private WordCounter.WordCount and;
    private WordCounter.WordCount in;
    private WordCounter.WordCount as;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() {
        Log.logger.debug("Starting Test " + testName.getMethodName() + ":");
        wc = new WordCounter(noOfFrequentWords);
        the = new WordCounter.WordCount("the", 10);
        of = new WordCounter.WordCount("of", 8);
        and = new WordCounter.WordCount("and", 7);
        in = new WordCounter.WordCount("in", 4);
        as = new WordCounter.WordCount("as", 2);
    }

    @Test
    public void checkPQueueIsMinHeap() {
        // Check that the priority queue's first element is always the min
        WordCounter.frequentWords.addWord(in);
        WordCounter.frequentWords.addWord(and);
        assertEquals(in, WordCounter.frequentWords.minHeap.peek());
        WordCounter.frequentWords.addWord(of);
        assertEquals(in, WordCounter.frequentWords.minHeap.peek());
    }

    @Test
    public void checkMinHeapSize() {
        // Check that the addWord method respects the maximum size of the heap
        WordCounter.frequentWords.addWord(the);
        WordCounter.frequentWords.addWord(of);
        WordCounter.frequentWords.addWord(and);
        WordCounter.frequentWords.addWord(in);
        WordCounter.frequentWords.addWord(as);
        assertEquals(3, WordCounter.frequentWords.minHeap.size());
    }

    @Test
    public void checkMinHeapOrder() {
        // Check that the priority queue's ordering is in line with a min heap
        WordCounter.frequentWords.addWord(as);
        WordCounter.frequentWords.addWord(and);
        WordCounter.frequentWords.addWord(in);
        assertEquals(as, WordCounter.frequentWords.minHeap.poll());
        assertEquals(in, WordCounter.frequentWords.minHeap.poll());
        assertEquals(and, WordCounter.frequentWords.minHeap.poll());
    }

    @Test
    public void checkHeapContainsMaxOnly() {
        // Check that the priority queue's ordering is in line with a min heap
        WordCounter.frequentWords.addWord(the);
        WordCounter.frequentWords.addWord(of);
        WordCounter.frequentWords.addWord(and);
        WordCounter.frequentWords.addWord(in);
        WordCounter.frequentWords.addWord(as);
        assertTrue(WordCounter.frequentWords.minHeap.contains(the));
        assertTrue(WordCounter.frequentWords.minHeap.contains(of));
        assertTrue(WordCounter.frequentWords.minHeap.contains(and));
        assertTrue(!WordCounter.frequentWords.minHeap.contains(as));
        assertTrue(!WordCounter.frequentWords.minHeap.contains(in));
    }

    @Test
    public void checkHeapReplacesMin() {
        // Check that when adding a higher count to the heap, the min is always the replaced word count
        WordCounter.frequentWords.addWord(and);
        WordCounter.frequentWords.addWord(in);
        WordCounter.frequentWords.addWord(as);
        assertTrue(WordCounter.frequentWords.minHeap.contains(and));
        assertTrue(WordCounter.frequentWords.minHeap.contains(as));
        assertTrue(WordCounter.frequentWords.minHeap.contains(in));
        WordCounter.frequentWords.addWord(the);
        WordCounter.frequentWords.addWord(in);
        WordCounter.frequentWords.addWord(of);
        assertTrue(WordCounter.frequentWords.minHeap.contains(the));
        assertTrue(WordCounter.frequentWords.minHeap.contains(of));
        assertTrue(WordCounter.frequentWords.minHeap.contains(and));
    }

    @After
    public void cleanUp() {
        Log.logger.debug("End of " + testName.getMethodName() + " test.\n");
    }
}
