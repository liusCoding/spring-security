package com.ls.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: DemoController
 * @description: 演示Controller
 * @author: liusCoding
 * @create: 2020-03-10 10:57
 */

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}
