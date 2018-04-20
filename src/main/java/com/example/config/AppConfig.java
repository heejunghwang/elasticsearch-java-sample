package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    public static String host;
    public static Integer port;


    @Value("${elasticsearch.host}")
    public void setHost(String elasticsearchHost) {
        this.host = elasticsearchHost;
    }

    @Value("${elasticsearch.port}")
    public void setPort(Integer elasticsearchPort) {
        this.port = elasticsearchPort;
    }

}
