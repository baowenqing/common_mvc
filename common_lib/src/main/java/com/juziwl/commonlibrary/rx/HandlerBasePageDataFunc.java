package com.juziwl.commonlibrary.rx;

import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.model.BasePageBean;
import com.juziwl.commonlibrary.model.ResponseData;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 适用于：rx map操作符
 *
 * @author 文庆
 * @description rx处理 分页数据 脱壳处理
 */
public abstract class HandlerBasePageDataFunc<T, R> implements Function<ResponseData<BasePageBean<T>>, R> {

    @Override
    public R apply(@NonNull ResponseData<BasePageBean<T>> tResponseData) throws Exception {
        if (Global.STATUS_SUCCESS == tResponseData.success) {
            if (tResponseData.data != null && tResponseData.data.records != null) {
                return ListMapApply(tResponseData.data.records);
            } else {
                //数据获取异常的情况
                throw new HttpException(tResponseData.message,tResponseData.code);
            }
        } else {
            throw new HttpException(tResponseData.message,tResponseData.code);
        }
    }

    public abstract R ListMapApply(List<T> t) throws Exception;
}
