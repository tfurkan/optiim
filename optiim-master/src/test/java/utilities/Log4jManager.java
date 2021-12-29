package utilities;

import org.apache.log4j.Logger;


public class Log4jManager {

    public static Logger log = Logger.getLogger(Log4jManager.class);

    public static void info(String message){
        log.info(message);
    }
    public static void error(String message, Throwable t){
        log.error(message, t);
    }
    public static void warn(String message){log.warn(message);}
}
