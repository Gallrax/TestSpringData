package com.cx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/6
 * @Version: 1.0
 */
@Configuration
//@EnableAutoConfiguration
@EnableRedisRepositories(basePackages = "com.cx.repository.redis")
@PropertySource("classpath:/application.yml")
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String hostName;
    @Value("${spring.redis.port}")
    private Integer port;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPort(port);
        System.out.println("connectionFactory hostName : " + hostName + " port : " + port);
        return jedisConnectionFactory;
    }

    /**
     * @Author: 冯冠凯 
     * @Description: 使用Repository方式
     * @Date: Created on 2018/2/7
     * @Version: 1.0
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
        template.setConnectionFactory(factory);
        return template;
    }

    /**
     * @Author: 冯冠凯 
     * @Description: 使用RedisTemplate方式
     * @Date: Created on 2018/2/7
     * @Version: 1.0
     */
    /*@Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        String hostName = jedisConnectionFactory.getHostName();
        int port = jedisConnectionFactory.getPort();
        System.out.println(" redisTemplate hostName : " + hostName + " port : " + port);
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPort(port);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }*/
}
