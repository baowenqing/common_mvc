package com.juziwl.commonlibrary.rx;

import com.juziwl.commonlibrary.utils.DialogManager;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 观察者回调
 * 最后接口成功后的处理
 */

public abstract class SuccessConsumer<T> implements Consumer<T> {

    public boolean dealWithYouSelf = false;

    @Override
    public void accept(@NonNull T result) throws Exception {
        if (needCancelDialog()) {
            DialogManager.getInstance().cancelDialog();
        }
        onSuccess(result);
    }

    public abstract void onSuccess(T result);

    public boolean needCancelDialog() {
        return true;
    }
}
