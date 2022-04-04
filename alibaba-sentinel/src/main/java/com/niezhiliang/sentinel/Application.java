package com.niezhiliang.sentinel;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @Author : niezhiliang
 * @Date : 2022/4/3
 */
@SpringBootApplication(scanBasePackages = "com.niezhiliang")
@EnableDiscoveryClient
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public Logger.Level feignLoggerLebel() {
        return Logger.Level.FULL;
    }
}
