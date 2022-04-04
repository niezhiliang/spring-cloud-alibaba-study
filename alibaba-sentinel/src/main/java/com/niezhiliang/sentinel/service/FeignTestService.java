package com.niezhiliang.sentinel.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author : niezhiliang
 * @Date : 2022/4/4
 */
@FeignClient(value = "provider-service",fallback = FeignTestServiceImpl.class)
@Service
public interface FeignTestService {

    @GetMapping(value = "get/{id}")
    String test(@PathVariable(value = "id") Long id);
}

@Component
class FeignTestServiceImpl implements FeignTestService {

    @Override
    public String test(Long id) {
        return "Feign服务降级。。。。";
    }
}
