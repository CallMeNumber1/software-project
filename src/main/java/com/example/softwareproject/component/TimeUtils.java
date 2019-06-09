package com.example.softwareproject.component;

import com.example.softwareproject.entity.Exam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: software-project
 * @description: 时间处理判断工具, minTime(), maxTime(), isTimeConflict()
 * @author: zhanyeye
 * @create: 2019-06-09 08:26
 */
@Component
public class TimeUtils {
    //返回最小时间
    public LocalDateTime minTime(LocalDateTime t1, LocalDateTime t2) {
        return t1.compareTo(t2) < 0 ? t1 : t2;
    }

    //返回最大时间
    public LocalDateTime maxTime(LocalDateTime t1, LocalDateTime t2) {
        return t1.compareTo(t2) > 0 ? t1 : t2;
    }

    //比较考试的时间是否冲突,传入的函数是2个Exam对象
    public boolean isTimeConflict (Exam e1, Exam e2) {
        LocalDateTime LastStartTime = maxTime(e1.getBeginTime(), e2.getBeginTime());   //最晚开始时间
        LocalDateTime EarliestFinishTime = minTime(e1.getEndTime(), e2.getEndTime());  //最早结束时间
        return LastStartTime.compareTo(EarliestFinishTime) > 0 ? true : false;
    }
}
