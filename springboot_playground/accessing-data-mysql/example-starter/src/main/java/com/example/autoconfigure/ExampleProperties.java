package com.example.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "example-starter")
@Data
public class ExampleProperties {
    private String name;

    private String description;
}
