package com.niezhiliang.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Component;

/**
 * @Author : niezhiliang
 * @Date : 2022/4/3
 */
@Component
public class CommonService {

    @SentinelResource("common")
    public String common() {
        return "common";
    }
}
