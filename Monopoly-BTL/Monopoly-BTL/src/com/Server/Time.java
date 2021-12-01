package com.Server;

public class Time implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(30*60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
