package com.ls.security.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonView;
import com.ls.security.demo.dto.User;
import com.ls.security.demo.dto.UserQueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @className: UserController
 * @description: 用户controller
 * @author: liusCoding
 * @create: 2020-03-10 14:48
 */

@RestController
@Slf4j

public class UserController {


    @GetMapping("/user")
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition userQueryCondition, @PageableDefault Pageable pageable){

        log.info("【分页参数】:{}", JSON.toJSONString(pageable, SerializerFeature.PrettyFormat));
        log.info("username:{}",userQueryCondition);
        return  Stream.of(new User(),new User(),new User()).collect(Collectors.toList());

    }


    /**
     * restful参数可以使用正则表达式
     * @date: 2020/3/10
     * @param id
     * @return: com.ls.security.demo.dto.User
     **/
    @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)

    @GetMapping("/user/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id){

        log.info("id:{}",id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }
}
