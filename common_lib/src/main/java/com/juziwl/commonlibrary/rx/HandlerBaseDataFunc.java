package com.juziwl.commonlibrary.rx;

import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.model.ResponseData;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 适用于：rx map操作符
 *
 * @author 文庆
 * @description rx处理 基础数据 脱壳处理
 */
public abstract  class HandlerBaseDataFunc<T, R> implements Function<ResponseData<T>, R> {


    @Override
    public R apply(@NonNull ResponseData<T> tResponseData) throws Exception {
        //response中code码不为200 出现错误
        if (Global.STATUS_SUCCESS == (tResponseData.success)) {
            return onSuccess(tResponseData.data);
        } else {
            throw new HttpException(tResponseData.message,tResponseData.code);
        }
    }

    public abstract R onSuccess(T t);
}
