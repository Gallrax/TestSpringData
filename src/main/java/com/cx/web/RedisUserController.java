package com.cx.web;

import com.alibaba.fastjson.JSON;
import com.cx.entity.RedisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/7
 * @Version: 1.0
 */
@RestController
@RequestMapping("/redisUser")
public class RedisUserController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ModelAttribute
    public void before() {
        JedisConnectionFactory connectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        String hostName = connectionFactory.getHostName();
        int port = connectionFactory.getPort();
        System.out.println(" hostName : " + hostName + " port : " + port);
    }

    @RequestMapping("/index")
    public String index() {
        RedisUser redisUser = new RedisUser("Aa", "a", 1, "Aa@chaoxing.com");
//        Long add = redisTemplate.opsForSet().add(UUID.randomUUID().toString(), redisUser);
        redisTemplate.opsForValue().set(UUID.randomUUID().toString(), JSON.toJSON(redisUser).toString());
        return "ok";
    }

    @RequestMapping("/save")
    public RedisUser save() {
        String uuid = UUID.randomUUID().toString();
        RedisUser redisUser = new RedisUser(uuid, uuid.toLowerCase().charAt(0) + "", 1, uuid + "@chaoxing.com");
        redisTemplate.opsForValue().set(uuid, JSON.toJSON(redisUser).toString());
        return redisUser;
    }

    @RequestMapping("/findAll")
    public List<RedisUser> findAll() {
        Set<String> keys = redisTemplate.keys("*");
        List<RedisUser> list = new ArrayList<RedisUser>();
        for (String key : keys) {
            String o = (String) redisTemplate.opsForValue().get(key);
            System.out.println(o);
            RedisUser temp = JSON.parseObject(o, RedisUser.class);
            list.add(temp);
        }
        return list;
    }

    @RequestMapping("/find/{id}")
    public RedisUser find(@PathVariable("id") Integer id) {
        RedisUser redisUser = (RedisUser) redisTemplate.opsForValue().get(id);
        return redisUser;
    }
}
