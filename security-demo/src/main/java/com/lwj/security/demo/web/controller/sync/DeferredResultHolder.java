package com.lwj.security.demo.web.controller.sync;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author 钱隆
 * @create 2017/10/14 23:02
 **/
@Component
public class DeferredResultHolder {
    private Map<String,DeferredResult<String>> deferredResultMap = Maps.newHashMap();

    public Map<String, DeferredResult<String>> getDeferredResultMap() {
        return deferredResultMap;
    }

    public void setDeferredResultMap(Map<String, DeferredResult<String>> deferredResultMap) {
        this.deferredResultMap = deferredResultMap;
    }
}
