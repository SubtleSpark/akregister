package com.huangcj.akcheck.common;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: HuangChengJun
 * @Date: 2023/7/19 10:51
 * @Description:
 */
@Configuration
public class CommonCfg {
    @Value("${zk-addr}")
    private String zkAddr;
    @Value("${spring.application.name}")
    private String namespace;

    /**
     * 没有实例化RestTemplate时，初始化RestTemplate
     */
    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CuratorFramework curator() {
        int sleepTime = 1000;
        int maxTry = 10;
        int connectTimeOut = 5000;
        int sessionTimeOut = 5000;

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(sleepTime, maxTry);
        CuratorFramework businessClient = CuratorFrameworkFactory.builder()
                .connectString(zkAddr)
                .namespace(namespace)
                .connectionTimeoutMs(connectTimeOut)
                .sessionTimeoutMs(sessionTimeOut)
                .retryPolicy(retryPolicy)
                .build();
        businessClient.start();
        return businessClient;
    }
}
