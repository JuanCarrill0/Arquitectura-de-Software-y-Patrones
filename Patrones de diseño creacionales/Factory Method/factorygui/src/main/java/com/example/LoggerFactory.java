package com.example;

public class LoggerFactory {

public Logger getLogger(String selection) {
    if (selection == "File") {
      return new FileLogger();
    } else {
      return new ConsoleLogger();
    }
  }

}
