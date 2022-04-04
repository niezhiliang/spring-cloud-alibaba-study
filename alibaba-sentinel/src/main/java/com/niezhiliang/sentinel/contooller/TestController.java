package com.niezhiliang.sentinel.contooller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.niezhiliang.sentinel.service.FeignTestService;
import com.niezhiliang.sentinel.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * @Author : niezhiliang
 * @Date : 2022/4/3
 */
@RestController
public class TestController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private FeignTestService feignTestService;

    @GetMapping("testA")
    public String testA() {
        return commonService.common();
    }

    @GetMapping("testB")
    public String testB() {
        return commonService.common();
    }


    /**
     * openFeign使用及Sentinel降级
     * sentinel配置持久化
     * @param id
     * @return
     */
    @GetMapping(value = "openfeign/{id}")
    @SentinelResource(value = "openFiegn")
    public String openFiegn(@PathVariable(value = "id") Long id) {

        return feignTestService.test(id);
    }


    /**
     * 热点参数降级
     * @param hot1
     * @param hot2
     * @return
     */
    @GetMapping("testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "testHotKeyFallBack",fallback = "fallBackHandler")
    public String testHotKey(@RequestParam(value = "hot1",required = false) String hot1
                           , @RequestParam(value = "hot2",required = false) String hot2) {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("随机异常");
        }
        return "---testHotKey";
    }

    /**
     * 只处理Sentinel的异常
     * @param hot1
     * @param hot2
     * @param exception
     * @return
     */
    public String testHotKeyFallBack(String hot1, String hot2, BlockException exception) {
        return "热点参数被限流";
    }


    /**
     * 处理Java程序异常
     * @param hot1
     * @param hot2
     * @param e
     * @return
     */
    public String fallBackHandler(String hot1, String hot2, Throwable e) {
        return "代码异常执行降级,异常信息:" + e.getMessage();
    }
}
