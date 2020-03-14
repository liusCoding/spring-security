package com.ls.security.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @className: AsyncController
 * @description: 异步处理Controller
 * @author: liusCoding
 * @create: 2020-03-13 14:51
 */

@RestController
@Slf4j
@RequestMapping("/order")
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping
    public DeferredResult<String> order() throws InterruptedException {
        log.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setCompleteOrder(orderNumber);


        DeferredResult<String> deferredResult = new DeferredResult<>();

        deferredResultHolder.getMap().put(orderNumber,deferredResult);

//        Callable<String> result = () -> {
//            log.info("副线程开始");
//            Thread.sleep(1000);
//            log.info("副线程返回");
//            return "success";
//        };



        log.info("主线程返回");

        return deferredResult;
    }

}
