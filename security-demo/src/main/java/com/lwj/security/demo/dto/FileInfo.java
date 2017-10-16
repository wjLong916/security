package com.lwj.security.demo.dto;

import lombok.Data;

/**
 * 文件类
 *
 * @author 钱隆
 * @create 2017/10/14 12:32
 **/
public @Data class FileInfo {
    private Long id;
    private String name;
    private String path;
    private Long size;
}
