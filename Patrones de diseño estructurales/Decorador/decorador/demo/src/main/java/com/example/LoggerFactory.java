package com.example;

import java.util.*;
import java.io.*;

public class LoggerFactory {

  /*public boolean isFileLoggingEnabled() {
    Properties p = new Properties();
    try {
      p.load(ClassLoader.getSystemResourceAsStream(
        "Logger.properties"));
      String fileLoggingValue =
        p.getProperty("FileLogging");
      if (fileLoggingValue.equalsIgnoreCase("ON") == true)
        return true;
      else
        return false;
    } catch (IOException e) {
      return false;
    }
  }*/
// El método isFileLoggingEnabled() verifica si el registro de archivos está habilitado

  public boolean isFileLoggingEnabled() {
    Properties p = new Properties();
    try (InputStream input = ClassLoader.getSystemResourceAsStream("com/example/Logger.properties")) {
        if (input == null) {
            // El archivo no se encontró
            System.out.println("Lo siento, no se encontró el archivo Logger.properties");
            return false;
        }
        p.load(input);
        String fileLoggingValue = p.getProperty("FileLogging");
        return "ON".equalsIgnoreCase(fileLoggingValue);
    } catch (IOException e) {
        return false;
    }
}

  public Logger getLogger() {
    if (isFileLoggingEnabled()) {
      return FileLogger.getFileLogger();
    } else {
      return new ConsoleLogger();
    }
  }

}
