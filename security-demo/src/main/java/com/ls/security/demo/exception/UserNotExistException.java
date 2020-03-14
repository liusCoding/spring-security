package com.ls.security.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @className: UserNotExistException
 * @description:
 * @author: liusCoding
 * @create: 2020-03-12 15:11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserNotExistException extends RuntimeException implements Serializable {

    private String id;

    public UserNotExistException(String id){
        super("user not exist");

        this.id = id;
    }
}
