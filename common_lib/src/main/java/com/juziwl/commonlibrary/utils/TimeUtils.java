package com.juziwl.commonlibrary.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/06/28
 * @modify Neil
 * @modifydate 2017/11/1
 * @description 时间工具类
 */
public class TimeUtils {

    public static final int NUMBER_1 = 1;
    public static final int NUMBER_2 = 2;
    public static final int NUMBER_10 = 10;
    public static final int NUMBER_60 = 60;
    public static final String TYPE_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String TYPE_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    /**
     * 线程安全的时间格式化:yyyy-MM-dd HH:mm:ss
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
    };
    public static ThreadLocal<SimpleDateFormat> YYYYDOTMMDOTDDHHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault());
        }
    };

    public static ThreadLocal<SimpleDateFormat> YYYYDOTMMDOTDD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        }
    };

    /**
     * 线程安全的时间格式化:YYYYmmDDHHMM
     */
    public static ThreadLocal<SimpleDateFormat> YYYYmmDDHHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        }
    };

    /**
     * 线程安全的时间格式化:yyyy-MM-dd 00:00:00
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSSZero = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.getDefault());
        }
    };

    /**
     * 线程安全的时间格式化:MM/dd HH:mm
     */
    public static ThreadLocal<SimpleDateFormat> MMDDHHMMXIE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM/dd HH:mm", Locale.getDefault());
        }
    };

    /**
     * yyyy-MM-dd
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
    };
    /**
     * yyyy-MM
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM", Locale.getDefault());
        }
    };

    /**
     * MM月dd日 HH:mm
     */
    public static ThreadLocal<SimpleDateFormat> MMDDHHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM月dd日 HH:mm", Locale.getDefault());
        }
    };

    /**
     * MM-dd HH:mm
     */
    public static ThreadLocal<SimpleDateFormat> MM_DDHHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
        }
    };

    /**
     * MM月dd日
     */
    public static ThreadLocal<SimpleDateFormat> MMYDDR = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM月dd日", Locale.getDefault());
        }
    };

    /**
     * mm月dd日
     */
    public static ThreadLocal<SimpleDateFormat> MMYDDR2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("M月dd日", Locale.getDefault());
        }
    };

    /**
     * MM/dd
     */
    public static ThreadLocal<SimpleDateFormat> MMYXIEDDR = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM/dd", Locale.getDefault());
        }
    };


    /**
     * yyyy年MM月dd日 HH点mm分
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMChinese = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日 HH点mm分", Locale.getDefault());
        }
    };
    /**
     * yyyy年MM月dd日HH点mm分
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMChineseNoBlank = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日HH点mm分", Locale.getDefault());
        }
    };

    /**
     * yyyy年MM月dd日
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDChinese = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        }
    };

    /**
     * yyyy年
     */
    public static ThreadLocal<SimpleDateFormat> YYYYChinese = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年", Locale.getDefault());
        }
    };

    /**
     * MM月dd日
     */
    public static ThreadLocal<SimpleDateFormat> MMDDChinese = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM月dd日", Locale.getDefault());
        }
    };

    /**
     * HH:mm
     */
    public static ThreadLocal<SimpleDateFormat> HHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm", Locale.getDefault());
        }
    };

    /**
     * HH:mm:ss
     */
    public static ThreadLocal<SimpleDateFormat> HHMMSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        }
    };

    /**
     * yyyyMMddHHmmssSSS
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS_SSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
        }
    };


    /**
     * yyyyMMddHHmmss
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS_NUMBER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        }
    };


    /**
     * yyyy年MM月dd日 HH:mm
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.getDefault());
        }
    };

    /**
     * "yyyy年MM月dd日
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDD_CH = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        }
    };

    /**
     * MM-dd
     */
    public static ThreadLocal<SimpleDateFormat> MMDD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd", Locale.getDefault());
        }
    };

    /**
     * MM-dd 10.1  10.2
     */
    public static ThreadLocal<SimpleDateFormat> MMDOTDD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM.dd", Locale.getDefault());
        }
    };

    /**
     * MM/dd
     */
    public static ThreadLocal<SimpleDateFormat> MM_DD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM/dd", Locale.getDefault());
        }
    };

    /**
     * yyyy-MM-dd HH:mm:ss:SSS
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSSSSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault());
        }
    };

    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime() {
        return YYYYMMDDHHMMSS.get().format(new Date());
    }


    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTimeNUMBER() {
        return YYYYMMDDHHMMSS_NUMBER.get().format(new Date());
    }


    /**
     * @return yyyy-MM-dd 00:00:00
     */
    public static String getCurrentTimeAttendance() {
        return YYYYMMDDHHMMSSZero.get().format(new Date());
    }

    public static String getTodayTime() {
        return YYYYMMDD.get().format(new Date());
    }


    public static String getCurrentTime(ThreadLocal<SimpleDateFormat> timeFormat) {
        return timeFormat.get().format(new Date());
    }

    public static String getFormatTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getFormatTime(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    /**
     * @return yyyyMMddHHmmssSSS
     */
    public static String getCurrentTimeForFileName() {
        return YYYYMMDDHHMMSS_SSS.get().format(new Date());
    }

    /**
     * 所有时间格式用这个方法<br/>
     * 规则：发布时间距当前时间10分钟之内，显示“刚刚”；<br/>
     * 1小时之内，以分钟为最小单位，显示XX分钟前；<br/>
     * 1小时以上1天以内的（即当天的），以小时为最小单位，显示：xx小时前；<br/>
     * 1天以上的，由于其时效性弱，所以通常以天为最小单位，如：昨天HH：MM<br/>
     * 两天以上，显示：xx月xx日 HH：MM<br/>
     * 1年以上，显示xxxx年xx月xx日<br/>
     *
     * @param time
     * @return
     */
    public static String formatTime(String time) {
        if (StringUtils.isEmpty(time)) {
            return "";
        }
        Calendar currentTime = Calendar.getInstance();
        Calendar needFormatTime = Calendar.getInstance();
        Calendar midTime = Calendar.getInstance();
        try {
            needFormatTime.setTime(YYYYMMDDHHMMSS.get().parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        midTime.add(Calendar.DAY_OF_YEAR, -NUMBER_1);
        boolean flag = midTime.get(Calendar.DAY_OF_YEAR) == needFormatTime.get(Calendar.DAY_OF_YEAR);
        // 1年以上，显示xxxx年xx月xx日 HH：MM
        if (currentTime.get(Calendar.YEAR) != needFormatTime.get(Calendar.YEAR)) {
            return YYYYMMDD_CH.get().format(needFormatTime.getTime());
        }
        // 一个月以上，显示：xx月xx日 HH：MM
        if ((currentTime.get(Calendar.MONTH) != needFormatTime.get(Calendar.MONTH)) && !flag) {
            return MMDDHHMM.get().format(needFormatTime.getTime());
        }
        // 两天以上，显示：xx月xx日 HH：MM
        int delta = currentTime.get(Calendar.DAY_OF_MONTH) - needFormatTime.get(Calendar.DAY_OF_MONTH);
        if (delta >= NUMBER_2 && !flag) {
            return MMDDHHMM.get().format(needFormatTime.getTime());
        }
        // 1天以上的，由于其时效性弱，所以通常以天为最小单位，如：昨天HH：MM
        if (delta == 1 || flag) {
            return "昨天 " + HHMM.get().format(needFormatTime.getTime());
        }
        // 1小时以上1天以内的（即当天的），以小时为最小单位，显示：xx小时前；
        long deltaInMills = currentTime.getTimeInMillis() - needFormatTime.getTimeInMillis();
        //大于一小时
        if (deltaInMills >= 60 * 60 * 1000) {
            return String.format(Locale.getDefault(), "%d小时前", deltaInMills / (60 * 60 * 1000));
        }
        //小于一小时
        if (deltaInMills < 60 * 60 * 1000 && deltaInMills >= 10 * 60 * 1000) {
            return String.format(Locale.getDefault(), "%d分钟前", deltaInMills / (60 * 1000));
        }
        // 10分钟之内，显示“刚刚”；
        return "刚刚";
    }

    /**
     * 把一种格式的时间转化成另一种格式
     *
     * @param date           要转化的时间
     * @param originalFormat 要转化的时间的原格式
     * @param toFormat       需要转化成的格式
     */
    public static String stringDateToStringDate(String date, ThreadLocal<SimpleDateFormat> originalFormat,
                                                ThreadLocal<SimpleDateFormat> toFormat) {
        if (StringUtils.isEmpty(date)) {
            return "";
        }
        try {
            Date originalDate = originalFormat.get().parse(date);
            return toFormat.get().format(originalDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 5分钟内不显示时刻<br/>
     * 24小时内： xx小时:xx分钟<br/>
     * 超过24小时~本年内:  xx月xx日  xx小时:xx分钟<br/>
     * 超过本年：xx年xx月xx日  xx小时:xx分钟<br/>
     *
     * @param time 时间戳
     * @return
     */
    public static String formatMsgTime(long time) {
        if (time == 0) {
            return "";
        }
        Calendar currentTime = Calendar.getInstance();
        Calendar needFormatTime = Calendar.getInstance();
        needFormatTime.setTimeInMillis(time);

        if (currentTime.get(Calendar.YEAR) != needFormatTime.get(Calendar.YEAR)) {
            return YYYYMMDDHHMM.get().format(needFormatTime.getTime());
        }
        if (currentTime.get(Calendar.MONTH) != needFormatTime.get(Calendar.MONTH) ||
                currentTime.get(Calendar.DAY_OF_MONTH) != needFormatTime.get(Calendar.DAY_OF_MONTH)) {
            return MMDDHHMM.get().format(needFormatTime.getTime());
        }
//        if (currentTime.get(Calendar.YEAR) != needFormatTime.get(Calendar.YEAR)
//                || currentTime.get(Calendar.MONTH) != needFormatTime.get(Calendar.MONTH)) {
//            return MMDDHHMM.get().format(needFormatTime.getTime());
//        }
//        int deltaDay = currentTime.get(Calendar.DAY_OF_MONTH) - currentTime.get(Calendar.DAY_OF_MONTH);
//        if (deltaDay >= NUMBER_2) {
//            return MMDDHHMM.get().format(needFormatTime.getTime());
//        }
//        if (deltaDay == 1) {
//            return "昨天 " + HHMM.get().format(needFormatTime.getTime());
//        }
        return HHMM.get().format(needFormatTime.getTime());
    }


    public static String getTime(String formate, long time) {
        // 可以方便地修改日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
        return dateFormat.format(time);
    }

    /**
     * 2017-12-29 20:18:26从这个格式的时间里获取月份
     */
    public static int getTimeofMonth(String time) {
        if (StringUtils.isEmpty(time) || time.length() < 7) {
            return 0;
        }
        return Integer.parseInt(time.substring(5, 7));
    }

    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getTimeofYear(String time) {
        if (StringUtils.isEmpty(time) || time.length() < 4) {
            return 0;
        }
        return Integer.parseInt(time.substring(0, 4));
    }

    /**
     * 获取上周一
     *
     * @param date
     * @return
     */
    public static Date getLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    /**
     * 获取本周一
     *
     * @param date
     * @return
     */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取当前日期是星期几  0周日 1到6正常
     *
     * @return
     */
    public static int getWeekDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return dayWeek;
    }


    /**
     * 获取下周一
     *
     * @param date
     * @return
     */
    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    public static Date string2Date(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String currentDate() {
        return currentDateByFormat(YYYYMMDD);
    }

    public static String currentDateByFormat(ThreadLocal<SimpleDateFormat> threadLocal) {
        return threadLocal.get().format(new Date());
    }

    public static String getWeekByTime(String time, String[] weeks) {
        if (!StringUtils.isEmpty(time)) {
            try {
                Date date = YYYYMMDDHHMMSS.get().parse(time);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int weekPosition = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                return weeks[weekPosition];
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * //两个时间的差值 返回格式由最後參數決定
     *
     * @param startTime
     * @param startTimeFormat
     * @param endTime
     * @param endTimeFormat
     * @param resultTime
     * @return
     */
    public static String getTimeDifference(String startTime, ThreadLocal<SimpleDateFormat> startTimeFormat,
                                           String endTime, ThreadLocal<SimpleDateFormat> endTimeFormat, ThreadLocal<SimpleDateFormat> resultTime) {
        try {
            Date startDate = startTimeFormat.get().parse(startTime);
            Date endDate = endTimeFormat.get().parse(endTime);
            long timeResutl = endDate.getTime() - startDate.getTime();

            long day = timeResutl / (24 * 60 * 60 * 1000);
            long hour = (timeResutl / (60 * 60 * 1000) - day * 24);
            long min = ((timeResutl / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (timeResutl / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            java.util.Calendar calendar = java.util.Calendar.getInstance();
            try {
                calendar.setTime(TimeUtils.YYYYMMDDHHMMSS.get().parse("1970-01-01 00:00:00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.add(Calendar.HOUR_OF_DAY, (int) hour);
            calendar.add(Calendar.MINUTE, (int) min);
            calendar.add(Calendar.SECOND, (int) s);
            String rusultTime = resultTime.get().format(calendar.getTime());
            return rusultTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long getTimeDifferenceLength(String startTime, ThreadLocal<SimpleDateFormat> startTimeFormat,
                                               String endTime, ThreadLocal<SimpleDateFormat> endTimeFormat) {
        try {
            Date startDate = startTimeFormat.get().parse(startTime);
            Date endDate = endTimeFormat.get().parse(endTime);
            long timeLong = endDate.getTime() - startDate.getTime();
            return timeLong;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 将日期格式  转换  为时间戳
     */
    public static long getTimeStampByDate(String date, ThreadLocal<SimpleDateFormat> format) {
        try {
            Date curDate = format.get().parse(date);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            try {
                return YYYYMMDDHHMMChineseNoBlank.get().parse(date).getTime();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 将 时间戳  转换  为日期格式
     */
    public static String getDateByTimeStamp(long time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }


    /**
     * 获取当前的时间格式化与服务器时间格式相同
     *
     * @return
     */
    public static String getCurrentTimeFormatServerDate() {
        return getDateByTimeStamp(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据时间戳获取年月日
     *
     * @param time
     * @return
     */
    public static int getTimeofDay(String time) {

        SimpleDateFormat sdf = YYYYMMDDHHMMSS.get();
        Calendar cal = Calendar.getInstance();
        Date dt = null;
        try {
            dt = sdf.parse(time);
            cal.setTime(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.get(Calendar.YEAR);
    }


    /**
     * 获取过去或者未来 任意天内的日期数组
     *
     * @param intervals intervals天内
     * @return 日期数组
     */
    public static List<String> getFetureDays(int intervals, String time, ThreadLocal<SimpleDateFormat> formate, ThreadLocal<SimpleDateFormat> resultFormate) {
        List<String> fetureDaysList = new ArrayList<>();
        for (int i = 0; i < intervals; i++) {
            fetureDaysList.add(getFetureDate(i, time, formate, resultFormate));
        }
        return fetureDaysList;
    }

    /**
     * 获取某个时间过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past, String time, ThreadLocal<SimpleDateFormat> formate, ThreadLocal<SimpleDateFormat> resultFormate) {
        try {
            Date parse = formate.get().parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
            Date today = calendar.getTime();
            return formate.get().format(today);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past, String time, ThreadLocal<SimpleDateFormat> formate, ThreadLocal<SimpleDateFormat> resultFormate) {
        try {
            Date parse = formate.get().parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
            Date today = calendar.getTime();
            return resultFormate.get().format(today);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 根据传入时间获取某个时间对应的月份最后一天 日期样式自己确定
     */
    public static String getLastDaysOfMonth(String time, ThreadLocal<SimpleDateFormat> formate, ThreadLocal<SimpleDateFormat> resultFormate) {
        Date date = null;
        try {
            date = formate.get().parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // 设定当前时间为每月一号
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            // 当前日历的天数上-1变成最大值 , 此方法不会改变指定字段之外的字段
            calendar.roll(Calendar.DAY_OF_MONTH, -1);
            return resultFormate.get().format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getXingQi(String time, ThreadLocal<SimpleDateFormat> formate) {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六"};
        try {
            Date parse = formate.get().parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (dayOfWeek < 0) {
                dayOfWeek = 0;
            }
            return dayNames[dayOfWeek];
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDate2Bigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = YYYYMMDD.get();
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd HH:mm:ss
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDate2BiggerHhMmSS(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = YYYYMMDDHHMMSS.get();
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }

    /**
     * 获取的是yyyy-MM-dd格式的，获取给定日期当前周的第一天和最后一天的日期
     */
    public static String[] getFirstAndLastOfWeek(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int d;
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        String data1 = YYYYMMDD.get().format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        // 所在周结束日期
        String data2 = YYYYMMDD.get().format(cal.getTime());
        return new String[]{data1, data2};
    }

    /**
     * 获取当前时间与传入时间间隔（秒）
     *
     * @param viewStartTime long 开始时间
     * @return int 秒
     */
    public static int getIntervalTimeByNow(long viewStartTime) {
        return (int) ((System.currentTimeMillis() - viewStartTime) / 1000);
    }

    /**
     * 获取当前时间与传入时间间隔（秒）
     *
     * @param startTime String 开始时间
     * @return int 秒
     */
    public static int getIntervalTimeByNow(String startTime) {
        long timeMillion = 0L;
        try {
            timeMillion = YYYYMMDDHHMMSS.get().parse(startTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (timeMillion == 0L) {
            return 0;
        }
        return (int) ((System.currentTimeMillis() - timeMillion) / 1000);
    }

    /**
     * 作业时间的默认
     *
     * @return
     */
    public static String getPublishHomeWorkTime() {
        return getPublishHomeWorkDay() + " 22:00";
    }


    /**
     * 返回适合的作业日期
     *
     * @return
     */
    public static String getPublishHomeWorkDay() {
        //yyyy-MM-dd HH:mm:ss
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DATE);
        if (hour >= 18) {
            calendar.set(Calendar.DATE, day + 1);
            String dayAfter = YYYYMMDD.get().format(calendar.getTime());
            return dayAfter;
        } else {
            return String.format(YYYYMMDD.get().format(calendar.getTime()));
        }
    }

    /**
     * 返回 一天后的日期
     *
     * @return
     */
    public static String getNextDay() {
        //yyyy-MM-dd HH:mm:ss
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String dayAfter = YYYYMMDD.get().format(calendar.getTime());
        return dayAfter;
    }

    /**
     * 判断是传入的时间是否比当前的时间早
     *
     * @param time
     * @return
     */
    public static boolean isAfterNow(String time) {
        Date date = null;
        Date currentDate = new Date();
        SimpleDateFormat format = YYYYmmDDHHMM.get();
        if (!StringUtils.isEmpty(time)) {
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return date.after(currentDate);
    }

    /**
     * 当前的时间和传入的时间相等
     *
     * @param time
     * @return
     */
    public static boolean isCureentTime(String time) {
        Date date = null;
        Date currentDate = new Date();
        SimpleDateFormat format = YYYYmmDDHHMM.get();
        if (!StringUtils.isEmpty(time)) {
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date.equals(currentDate);
    }

    /**
     * 是否是当前时间之后的
     *
     * @param time
     * @return
     */
    public static boolean isAferCurrentTime(String time) {
        Date date = null;
        Date currentDate = new Date();
        SimpleDateFormat format = YYYYmmDDHHMM.get();
        if (!StringUtils.isEmpty(time)) {
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date.after(currentDate);
    }


    /**
     * 传入的时间和三个小时后的进行比较
     *
     * @param time
     * @return
     */
    public static boolean isCurrentThreeHourAfter(String time) {
        SimpleDateFormat format = YYYYmmDDHHMM.get();
        Date date = null;
        Date threeAfter = null;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);
        String c = format.format(calendar.getTime());
        if (!StringUtils.isEmpty(time)) {
            try {
                date = format.parse(time);
                threeAfter = format.parse(c);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date.after(threeAfter);
    }

    /**
     * 判断输入日期是30天之后的
     */
    public static boolean isCurrentThirtyDayAfter(String time) {
        SimpleDateFormat format = YYYYmmDDHHMM.get();
        Date date = null;
        Date threeAfter = null;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        String c = format.format(calendar.getTime());
        if (!StringUtils.isEmpty(time)) {
            try {
                date = format.parse(time);
                threeAfter = format.parse(c);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date.after(threeAfter);
    }


    /**
     * 判断输入日期是1天之后的
     */
    public static boolean isCurrentNextDayAfter(String time) {
        SimpleDateFormat format = YYYYmmDDHHMM.get();
        Date date = null;
        Date threeAfter = null;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        String c = format.format(calendar.getTime());
        if (!StringUtils.isEmpty(time)) {
            try {
                date = format.parse(time);
                threeAfter = format.parse(c);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date.after(threeAfter);
    }


    /**
     * 已特定的格式格式化时间戳
     */
    public static String formatTimeStemp(long timeStemp, SimpleDateFormat format) {
        Date date = new Date(timeStemp);
        return format.format(date);
    }


    //    、、判断两个日期相差的天数
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }


    /**
     * @param time 秒 转换成分钟
     * @return
     */
    public static int dealWithStudyTimeToMinute(String time) {
        try {
            int minute = 0;
            int fromTime = Integer.parseInt(time);
            if (fromTime <= 0) {
                return 0;
            } else {
                double d = fromTime * 1.0 / 60;
                Logger.e("d==" + d);
                minute = (int) Math.ceil(d);
                if (minute <= 0) {
                    return 0;
                } else {
                    return minute;
                }
            }
        } catch (Exception ex) {
            Logger.e(ex.toString());
        }
        return 0;
    }


    /**
     * 分钟转换成小时
     *
     * @param time
     * @return
     */
    public static String dealWithStudyTimeMinuteToHour(String time) {
        try {
            int min = Integer.parseInt(time);
            int hour = 0;
            if (min < 60) {
                return min + "";
            } else {
                hour = min / 60;
                min = min - hour * 60;
                if (min == 0) {
                    return hour + "h";
                }
                return hour + "h" + min;
            }
        } catch (Exception ex) {
            Logger.e(ex.toString());
        }
        return "";
    }

    /**
     * 将指定格式的时间 2019-05-30，转换成：今天、昨天、或者原时间返回；
     */
    public static String getNearestDay(String timeFormat, String type) {

        if (timeFormat == null || timeFormat.equals("")) return "";

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        try {
            Date parse = simpleDateFormat.parse(timeFormat);
            long formatTime = parse.getTime() / 1000;
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            int tmp = (int) (currentTimeMillis - formatTime);//秒

            if (tmp < 3600 * 24) {
                return "今天";
            } else if (tmp < 3600 * 24 * 2) {
                return "昨天";
            } else {
                return timeFormat;
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeFormat;
    }


    /**
     * 将字符串时间格式为 yyyy-mm-dd  hh:mm:ss 转化成1970年时间
     */
    public static long getTimestamp(String timeFormat) {

        if (timeFormat == null || "".equals(timeFormat)) return 0;
        if (timeFormat.contains(".")) {
            String[] split = timeFormat.split("\\.");
            timeFormat = split[0];
        }

        Timestamp timestamp = Timestamp.valueOf(timeFormat);
        return timestamp.getTime();
    }

    /**
     * 将时间格式："2019-03-16 15:29"  转换成long型的；
     */
    public static long stringToLong(String timeFormat, String type) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        try {
            Date parse = simpleDateFormat.parse(timeFormat);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 改变时间戳样式；
     * 将时间yyyy-MM-DD HH:mm:ss 转换成 “年月日”样式；
     */
    public static String changeTimeStempStyle(String time, String style) {

        if (TextUtils.isEmpty(time)) return "";
        if (TextUtils.isEmpty(style)) return time;

        if (time.contains(" ")) {
            //时间戳是：yyyy-MM-DD HH:mm:ss
            String[] split = time.split(" ");
            if (split[0].contains(style)) {
                String[] splite2 = split[0].split(style);
                if (splite2.length == 3) {
                    return splite2[0] + "年" + splite2[1] + "月" + splite2[2] + "日";
                }
            }
            return time;
        } else {

            if (time.contains(style)) {
                String[] splite2 = time.split(style);
                if (splite2.length == 3) {
                    return splite2[0] + "年" + splite2[1] + "月" + splite2[2] + "日";
                }
            }
            return time;
        }
    }

}
