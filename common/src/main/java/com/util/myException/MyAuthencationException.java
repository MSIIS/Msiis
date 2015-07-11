package com.util.myException;

/**
 * Created by lenovo on 2015/7/11.
 */
public class MyAuthencationException extends  Exception  {
    private  String message;
    public MyAuthencationException(String message){
        super(message);
    }
    public MyAuthencationException(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
