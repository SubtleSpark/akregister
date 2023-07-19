package com.huangcj.akcheck.common;

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
    /**
     * 没有实例化RestTemplate时，初始化RestTemplate
     */
    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
