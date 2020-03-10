package com.ls.security.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @className: User
 * @description:
 * @author: liusCoding
 * @create: 2020-03-10 14:50
 */
@Data
public class User {

    /**
     * @JsonView 使用步骤
     * 1.使用接口来声明多个视图
     * 2.在值对象的get方法上指定视图
     * 3.在controller方法上指定视图
     **/

    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView {};

    @JsonView(UserSimpleView.class)
    private String username;

    @JsonView(UserDetailView.class)
    private String password;
}
