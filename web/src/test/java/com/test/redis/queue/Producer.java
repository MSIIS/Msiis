package com.test.redis.queue;

import com.util.redis.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

/**
 * Created by 志华 on 2016/4/18.
 */
public class Producer {
    private Jedis jedis;

    public Producer() {
        jedis = JedisUtil.getInstance().getJedis("192.168.9.106", 6379);
    }


    public void provide(String channel, Message message) throws IOException {
        String str1 = MessageUtil.convertToString(channel, "UTF-8");
        String str2 = MessageUtil.convertToString(message, "UTF-8");
        jedis.publish(str1, str2);
    }

    //close the connection
    public void close() throws IOException {
        jedis.close();
    }
}
