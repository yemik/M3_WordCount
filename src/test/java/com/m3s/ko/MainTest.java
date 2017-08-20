package com.m3s.ko;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() {
        Log.logger.debug("Starting Test " + testName.getMethodName() + ":");
    }

    @Test
    public void checkRuntimeIsAcceptable() throws IOException {
//        Check the runtime is < 3 minutes
        String[] args = null;
        WCMain main = new WCMain();
        main.main(args);
        assertTrue(WCMain.averageTime < 3*60*1000);
    }

    @After
    public void cleanUp() {
        Log.logger.debug("End of " + testName.getMethodName() + " test.\n");
    }
}
