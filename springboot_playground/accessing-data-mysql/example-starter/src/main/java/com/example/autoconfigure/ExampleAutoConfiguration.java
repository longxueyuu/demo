package com.example.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ExampleProperties.class)
public class ExampleAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ExampleBizService exampleBizService(ExampleProperties prop) {
        return new ExampleBizService(prop);
    }
}
