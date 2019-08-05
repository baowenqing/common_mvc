package com.juziwl.commonlibrary.rx;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.utils.DialogManager;
import com.juziwl.commonlibrary.utils.NetworkUtils;
import com.juziwl.commonlibrary.utils.StringUtils;
import com.juziwl.commonlibrary.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public abstract class ThrowableConsumer implements Consumer<Throwable> {


    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        DialogManager.getInstance().cancelDialog();
        if (!NetworkUtils.isNetworkAvailable(Global.application)) {
            ToastUtils.showToast(R.string.common_useless_network);
            return;
        }
        if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException) {
            ToastUtils.showToast(R.string.common_network_weak);
            return;
        }
        if (!dealWithYouSelf()) {
            //处理抛出的异常
            dealWithError(throwable);
        }
        onError(throwable);
    }

    /**
     * 默认处理
     *
     * @param throwable
     */
    private void dealWithError(Throwable throwable) {
        if (throwable != null && !StringUtils.isEmpty(throwable.getMessage())) {
            if (throwable.getMessage().length() > NetworkSubscriber.ERROR_MSG_MAX_LENGTH) {
                ToastUtils.showToast(R.string.common_fail_to_request);
            } else {
                ToastUtils.showToast(throwable.getMessage());
            }
        }
    }

    protected boolean dealWithYouSelf() {
        return false;
    }

    public abstract void onError(Throwable throwable);
}
