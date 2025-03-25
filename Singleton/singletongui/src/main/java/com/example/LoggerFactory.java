package com.example;

public class LoggerFactory {
    public static Logger getLogger() {
        String fileLogging = "ON"; // Simula leer desde un archivo de configuración

        if("ON".equals(fileLogging)) {
            return FileLogger.getFileLogger();
        } else {
            return ConsoleLogger.getConsoleLogger();
        }
    }
}
