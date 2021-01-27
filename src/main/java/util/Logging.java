package util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
    public static final Logger logger = Logger.getLogger("Logger");

    public static void log(Level level, String message){
        logger.log(level, message);
    }
}
