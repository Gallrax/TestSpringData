package com.cx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/2
 * @Version: 1.0
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.cx.repository.jpa")
public class GlobalConfig {
}
