package com.cx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/2
 * @Version: 1.0
 */
@Configuration
//@EnableRedisRepositories(basePackages = "com.cx.repository.redis")
@EnableElasticsearchRepositories(basePackages = "com.cx.repository.es")
@EnableJpaRepositories(basePackages = "com.cx.repository.jpa")
public class GlobalConfig {
}
