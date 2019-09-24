package org.hinsteny.scn.biz.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version HystrixController: 2019-09-19 09:10 All rights reserved.$
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello")
    public String hello(@RequestParam("time") int time) {
        logger.info("[{}] hystrix say hello with time {}", Thread.currentThread().getName(), time);
        return "Hello Hystrix";
    }

    @GetMapping("/hhello")
    @HystrixCommand(commandProperties = {
        @HystrixProperty(
            name = "execution.isolation.thread.timeoutInMilliseconds", // 熔断类型
            value = "20" // 超时熔断的时间
        )
    }, observableExecutionMode = ObservableExecutionMode.LAZY, fallbackMethod = "fallbackMethod")
    public String hhello(@RequestParam(value = "time", defaultValue = "10") int costTime) throws InterruptedException {
        logger.info("[{}] hystrix say hello with time {}", Thread.currentThread().getName(), costTime);
        // 模拟服务耗时
        Thread.sleep(costTime);
        System.out.printf("[%s] Execution costs time : %d\n", Thread.currentThread().getName(), costTime);
        return "Hello Hystrix";
    }

    public String fallbackMethod(int costTime) {
        return "fallback - costTime : " + costTime + "ma";
    }
}
