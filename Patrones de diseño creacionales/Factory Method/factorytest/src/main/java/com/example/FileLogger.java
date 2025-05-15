package com.example;

public class FileLogger implements Logger{
    public void log(String message){
        System.out.println("Escribiendo en un archivo: " + message);
    } 
}
