package com.lwj.security.demo.web.controller.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author 钱隆
 * @create 2017/10/14 22:48
 **/
@Component
public class MockQueue {
    private Logger log = LoggerFactory.getLogger(getClass());

    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(()->{
            log.info("接到下单请求,订单号 ： {}",placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.info(e.getMessage());
                log.info(e.getStackTrace().toString());
            }
            this.completeOrder = this.placeOrder = placeOrder;
            log.info("下单请求处理完毕,订单号 ： {}",placeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
