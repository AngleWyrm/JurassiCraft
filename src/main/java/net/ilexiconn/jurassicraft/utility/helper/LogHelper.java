package net.ilexiconn.jurassicraft.utility.helper;

import net.ilexiconn.jurassicraft.lib.Reference;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ProPercivalalb
 */
public class LogHelper {

    private static Logger carboniferousLogger = Logger.getLogger(Reference.MOD_ID);

    public static void init() {
    	//carboniferousLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, String message) {
    	carboniferousLogger.log(logLevel, message);
    }
    
    public static void logWarning(String message) {
    	carboniferousLogger.log(Level.WARNING, message);
    }
    
    public static void logInfo(String message) {
    	carboniferousLogger.log(Level.INFO, message);
    }

    public static void logSevere(String message) {
    	carboniferousLogger.log(Level.SEVERE, message);
    }
}