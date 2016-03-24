package com.test.common;

/**
 * 自定义测试异常
 * Created by gzh on 2016-3-24.
 */
public class MyTestException extends  Exception {

    //自定义属性，异常等级。
    private  int  exceptionLevel;

    public MyTestException(int exceptionLevel) {
        this.exceptionLevel = exceptionLevel;
    }

    public MyTestException(String message, Throwable cause, int exceptionLevel) {
        super(message, cause);
        this.exceptionLevel = exceptionLevel;
    }

    public MyTestException(String message, int exceptionLevel) {
        super(message);
        this.exceptionLevel = exceptionLevel;
    }

    public MyTestException(String message) {
        super(message);
    }
}
