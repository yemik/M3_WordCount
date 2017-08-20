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

    void traceMessage(String message) {
        logger.trace(message);
    }

    void output(String message) {
        System.out.println(message);
        logger.trace(message);
    }

    void infoMessage(String message) {
        logger.info(message);
    }

    void debugMessage(String message) {
        logger.debug(message);
    }

    void errorMessage(String message) {
        logger.error(message);
    }

    void exceptionMessage(String message, Exception exception) {
        logger.error(message, exception);
    }

}
