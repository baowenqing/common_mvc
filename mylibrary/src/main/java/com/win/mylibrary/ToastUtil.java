package com.win.mylibrary;

import android.content.Context;
import android.widget.Toast;

/**
 * @author 文庆
 * @date 2019/8/5.
 * @description
 */
public class ToastUtil {
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
