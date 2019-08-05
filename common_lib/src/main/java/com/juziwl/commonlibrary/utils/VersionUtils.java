package com.juziwl.commonlibrary.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.Locale;

import okhttp3.OkHttpClient;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/11/22
 * @description 版本相关的工具类
 */
public class VersionUtils {

    private static final int DOWNLOAD_NOTIFICATION_ID = 666;
    public static boolean isDownload = false;

    /**
     * 获取当前的版本号
     */
    public static String getVersionName() {
        PackageManager packageManager = Global.application.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(Global.application.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前本地apk的版本
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 比较两个版本号，版本号不能写成1.2.42这样的，要写成1.2.4.2
     *
     * @return 0 相等，1 version1 > version2，-1 version1 < version2
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        //获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        //循环判断每位的大小
        while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            //如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }
            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    public static void download(String title, String url) {
        if (isDownload) {
            ToastUtils.showToast(R.string.common_download_tips);
            return;
        }
        Context context = Global.application;
        NotificationManager mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotifyManager == null) {
            return;
        }
        final String DOWNLOAD_NOTIFICATION_CHANNAL_ID = "download_notification_channel_id";
        final String DOWNLOAD_NOTIFICATION_CHANNAL_NAME = "download_notification_channel_name";

        NotificationUtils.setChannel(mNotifyManager, DOWNLOAD_NOTIFICATION_CHANNAL_ID,
                DOWNLOAD_NOTIFICATION_CHANNAL_NAME);
        NotificationCompat.Builder mBuilder;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            mBuilder = new NotificationCompat.Builder(Global.application, DOWNLOAD_NOTIFICATION_CHANNAL_ID);
        } else {
            mBuilder = new NotificationCompat.Builder(Global.application);
        }
        mBuilder.setContentTitle(title)
                .setContentText("已下载0%")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setProgress(100, 0, false)
                .setSmallIcon(CommonTools.getResourceId("main_icon", "mipmap"));
        Notification notify = mBuilder.build();
        notify.flags |= Notification.FLAG_NO_CLEAR;
        mNotifyManager.notify(DOWNLOAD_NOTIFICATION_ID, notify);

        File apk = new File(Global.filePath, Global.loginType == Global.TEACHER ? Global.TEA_APKFILENAME : Global.PAR_APKFILENAME);
        if (!apk.getParentFile().exists()) {
            apk.getParentFile().mkdirs();
        }
        if (apk.exists()) {
            apk.delete();
        }
        OkGo.<File>get(url)
                .client(new OkHttpClient.Builder().build())
                .execute(new FileCallback(apk.getParent(), apk.getName()) {

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        ToastUtils.showToast(R.string.common_download_tips);
                        isDownload = true;
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        mNotifyManager.cancel(DOWNLOAD_NOTIFICATION_ID);
                        isDownload = false;
                        AppManager.getInstance().killAllActivity();
                        FileUtils.installApk(context, response.body());
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        int percent = (int) (progress.fraction * 100);
                        mBuilder.setProgress(100, percent, false)
                                .setContentText(String.format(Locale.getDefault(), "已下载%d%%", percent));
                        mNotifyManager.notify(DOWNLOAD_NOTIFICATION_ID, mBuilder.build());
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        isDownload = false;
                        mNotifyManager.cancel(DOWNLOAD_NOTIFICATION_ID);
                        ToastUtils.showToast("新版本下载失败，请检查网络");
                        try {
                            AppManager.getInstance().killAllActivity();
                            Intent intents = new Intent(context.getApplicationContext(), Class.forName(Global.loginActivity));
                            if (intents.resolveActivity(context.getPackageManager()) != null) {
                                intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.getApplicationContext().startActivity(intents);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        mNotifyManager.cancel(DOWNLOAD_NOTIFICATION_ID);
                        isDownload = false;
                    }
                });
    }
}
