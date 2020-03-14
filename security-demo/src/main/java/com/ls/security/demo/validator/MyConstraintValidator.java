package com.ls.security.demo.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @className: MyConstraintValidator
 * @description: 自定义注解
 * @author: liusCoding
 * @create: 2020-03-10 18:21
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {
    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        log.info("【自定义校验】:{}",o);
        return false;
    }
}
