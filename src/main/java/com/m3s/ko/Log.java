package com.m3s.ko;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

    final static String LOG_PROPERTIES_FILE = "resources/log4j.properties";
    final static Logger logger = Logger.getLogger(Log.class.getName());

    Log() {
        initialiseLogging();
    }

    public static void initialiseLogging() {
        PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
        logger.info("Logging initialised");
    }

    public void traceMessage(String message) {
        logger.trace(message);
    }

    public void debugMessage(String message) {
        logger.debug(message);
    }

    public void errorMessage(String message) {
        logger.error(message);
    }

}
