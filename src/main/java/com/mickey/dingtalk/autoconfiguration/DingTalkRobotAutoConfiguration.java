package com.mickey.dingtalk.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * DingTalk机器人的AutoConfiguration，默认自动化加载一些配置
 */
@Configuration
public class DingTalkRobotAutoConfiguration {

    /**
     * 创建一个RestTemplate客户端，并注册到Spring容器，供后续程序调用
     * @return RestTemplate客户端
     */
    @Bean(name = "dingTalkRobotRestTemplate")
    @ConditionalOnMissingBean
    public RestTemplate dingTalkRobotRestTemplate() {
        return new RestTemplate();
    }
}