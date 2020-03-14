package com.ls.security.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.ls.security.demo.validator.MyConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

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
    private String id;

    @JsonView(UserSimpleView.class)
    @MyConstraint(message = "这是一个校验测试")
    private String username;

    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonView(UserSimpleView.class)
    @Past(message = "生日必须是过去的时间")
    private Date birthday;
}
