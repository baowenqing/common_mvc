package com.juziwl.commonlibrary.rx;

import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.model.ResponseData;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 适用于操作符：rx flatMap
 * <p>
 * 流式处理 ：一般用于 接口  连续调用时候 使用【或者 处理数据等】
 *
 * @param <T> 流式处理
 * @param <R> 返回流
 */
public abstract class HandlerFlowableFunc<T, R> implements Function<ResponseData<T>, Flowable<ResponseData<R>>> {

    @Override
    public Flowable<ResponseData<R>> apply(@NonNull ResponseData<T> tResponseData) throws Exception {
        if (Global.STATUS_SUCCESS == tResponseData.success) {
            return onSuccess(tResponseData.data);
        } else {
            throw new HttpException(tResponseData.message, tResponseData.code);
        }
    }

    public abstract Flowable<ResponseData<R>> onSuccess(T t);
}