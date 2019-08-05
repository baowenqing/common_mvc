package com.juziwl.commonlibrary.datacenter;

import com.juziwl.commonlibrary.utils.UserPreference;

/**
 * @author 文庆
 * @description 数据中心：管理app内所有数据
 */
public class AllDataCenterManager {
    private final String DATA_BASE_NAME = "yihu_app.db";

    /**
     * 用户数据
     */
    public UserPreference userPreference;

    private static class SingletonHolder {
        private static AllDataCenterManager instance = new AllDataCenterManager();
    }

    public static AllDataCenterManager getInstance() {
        return SingletonHolder.instance;
    }

    public UserPreference getUserPreference() {
        if (userPreference == null) {
            userPreference = new UserPreference();
        }
        return userPreference;
    }


}
