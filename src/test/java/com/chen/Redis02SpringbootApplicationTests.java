package com.chen;

import com.chen.pojo.User;
import com.chen.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1(){
        redisUtil.set("name","test");
        System.out.println(redisUtil.get("name"));
    }
    @Test
    void contextLoads() {
        // 在企业开发中，我们80%的情况下都不会使用这个原生的方法去编写代码
        // RedisUtil

        // RedisTemplate 操作不同的数据类型，api和我们的指令是一样的
        // opsForValue 操作字符串 类似于string
        // opsForList 操作list 类似于list
        // opsForSet
        // opsForHash
        // opsForGeo
        // opsForHyperLogLog

        // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate来操作，比如事务，和基本的crud

        // 获取redis的连接对象
        /*RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();
        connection.flushDb();*/

        redisTemplate.opsForValue().set("mykey","关注我");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }
    @Test
    public void test() throws JsonProcessingException {
        // 真实的开发一般都是用json来传递对象
        User user = new User("陈", 3);
//        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
