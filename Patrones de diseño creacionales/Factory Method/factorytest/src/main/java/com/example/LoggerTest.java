package com.example;

public class LoggerTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger();
        logger.log("Este es un mensaje de prueba");
    }
}