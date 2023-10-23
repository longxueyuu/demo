package com.example.restservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "local")
public class Config {
    private String host;
    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Config{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
