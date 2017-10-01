package com.lwj.security.demo.service.impl;

import com.lwj.security.demo.service.IHellorService;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author 钱隆
 * @create 2017-10-01 20:47
 **/
@Service
public class HellorService implements IHellorService {
    @Override
    public String getHellor() {
        return "Good";
    }
}
