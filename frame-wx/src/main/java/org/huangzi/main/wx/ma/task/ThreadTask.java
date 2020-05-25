package org.huangzi.main.wx.ma.task;

import java.time.LocalDateTime;

/**
 * @author: XGLLHZ
 * @date: 2020/4/14 上午11:50
 * @description: 基于注解的多线程定时任务
 */
//@Configuration   //设置配置类
//@EnableScheduling   //开启定时任务
//@EnableAsync   //开启多线程
public class ThreadTask {

    //@Async   //异步线程
    //@Scheduled(fixedDelay = 5000)
    public void firstTask() throws InterruptedException {
        System.out.println("第一个多线程定时任务: " + LocalDateTime.now().toLocalTime() + "\n" +
                "线程: " + Thread.currentThread().getName());
        Thread.sleep(1000 * 5);
    }

    //@Async
    //@Scheduled(fixedDelay = 10000)
    public void secondTask() {
        System.out.println("第二个多线程定时任务: " + LocalDateTime.now().toLocalTime() + "\n" +
                "线程: " + Thread.currentThread().getName());
    }

}
