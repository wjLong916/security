package com.lwj.security.demo.web.controller.sync;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;


/**
 * ${DESCRIPTION}
 *
 * author 钱隆
 * create 2017/10/14 18:11
 **/
@RestController
@RequestMapping("syncController")
public class SyncController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order")
    public Callable<String> getOrder() throws Exception {
        log.info("主线程开始");
        Callable<String> callable = new Callable<String>(){
            @Override
            public String call() throws Exception {
                log.info("子线程开始");
                Thread.sleep(1000);
                log.info("子线程结束");
                return "success";
            }
        };
        log.info("主线程结束");
        return callable;
    }

    @GetMapping("/getOrder")
    public DeferredResult<String> findOrder() throws InterruptedException {
        log.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getDeferredResultMap().put(orderNumber,result);
        log.info("主线程结束");
        return result;
    }
}
