package org.huangzi.main.ma.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * @author: XGLLHZ
 * @date: 2020/4/14 上午11:26
 * @description: 定时任务-动态: 基于接口
 */
/*@Configuration
@EnableScheduling
public class ScheduledDynamicTask implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //添加定时任务
                () -> System.out.println("测试定时任务: " + System.currentTimeMillis()),
                //设置执行周期
                triggerContext -> {
                    //从数据库读取时间 然后返回时间
                    return new CronTrigger().nextExecutionTime(triggerContext);
                }
        );
    }

}*/
