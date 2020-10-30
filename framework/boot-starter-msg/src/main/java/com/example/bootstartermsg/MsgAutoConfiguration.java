package com.example.bootstartermsg;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author wangxi03 created on 2020/10/30 11:01 AM
 * @version v1.0
 */
@Configuration
@ConditionalOnClass(MsgService.class)
@EnableConfigurationProperties(MsgProperties.class)
public class MsgAutoConfiguration {

    @Resource
    private MsgProperties msgProperties;

    @Bean
    @ConditionalOnMissingBean(MsgService.class)
    // @ConditionalOnProperty表示当配置文件中msg.enabled=true时才进行相应的实例化操作，默认情况下不会进行实例化操作。
    @ConditionalOnProperty(prefix = "msg", value = "enable", havingValue = "true")
    public MsgService msgService() {
        MsgService msgService = new MsgService(msgProperties.getUsername(), msgProperties.getPassword());

        return msgService;
    }
}
