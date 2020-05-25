package org.huangzi.main.wx.ma.task;

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
