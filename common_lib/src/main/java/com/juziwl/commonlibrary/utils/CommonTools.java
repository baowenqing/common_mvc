package com.juziwl.commonlibrary.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.juziwl.commonlibrary.config.Global;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月18日
 * @description 应用程序的公共工具类
 */
public class CommonTools {

    private static boolean isActive=true;


    public static void startActivity(Context context, Class classes) {
        startActivity(context, classes, null);
    }

    public static void startActivity(Context context, Class classes, Bundle bundle) {
        Intent intent = new Intent(context, classes);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public static String outputError(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();

        if (cause != null) {
            cause.printStackTrace(printWriter);
        }
        String message = writer.toString();
        Logger.e(message);
        return message;
    }

    public static void setAlarmParams(Notification notification, String audio) {
        // AudioManager provides access to volume and ringer mode control.
        if (!TextUtils.isEmpty(audio)) {
            int a = Integer.parseInt(audio);
            // 获取系统设置的铃声模式
            switch (a) {
                case 0:
                    // 静音模式，值为0，这时候不震动，不响铃
                    notification.sound = null;
                    notification.vibrate = null;
                    notification.defaults = Notification.DEFAULT_LIGHTS;
                    break;
                case 1:
                    // 震动模式，值为1，这时候震动，不响铃
                    notification.sound = null;
                    notification.defaults = Notification.DEFAULT_VIBRATE;
                    break;
                case 2:
                    // 获取软件的设置
                    notification.defaults = Notification.DEFAULT_SOUND;
                    notification.vibrate = null;
                    break;
                case 3:
                    // 声音加振动
                    notification.defaults = Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 显示软键盘
     */
    public static void showInput(EditText et_msg) {
        try {
            et_msg.setFocusable(true);
            et_msg.setFocusableInTouchMode(true);
            et_msg.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) et_msg.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(et_msg, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideInput(EditText et_msg) {
        try {
            InputMethodManager imm = (InputMethodManager) et_msg.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_msg.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *  检查输入法是否显示
     * @param et_msg
     * @return
     */
    public  static boolean checkInputVisible(EditText et_msg){
        InputMethodManager imm = (InputMethodManager) et_msg.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.hideSoftInputFromWindow(et_msg.getWindowToken(), 0)) {
            return true;
        }
        return false;
    }


    public static boolean containInt(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean containString(String[] array, String value) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean arrayContainString(String value, String[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            if (value.contains(arrays[i])) {
                return true;
            }
        }
        return false;
    }

    public static void copy(String content, Context context) {
        try {
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData textCd = ClipData.newPlainText(content, content);
            cmb.setPrimaryClip(textCd);
            ToastUtils.showToast("已复制到剪切板");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备类型
     *
     * @return
     */
    public static String getDeviceType() {
        return "Android," + Build.VERSION.RELEASE + "," +
                Build.MODEL + "," + VersionUtils.getVersionName();
    }

    /**
     * 用浏览器打开
     */
    public static void openUrlWithBrower(Context context, String url) {
        if (StringUtils.isEmpty(url)) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast("无法找到浏览器来打开链接");
        }
    }

    /**
     * 根据名字获取资源的ID
     */
    public static int getResourceId(String resName, String resType) {
        Context context = Global.application;
        return context.getResources().getIdentifier(resName, resType, context.getPackageName());
    }

    /**
     * 是否是主进程
     */
    public static boolean isMainProcess(Context context) {
        return context.getPackageName().equals(getCurrentProcessName(context));
    }

    private static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
                if (process.pid == pid) {
                    processName = process.processName;
                }
            }
        }
        return processName;
    }
    public static boolean isAppForeground() {
        ActivityManager am =
                (ActivityManager) Global.application.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) return false;
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return aInfo.processName.equals(Global.application.getPackageName());
            }
        }
        return false;
    }
    /****************
     *
     * 发起添加群流程。群号：Android接班人(188683852) 的 key 为： BeGM-2AoLqFuLAnCxzfhKUgdi3sr1jgY
     * 调用 joinQQGroup(BeGM-2AoLqFuLAnCxzfhKUgdi3sr1jgY) 即可发起手Q客户端申请加群 Android接班人(188683852)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public static boolean joinQQGroup(Context context, String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            ToastUtils.showToast("请先安装QQ客户端");
            return false;
        }
    }

    /**
     * 跳转到系统的添加联系人界面
     */
    public static void addNewContacts(Context context, String phone) {
        Intent addIntent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
        addIntent.setType("vnd.android.cursor.dir/person");
        addIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        addIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        addIntent.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE, phone);
        if (addIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(addIntent);
        } else {
            ToastUtils.showToast("无法跳转到手机通讯录，请自行添加");
        }
    }

    /**
     * 添加到现有联系人
     */
    public static void addToExistContact(Context context, String phone) {
        Intent oldConstantIntent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
        oldConstantIntent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        oldConstantIntent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        oldConstantIntent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        if (oldConstantIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(oldConstantIntent);
        }
    }

    /**
     * 跳转到拨号界面，同时传递电话号码
     */
    public static void callPhone(Context context, String phone) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        context.startActivity(dialIntent);
    }

    /**
     * 打开文件选择器
     */
    public static final int CHOOSE_FILE_REQUEST = 123;

    public static void openChooser(Activity context) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        context.startActivityForResult(i, CHOOSE_FILE_REQUEST);
    }

    /**
     * 打开当前App的详情界面
     */
    public static void openAppInfo(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static final int TAKE_PHOTOS_REQUEST = 124;

    /**
     * 用系统相机拍照
     */
    public static Uri openSystemCamera(Activity context) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        File image = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), System.currentTimeMillis() + ".jpg");
        FileUtils.createFileByDeleteOldFile(image);
        Uri fileUri = FileUtils.getFileUri(context, image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        context.startActivityForResult(intent, TAKE_PHOTOS_REQUEST);
        return fileUri;
    }

    /**
     * 打开系统相册
     */
    public static void openSystemAlbum(Activity context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        context.startActivityForResult(intent, CHOOSE_FILE_REQUEST);
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(Activity activity,String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;

            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DecimalFormat df6 = new DecimalFormat("000000");
    private static Random rand = new Random();

    /**
     * 获取6位随机码
     */
    public static String getSixRandomNo() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = array.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 6; i++) {
            result = result * 10 + array[i];
        }
        return df6.format(result);
    }



    /**
     * 获取剪贴板的文本
     *
     * @param context 上下文
     * @return 剪贴板的文本
     */
    public static CharSequence getClipBoardText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(context);
        }
        return null;
    }


    /**
     * 复制文本到剪贴板
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void copyText( CharSequence text,Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText("text", text));
    }


    public static void setIsActive(boolean b) {
        isActive=b;
    }

    public static boolean getIsActive() {
        return isActive;
    }
}
