package com.ls.security.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ls.security.demo.SecurityDemoApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @className: UserControllerTest
 * @description: 用户controller测试
 * @author: liusCoding
 * @create: 2020-03-10 14:36
 */

@Slf4j
public class UserControllerTest  extends SecurityDemoApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username", "coco")
                .param("size", "15")
                .param("page", "3")
                .param("sort", "age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();


        log.info("【查询结果】:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
        //jsonPath 了解GitHub搜索jsonpath

    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();

        log.info("【查询结果】:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }


    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/a").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


    @Test
    public void whenCreateSuccess() throws Exception {
        Long birthday = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+birthday+"}";
        log.info("Date:{},LocalDateTime:{}",new Date().getTime(),birthday);

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        log.info("【查询结果】:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }


    @Test
    public void whenUpdateSuccess() throws Exception {
        Long birthday = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":"+birthday+"}";
        log.info("Date:{},LocalDateTime:{}",new Date().getTime(),birthday);

        String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        log.info("【查询结果】:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }


    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    /**
     * 模拟文件上传
     * @date: 2020/3/13
     * @param
     * @return: void
     **/
    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
                .file(new MockMultipartFile("file", "text.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.info("【文件上传】：{}",result,SerializerFeature.PrettyFormat);
    }



}
