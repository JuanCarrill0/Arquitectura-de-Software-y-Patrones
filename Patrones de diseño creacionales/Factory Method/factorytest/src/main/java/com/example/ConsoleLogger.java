package com.example;

public class ConsoleLogger implements Logger{
    public void log(String message){
        System.out.println("Mostrando en consola: " + message);
    }
}
