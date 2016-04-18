package com.test.redis.queue;

import java.io.IOException;

/**
 * Created by 志华 on 2016/4/18.
 */
public class TestPubAndSub {

    public static void main(String[] args) {

        Message msg = new Message("hello!", "this is the first message!");

        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        try {
            producer.provide("chn1", msg);
            consumer.consum("chn1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
