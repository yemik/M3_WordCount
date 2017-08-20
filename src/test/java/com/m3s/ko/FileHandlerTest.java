package com.m3s.ko;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.IOException;

import static java.lang.System.in;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileHandlerTest {
    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() {
        Log.logger.debug("Starting Test " + testName.getMethodName() + ":");
    }

    @Test
    public void checkSymbols() throws IOException {
        // Test to check symbols are ignored when counting words
//        theres theres there-s there,s there.s there's there;s £theres there~s there|s
//        theres theres there-s there,s there.s there's there;s £theres there~s there|s
//        theres theres there's there,s there.s there's there;s £theres there~s there|s
//        theres theres there's there,s there.s there's there;s £theres there~s there|s
        FileHandler file = new FileHandler("resources/testFiles/symbolCheck", 5);
        file.readFileStream();
        assertEquals(new WordCounter.WordCount("there-s",2).toString(), WordCounter.frequentWords.minHeap.poll().toString());
        assertEquals(new WordCounter.WordCount("there's",6).toString(), WordCounter.frequentWords.minHeap.poll().toString());
        assertEquals(new WordCounter.WordCount("theres",32).toString(), WordCounter.frequentWords.minHeap.poll().toString());
    }

    @Test
    public void checkSymbolsSize() throws IOException {
        // Test to check that words containing unaccepted symbols are not differentiated
//        theres theres there-s there,s there.s there's there;s £theres there~s there|s
//        theres theres there-s there,s there.s there's there;s £theres there~s there|s
//        theres theres there's there,s there.s there's there;s £theres there~s there|s
//        theres theres there's there,s there.s there's there;s £theres there~s there|s
        FileHandler file = new FileHandler("resources/testFiles/symbolCheck", 5);
        file.readFileStream();
        assertEquals(3, WordCounter.frequentWords.minHeap.size());
    }

    @Test
    public void checkOnlyMaxOccurrences() throws IOException {
        // Test to check that only the words with the maximum occurrences are added into the min heap
//        and and and
//                hello
//        hi hi
//        bob bob bob bob
//        in in in
//        jim jim
        FileHandler file = new FileHandler("resources/testFiles/correctMax");
        file.readFileStream();
        assertEquals(3, WordCounter.frequentWords.minHeap.size());
        assertEquals(new WordCounter.WordCount("in",3).toString(), WordCounter.frequentWords.minHeap.poll().toString());
        assertEquals(new WordCounter.WordCount("and",3).toString(), WordCounter.frequentWords.minHeap.poll().toString());
        assertEquals(new WordCounter.WordCount("bob",4).toString(), WordCounter.frequentWords.minHeap.poll().toString());
    }

    @Test
    public void checkOnlyAcknowledgeUTF8() throws IOException {
        // Test to check that words containing foreign symbols are not differentiated
//        t�he man we�nt for a sw1m.
//        the man went for a jog.
//        the man went for a nap.
//        t�he man we�nt for a walk.
//        t�he man we�nt for a trip.
//        t�he man we�nt for a skip.
//        the man went for
//        the man went
//        the man
//        the
        FileHandler file = new FileHandler("resources/testFiles/encodingCheck", 5);
        file.readFileStream();
        assertEquals(5, WordCounter.frequentWords.minHeap.size());
        assertEquals(new WordCounter.WordCount("a",6).toString(), WordCounter.frequentWords.minHeap.poll().toString());
        assertEquals(new WordCounter.WordCount("for",7).toString(), WordCounter.frequentWords.minHeap.poll().toString());
        assertEquals(new WordCounter.WordCount("went",8).toString(), WordCounter.frequentWords.minHeap.poll().toString());
        assertEquals(new WordCounter.WordCount("man",9).toString(), WordCounter.frequentWords.minHeap.poll().toString());
        assertEquals(new WordCounter.WordCount("the",10).toString(), WordCounter.frequentWords.minHeap.poll().toString());
    }

    @Test
    public void checkCaseInsensitive() throws IOException {
        // Test to check that the case of a word does not matter when counting it
//        HELLO hello
//        Hello hELLO
//        HEllo heLLO
//        HELlo helLO
//        HELLo hellO
        FileHandler file = new FileHandler("resources/testFiles/caseInsensitive", 5);
        file.readFileStream();
        assertEquals(1, WordCounter.frequentWords.minHeap.size());
        assertEquals(new WordCounter.WordCount("hello",10).toString(), WordCounter.frequentWords.minHeap.poll().toString());
    }


    @After
    public void cleanUp() {
        Log.logger.debug("End of " + testName.getMethodName() + " test.\n");
    }
}
