package com.ls.security.demo.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: DeferredResultHolder
 * @description:
 * @author: liusCoding
 * @create: 2020-03-13 16:29
 */

@Component
@Data
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> map = new HashMap<>(16);



}
