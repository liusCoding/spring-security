package com.ls.security.demo.controller;

import com.ls.security.demo.dto.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @className: FileController
 * @description:
 * @author: liusCoding
 * @create: 2020-03-13 11:31
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    String folder ="E:\\spring-security\\security-demo\\src\\main\\java\\com\\ls\\security\\demo\\controller";
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {

        log.info("【文件名】：{}",file.getName());
        log.info("【原始文件名】：{}",file.getOriginalFilename());
        log.info("【文件尺寸】：{}",file.getSize());

        File localFile = new File(folder,System.currentTimeMillis()+".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id , HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (
                InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
            OutputStream outputStream = response.getOutputStream();
        ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }

    }

}
