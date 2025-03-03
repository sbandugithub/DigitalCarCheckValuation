package utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Log {
    private static final Logger log = LoggerFactory.getLogger(Log.class);

    public static void info(String message) {
        log.info("\u001B[37mMessage: {}\u001B[0m", message);
    }

    public static void error(String message) {
        log.error("\u001B[31mMessage: {}\u001B[0m", message);
    }

    public static void pass(String message) {
        log.info("\u001B[32mMessage: {}\u001B[0m", message);
    }



}
