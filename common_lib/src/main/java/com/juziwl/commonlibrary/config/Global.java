package com.juziwl.commonlibrary.config;

import android.app.Application;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.IntDef;

import com.juziwl.commonlibrary.BuildConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ztn on
 * 全局数据存放处 广播action 消息 url地址等等
 *
 * @author Army
 */
public class Global {

    public static Application application;


    public static String TOKEN_TAG = "X-Auth-Token";
    public static String loginActivity = "";

    public static String Tag_Service_Error = "服务端返回数据异常";


    /**
     * ----------------------------------------------------------------
     * 正式库和测试库的切换记得修改sdklibrary里QQ的manifest的配置
     * ----------------------------------------------------------------
     */
    public static final String ENCODING = "UTF-8";
    public static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yiHuApp/";


    static boolean isTest = true;
    //修改为 通过是否debug模式 切换环境
//    static boolean isTest = BuildConfig.DEBUG;

    //base地址
    public static String BASE_URL = isTest ? "http://172.17.10.7/" : "http://study.sdusz.com/";
    //public static String BASE_URL = isTest ? "http://172.17.10.33/" : "http://study.sdusz.com/";

    //登录地址
    public static String BASE_LOGIN_URL = isTest ? "http://172.17.10.7/" : "http://user.sdusz.com/";
    //public static String BASE_LOGIN_URL = isTest ? "http://172.17.10.33/" : "http://user.sdusz.com/";

    //文件地址
    public static final String BASE_URL_DOWNLOAD = isTest ? BASE_URL + "download/" : "http://study-file.sdusz.com/";

    //m3u8视频播放地址
    public static final String BASE_URL_VIDEO = isTest ? BASE_URL + "hls/" : "http://hls.sdusz.com/study-hls/";

    /**
     * 语音存放路径
     */
    public static String VOICEPATH;

    /**
     * 视频存放路径
     */
    public static String VIDEOPATH;

    /**
     * 图片保存路径
     */
    public static String SAVEPICTURE;

    /**
     * 选图片缓存和glide缓存的图片路径
     */
    public static String imgPath = filePath + "imgCache/";

    /**
     * 文件的存放地址
     */
    public static String SAVEFILEPATH = filePath + "file/";


    public static final int STUDENT = 0;
    public static final int PARENT = 1;
    public static final int TEACHER = 2;


    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STUDENT, PARENT, TEACHER})
    public @interface LoginType {
    }

    @LoginType
    public static int loginType = PARENT;

    public static final String PAR_APKFILENAME = "exuecloud_par.apk";
    public static final String TEA_APKFILENAME = "exuecloud_tea.apk";


    /*--------------------------------  传值 参数相关的  ----------------------------------*/
    public static final String ACTION_CONTENT = "action_content";
    public static final String ACTION_MESSAGE = "action_message";
    public static final String ACTION_TAG = "action_tag";
    public static final String ACTION_MSG = "action_msg";
    public static final String ACTION_OTHER = "action_other";


    /*--------------------------------  图片 参数相关的  ----------------------------------*/
    public static final int P720 = 720;
    public static final int P1080 = 1080;
    public static final int P1500 = 1500;

    /*--------------------------------  http 参数相关的  ----------------------------------*/
    public static boolean STATUS_SUCCESS = true;

    /*--------------------------------资源类型 参数相关的  ----------------------------------*/
    public static final String RESOURCE_IMG = "01";
    public static final String RESOURCE_DOCUMENT = "02";
    public static final String RESOURCE_MUSIC = "03";
    public static final String RESOURCE_VIDEO = "04";
    public static final String RESOURCE_ZIP = "05";
    public static final String RESOURCE_OTHER = "06";


}
