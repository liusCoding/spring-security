package com.ls.security.demo.controller;

import com.ls.security.demo.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: ControllerExceptionHandler
 * @description:
 * @author: liusCoding
 * @create: 2020-03-12 15:30
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleUserNotException(UserNotExistException e){
        Map<String,Object> result = new HashMap<>(16);
        result.put("id",e.getId());
        result.put("message",e.getMessage());

        return result;
    }
}
