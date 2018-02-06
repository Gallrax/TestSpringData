package com.cx.config;

import com.cx.repository.es.ESUserRepository;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.NodeClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.UUID;

/**
 * @Author: 冯冠凯
 * @Description:
 * @Date: Created on 2018/2/6
 * @Version: 1.0
 */
@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfiguration {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private ESUserRepository esUserRepository;

    @Bean
    public NodeClientFactoryBean client() {

        NodeClientFactoryBean bean = new NodeClientFactoryBean(true);
        bean.setClusterName("es");
        bean.setEnableHttp(false);
        bean.setPathData("target/elasticsearchTestData");
        bean.setPathHome("src/test/resources/test-home-dir");

        return bean;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {
        return new ElasticsearchTemplate(client);
    }

}
