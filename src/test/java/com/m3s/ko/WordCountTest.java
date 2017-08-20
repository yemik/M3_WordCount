package com.m3s.ko;

import org.junit.*;
import org.junit.rules.TestName;
import static org.junit.Assert.assertEquals;

public class WordCountTest {
    private WordCounter wc = null;
    private WordCounter.WordCount then = new WordCounter.WordCount("then", 10);
    private final int noOfFrequentWords = 3;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() {
        Log.logger.debug("Starting Test " + testName.getMethodName() + ":");
        wc = new WordCounter(noOfFrequentWords);
    }

//    @Test
//    public void checkCorrectNoOfTopWords() {
//        assertEquals(noOfFrequentWords, WordCounter.frequentWords.noOfFrequentWords);
//    }
//
//    @Test
//    public void checkWordCountWord() {
//        assertEquals("then", then.word);
//    }
//
//    @Test
//    public void checkWordCountCount() {
//        assertEquals(10, then.count);
//    }

    @Test
    public void checkWordCountToString() {
        assertEquals("then\t:\t10 occurrences.", then.toString());
    }

    @After
    public void cleanUp() {
        Log.logger.debug("End of " + testName.getMethodName() + " test.\n");
    }
}
