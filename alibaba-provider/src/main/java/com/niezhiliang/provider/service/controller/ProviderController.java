package com.niezhiliang.provider.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : niezhiliang
 * @Date : 2022/4/4
 */
@RestController
public class ProviderController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping(value = "get/{id}")
    public String test(@PathVariable(value = "id") Long id) {

        return port + " : " + id;
    }
}
