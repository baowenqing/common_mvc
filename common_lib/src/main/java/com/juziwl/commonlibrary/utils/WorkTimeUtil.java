package com.juziwl.commonlibrary.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 文庆
 * @date 2019/3/29.
 * @description 设置工作时间 工具类
 * 默认8:00--18:00  起始时间包含  截止时间（分钟）不包含
 */
public class WorkTimeUtil {

    private volatile static WorkTimeUtil instance;
    private String workTime = "";
    private Integer[] workTimeArr = new Integer[]{};
    private final String REGEX_FLAG = "至";

    public static WorkTimeUtil getInstance() {
        if (instance == null) {
            synchronized (WorkTimeUtil.class) {
                if (instance == null) {
                    instance = new WorkTimeUtil();
                }
            }
        }
        return instance;
    }

    public void init() {

    }


    /**
     * 设置工作时间
     *
     * @param startTime xx:xx
     * @param endTime   xx:xx
     * @param arr       0周日 1-6对应周一到
     */
    public void setWorkTime(String startTime, String endTime, Integer[] arr) {
        workTime = startTime.concat(REGEX_FLAG).concat(endTime);
        workTimeArr = arr;
    }


    /**
     * 取消设置工作时间
     */
    public void cancelWorkTime() {
        workTime = "";
    }

    /**
     * 是否设置过 工作时间
     *
     * @return
     */
    public boolean isHasWorkTime() {
        return !TextUtils.isEmpty(workTime);
    }

    /**
     * 是否处于工作时间
     *
     * @return
     */
    public boolean isBetweenWorkTime() {
        try {
            if (isHasWorkTime()) {
                if (!Arrays.<Integer>asList(getDayArr()).contains(TimeUtils.getWeekDay())) {
                    return false;
                }
                Date originalDate = TimeUtils.HHMM.get().parse(getStartTime());
                long startTime = originalDate.getTime();
                Date endDate = TimeUtils.HHMM.get().parse(getEndTime());
                long endaTime = endDate.getTime();
                String nowTime = TimeUtils.stringDateToStringDate(TimeUtils.getCurrentTime(), TimeUtils.YYYYMMDDHHMMSS, TimeUtils.HHMM);
                long time = TimeUtils.HHMM.get().parse(nowTime).getTime();
                if (time >= startTime && time < endaTime) {
                    return true;
                } else {
                    return false;
                }
            } else {
//                、、默认整天都是工作时间
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 返回起始时间
     *
     * @return XX:XX
     */
    public String getStartTime() {
        String reslut = "";
        if (workTime.contains(REGEX_FLAG)) {
            reslut = workTime.split(REGEX_FLAG)[0];
        }
        return reslut;
    }

    /**
     * 返回截止时间
     *
     * @return XX:XX
     */
    public String getEndTime() {
        String reslut = "";
        if (workTime.contains(REGEX_FLAG)) {
            reslut = workTime.split(REGEX_FLAG)[1];
        }
        return reslut;
    }


    /**
     * 返回设置工作日 数组
     *
     * @return 数组
     */
    public Integer[] getDayArr() {
        return workTimeArr;
    }

    /**
     * 清除设置
     */
    public void reset() {
        workTime = "";
        workTimeArr = new Integer[]{};
    }
}
