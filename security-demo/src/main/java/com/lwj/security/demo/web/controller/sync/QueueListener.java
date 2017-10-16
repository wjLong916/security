package com.lwj.security.demo.web.controller.sync;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author 钱隆
 * @create 2017/10/14 23:14
 **/
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while (true){
                if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                    String orderNumber = mockQueue.getCompleteOrder();
                    log.info("返回订单处理结果{}", orderNumber);
                    deferredResultHolder.getDeferredResultMap().get(orderNumber).setResult("order success");
                    mockQueue.setCompleteOrder(null);
                }else{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.info(e.getMessage());
                        log.info(e.getStackTrace().toString());
                    }
                }
            }
        }).start();
    }
}
