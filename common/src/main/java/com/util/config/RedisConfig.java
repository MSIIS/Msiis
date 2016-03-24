package com.util.config;

/**
 * Created by 志华 on 2016/1/17.
 */
public class RedisConfig {


    public  static  int getMaxidle() {
        return  8;
    }
    public static  int getMaxTotle(){
        return  9;
    }

    public static  int getTimeout() {
        return 10;
    }

    public static  long getMaxwait(){
        return  10000L;
    }

}
