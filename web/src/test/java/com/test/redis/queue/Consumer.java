package com.test.redis.queue;

import com.util.redis.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;

/**
 * Created by 志华 on 2016/4/18.
 */
public class Consumer {
    private Jedis jedis;
    private JedisPool pool;

    public Consumer() {
        jedis = JedisUtil.getInstance().getJedis("192.168.9.106", 6379);
        ;
    }


    public void consum(String channel) throws IOException {
        JedisPubSub jedisPubSub = new JedisPubSub() {
            // 取得订阅的消息后的处理
            public void onMessage(String channel, String message) {
                System.out.println("Channel:" + channel);
                System.out.println("Message:" + message.toString());
            }

            // 初始化订阅时候的处理
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("onSubscribe:" + channel);
            }

            // 取消订阅时候的处理
            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println("onUnsubscribe:" + channel);
            }

            // 初始化按表达式的方式订阅时候的处理
            public void onPSubscribe(String pattern, int subscribedChannels) {
// System.out.println(pattern + "=" + subscribedChannels);
            }

            // 取消按表达式的方式订阅时候的处理
            public void onPUnsubscribe(String pattern, int subscribedChannels) {
// System.out.println(pattern + "=" + subscribedChannels);
            }

            // 取得按表达式的方式订阅的消息后的处理
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println(pattern + "=" + channel + "=" + message);
            }
        };

        jedis.subscribe(jedisPubSub, channel);
    }

    //close the connection
    public void close() throws IOException {
        //将Jedis对象归还给连接池
        jedis.close();
    }
}
