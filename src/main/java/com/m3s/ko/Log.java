package com.m3s.ko;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

    private final static String LOG_PROPERTIES_FILE = "resources/log4j.properties";
    final static Logger logger = Logger.getLogger(Log.class.getName());

    Log() {
        initialiseLogging();
    }

    static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        logger.info("Logging initialised");
    }
}
