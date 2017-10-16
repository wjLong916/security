package com.lwj.security.demo.web.controller;

import com.lwj.security.demo.dto.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author 钱隆
 * @create 2017/10/14 12:31
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    String folder = "D:\\saveFile\\temp";
    @PostMapping("/upload")
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(localFile.getName());
        fileInfo.setPath(localFile.getAbsolutePath());
        fileInfo.setSize(file.getSize());
        return fileInfo;
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try(FileInputStream inputStream = new FileInputStream(new File(folder, fileName.concat(".txt")));
            OutputStream outputStream = response.getOutputStream()){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);
        }

    }
}
