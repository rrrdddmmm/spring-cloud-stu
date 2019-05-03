package com.consume.rules;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRule {

    @Bean
    public IRule ribbonRule() {
//      return new RandomRule(); // 这里配置策略,随机策略配置
        return new CustomRandomRule(); // 自定义使用轮询的方式，但是每个服务请求四次之后换下一个
    }
}
