package com.ls.security.demo.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @className: QueueListener
 * @description:
 * @author: liusCoding
 * @create: 2020-03-13 16:44
 */
@Data
@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Thread thread = new Thread(()->{
            while(true){
                if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                    String completeOrder = mockQueue.getCompleteOrder();
                    log.info("返回订单处理结果："+completeOrder);
                    DeferredResult<String> deferredResult = deferredResultHolder.getMap().get("completeOrder");
                    deferredResult.setResult("placeorder success");
                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();



    }
}
