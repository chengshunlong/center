package com.zhbit.exception;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-16-23:03
 */
public class MyDatabaseException extends Exception{

    public MyDatabaseException() {
    }

    public MyDatabaseException(String message) {
        super(message);
    }
}
