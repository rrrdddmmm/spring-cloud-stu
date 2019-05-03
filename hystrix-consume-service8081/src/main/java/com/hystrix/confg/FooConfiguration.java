package com.hystrix.confg;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类为Feign的配置类
 * 注意：不应该在主应用程序的上下文@ComponentScan能够扫描的位置
 */
@Configuration
public class FooConfiguration {

    /**
     * 修饰的接口需要进行basic认证之后才可以进行调用
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user", "password");
    }
}
