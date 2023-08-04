package com.example.webfluxdemomaven.service;

public class SleepUtil {
    public static void sleepSeconds(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
