package com.test.redis;

import com.util.redis.JedisUtil;
import redis.clients.jedis.Jedis;
import sun.plugin2.message.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 志华 on 2016/1/17.
 */
public class RedisTest  {
    public static void main(String[] args) {
         Jedis client = JedisUtil.getInstance().getJedis("192.168.9.106",6379);
        client.hset("users","name","漳卅");
        client.hset("users","age","20");
        client.hset("users","sex","1");

        List<String>  list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        Map<String,String> result=client.hgetAll("users");
        for(String string :result.keySet()){
            System.out.println("key is :"+string +", and value is :" +result.get(string));
        }
    }
}
