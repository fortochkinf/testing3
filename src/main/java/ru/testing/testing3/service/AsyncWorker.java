package ru.testing.testing3.service;

import lombok.Getter;

public class AsyncWorker {

    public static final String RESULT_OK = "OK";
    public static final String RESULT_PROCESSING = "PROCESSING";
    public static final String NOT_EXECUTED = "NOT_EXECUTED";

    @Getter
    private String status = NOT_EXECUTED;

    public void runJob(){
        status = RESULT_PROCESSING;
        Thread newThread = new Thread(() -> {
            try {
                Thread.sleep(Double.valueOf(Math.random() * 10).intValue() + 11);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            status = RESULT_OK;
        });
        newThread.start();
    }
}
