package com.ly.redis;

import redis.clients.jedis.Jedis;

import java.util.Date;

public class HelloWorld {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.set("hello","world--"+new Date());
        System.out.println(jedis.get("hello"));
        jedis.close();
    }
}
