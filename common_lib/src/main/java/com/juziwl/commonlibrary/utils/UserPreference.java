package com.juziwl.commonlibrary.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.juziwl.commonlibrary.datacenter.AllDataCenterManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;


/**
 * @author 文庆
 * @description 存储用户信息
 */
public class UserPreference {
    private SharedPreferences settings;


    public UserPreference() {
    }


    //注意打开checkNull
    private void checkNull() {
        if (settings == null) {
            Activity topActivity = AppManager.getInstance().getTopActivity();
            settings = topActivity.getSharedPreferences("user_info", 0);
        }
    }
    /*--------------------------------------设置---------------------------------------------*/


    public String getUid() {
        checkNull();
        return settings.getString("uid", "");
    }

    /**
     * 设置 userid
     */
    public void setUid(String uid) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("uid", uid);
        editor.apply();
    }

    public String getAvatar() {
        checkNull();
        return settings.getString("avatar", "");
    }

    /**
     * 设置用户头像;
     */
    public void setAvatar(String avatar) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("avatar", avatar);
        editor.apply();
    }

    public String getUserName() {
        checkNull();
        return settings.getString("userName", "");
    }

    /**
     * 设置用户姓名；
     */
    public void setUserName(String userName) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("userName", userName);
        editor.apply();
    }


    public String getToken() {
        checkNull();
        return settings.getString("token", "");
    }

    /**
     * 设置 登录账号
     */
    public void setToken(String token) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token);
        editor.commit();
    }


    public String getLoginAccount() {
        checkNull();
        return settings.getString("LoginAccount", "");
    }

    /**
     * 设置 登录账号
     */
    public void setLoginAccount(String account) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("LoginAccount", account);
        editor.commit();
    }

    public String getPassword() {
        checkNull();
        return settings.getString("PassWord", "");
    }

    /**
     * 设置 登录账号
     */
    public void setPassword(String password) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("PassWord", password);
        editor.commit();
    }


    public boolean getAutoLogin() {
        checkNull();
        return settings.getBoolean("autoLogin", false);
    }

    /**
     * 设置 是否自动登录
     */
    public void setAutoLogin(boolean autoLogin) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("autoLogin", autoLogin);
        editor.apply();
    }


    public List<String> getSearchHistory() {
        checkNull();
        List<String> list = new ArrayList<>();
        String tag = "#<%!&>#";
        String str = settings.getString("searchHistory", "");
        if (!TextUtils.isEmpty(str)) {
            assert str != null;
            if (str.contains(tag)) {
                String[] arr = str.split(tag);
                list.addAll(Arrays.asList(arr));
            } else {
                list.add(str);
            }
        }
        //Collections.reverse(list);
        return list;
    }

    /**
     * 设置 历史记录list
     */
    public void setSearchHistory(List<String> list) {
        String tag = "#<%!&>#";
        StringBuilder sb = new StringBuilder();
        if (ListUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    sb.append(list.get(i)).append(tag);
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("searchHistory", sb.toString());
        editor.apply();
    }

}
