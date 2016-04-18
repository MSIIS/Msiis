package com.test.redis;

import com.util.redis.JedisUtil;
import redis.clients.jedis.Jedis;
import sun.plugin2.message.Serializer;

import java.util.*;

/**
 * Created by 志华 on 2016/1/17.
 */
public class RedisTest  {
    static Jedis jedis = null;
    public static void main(String[] args) {
        jedis = JedisUtil.getInstance().getJedis("192.168.9.106", 6379);
        try {
            RedisTest redisTest = new RedisTest();
            System.out.println("==========测试string==============");
            redisTest.testBasicString();
            System.out.println("==========测试map==============");
            System.out.println("==========测试list==============");
            System.out.println("==========测试set==============");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jedis.close();
        }

    }


    /**
     * 添加string
     */
    public void testBasicString() {
        // -----添加数据----------
        jedis.set("name", "minxr");// 向key-->name中放入了value-->minxr
        System.out.println(jedis.get("name"));// 执行结果：minxr
        System.out.println("exist" + jedis.exists("name"));
        // -----修改数据-----------
        // 1、在原来基础上修改
        jedis.append("name", "jarorwar"); // 很直观，类似map 将jarorwar
        // append到已经有的value之后
        System.out.println(jedis.get("name"));// 执行结果:minxrjarorwar
        // 2、直接覆盖原来的数据
        jedis.set("name", "tony");
        System.out.println(jedis.get("name"));// 执行结果：tony
        // 删除key对应的记录
        jedis.del("name");
        System.out.println(jedis.get("name"));// 执行结果：null
        /**
         * mset相当于 jedis.set("name","minxr"); jedis.set("jarorwar","tony");
         */
        jedis.mset("name", "minxr", "jarorwar", "tony");
        System.out.println(jedis.mget("name", "jarorwar"));
    }

    /**
     * 测试map
     */
    public void testMap() {
        Map<String, String> user = new HashMap<String, String>();
        user.put("name", "minxr");
        user.put("pwd", "password");
        jedis.hmset("user", user);
        // 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name");
        System.out.println(rsmap);
        // 删除map中的某个键值
        // jedis.hdel("user","pwd");
        System.out.println(jedis.hmget("user", "pwd")); // 因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); // 返回key为user的键中存放的值的个数1
        System.out.println(jedis.exists("user"));// 是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));// 返回map对象中的所有key [pwd, name]
        System.out.println(jedis.hvals("user"));// 返回map对象中的所有value [minxr,
        // password]
        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
    }

    /**
     * jedis操作List
     */
    public void testList() {
        // 开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        // 先向key java framework中存放三条数据
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        // 再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework", 0, -1));
    }

    public void testSet() {
        // 添加
        jedis.sadd("sname", "minxr");
        jedis.sadd("sname", "jarorwar");
        jedis.sadd("sname", "tony");
        jedis.sadd("sanme", "noname");
        // 移除noname
        jedis.srem("sname", "noname");
        System.out.println(jedis.smembers("sname"));// 获取所有加入的value
        System.out.println(jedis.sismember("sname", "minxr"));// 判断 minxr
        // 是否是sname集合的元素
        System.out.println(jedis.srandmember("sname"));
        System.out.println(jedis.scard("sname"));// 返回集合的元素个数
    }


    void test1() {
        String value;
        // 1. k-v存储
        jedis.set("name", "helloword");
        value = jedis.get("name");
        //设置5秒过期
        jedis.expire("name", 5);
        System.out.println("1: " + value);
        // 2. 将新增的值添加到旧值后面
        jedis.append("name", " append new info,");
        value = jedis.get("name");
        System.out.println("2: " + value);
        // 3. 删除key对应的记录
        // jedis.del("name");
        // value = jedis.get("name");
        // System.out.println("3: " + value);
        // 4. 批量设值
        jedis.mset("name1", "minxr", "name2", "aaa");
        System.out.println("4: " + jedis.mget("name1", "name2"));
        // 5. 清空数据，所有的
        // System.out.println(jedis.flushDB());
        // 6. 截取value的值
        System.out.println("6: " + jedis.getrange("name", 1, 3));
        // 7.按通配符模糊查找符合条件的key
        System.out.println("7: " + jedis.keys("*na*"));
        // 8. 数据库大小
        System.out.println("8: " + jedis.dbSize());

        // 9. k-v存储，v表示的List(队列形式)   //先进先出
        jedis.del("messages");
        jedis.rpush("messages", "how");
        jedis.rpush("messages", "are");
        jedis.rpush("messages", "you");
        // 返回messages对应的集合长度
        System.out.println("9: " + jedis.llen("messages"));
        // 取数据，第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        List<String> values = jedis.lrange("messages", 0, 1);
        System.out.println("9: " + values);

        // 10. k-v存储，v表示的List(堆栈形式)  //后进先出
        jedis.del("desc");
        jedis.lpush("desc", "1");
        jedis.lpush("desc", "2");
        jedis.lpush("desc", "3");
        jedis.lpush("desc", "4");
        // 数组长度
        System.out.println("10: " + jedis.llen("desc"));
        System.out.println("10: " + jedis.lrange("desc", 0, -1));

        // 11. 取出库中所有的key
        for (String a : jedis.keys("*")) {
            System.out.println("======================");
            System.out.println(a);
        }
    }
}
