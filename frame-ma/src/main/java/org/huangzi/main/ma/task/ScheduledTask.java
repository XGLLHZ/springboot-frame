package org.huangzi.main.ma.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.ma.entity.EventsEntity;
import org.huangzi.main.ma.mapper.EventsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/4/14 上午10:34
 * @description: 定时任务 静态: 基于注解; 动态: 基于接口; 多线程定时任务
 */
@Configuration   //标识配置类 兼容 Component 的效果
@EnableScheduling   //开启定时任务
public class ScheduledTask {

    @Autowired
    private EventsMapper eventsMapper;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void sendMsgTask() {
        List<EventsEntity> list = eventsMapper.selectList(new QueryWrapper<EventsEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE));
        if (list != null && list.size() > 0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String yearMd = dateFormat.format(new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
            String monthD = yearMd.substring(5, 10);
            for (EventsEntity eventsEntity : list) {
                if (eventsEntity.getEventTime().contains(monthD) && !monthD.equals(eventsEntity.getEventTime())) {
                    String oldYear = eventsEntity.getEventTime().substring(0, 4);
                    String newYear = yearMd.substring(0, 4);
                    int years = Integer.parseInt(newYear) - Integer.parseInt(oldYear);
                    System.out.println("亲爱的高毓婵: " + "7天后，将是你和小哥哥" + eventsEntity.getEventContent()
                            + years + "周年，记得给他准备礼物或者别忘了给他一个爱的抱抱哦！");
                }
                if (monthD.equals(eventsEntity.getEventTime())) {
                    System.out.println("亲爱的高毓婵: " + "7天后，将是" + eventsEntity.getEventContent()
                    + "记得给他准备礼物或者别忘了给他一个爱的抱抱哦！");
                }
            }
        }
    }

    /**
     * 测试定时任务-注解式
     * cron 表达式参数表示:
     *      秒: 0-59 0/5: 5s
     *      分: 0-59 0/5 5m
     *      时: 0-23
     *      日: 0-31
     *      月: 0-11
     *      周: 1-7 or SUN/MON/TUE/WED/THU/FRI/SAT
     */
    //@Scheduled(cron = "0 0/1 * * * ?")   //1m
    //@Scheduled(fixedRate = 5000)   5s
    public void TestTask() {
        System.out.println("测试定时任务: " + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = simpleDateFormat.format(new Date().getTime());
        System.out.println(s.substring(5, 10));
    }

}
