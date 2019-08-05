package com.juziwl.commonlibrary.rx;


import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.model.ResponseData;
import com.juziwl.commonlibrary.utils.CommonTools;
import com.juziwl.commonlibrary.utils.DialogManager;
import com.juziwl.commonlibrary.utils.NetworkUtils;
import com.juziwl.commonlibrary.utils.StringUtils;
import com.juziwl.commonlibrary.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;


/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/4/30
 * @description 网络请求基本订阅者
 */
public abstract class NetworkSubscriber<T> implements Subscriber<ResponseData<T>> {
    public static final int ERROR_MSG_MAX_LENGTH = 30;
    String WRONG_MESSAGE_NETOUT = "似乎已经断开网络连接";
    String WRONG_MESSAGE_SYSTEM = "系统错误，请重试";

    public NetworkSubscriber() {
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onComplete() {
        DialogManager.getInstance().cancelDialog();
    }

    @Override
    public void onError(Throwable e) {
        try {
            DialogManager.getInstance().cancelDialog();
            CommonTools.outputError(e);
            if (!NetworkUtils.isNetworkAvailable(Global.application)) {
                if (!dealHttpException(false, "", WRONG_MESSAGE_NETOUT, e)) {
                    ToastUtils.showToast(WRONG_MESSAGE_NETOUT);
                }
            } else {
                String errorMsg;
                String code;
                boolean success;
                if (e instanceof HttpException) {
                    success = ((HttpException) e).getIsSuccess();
                    code = ((HttpException) e).getCode();
                    errorMsg = e.getMessage();
                } else {
                    success = false;
                    code = "";
                    errorMsg = "";
                }
                if (!dealHttpException(success, code, errorMsg, e)) {

                    //判断是否是需要拦截的code
                    if ("CODE_INTERCEPTOR".equals(code)) return;

                    if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
                        ToastUtils.showToast(WRONG_MESSAGE_NETOUT);

                    } else if (e != null && !android.text.TextUtils.isEmpty(e.getMessage()) && e.getMessage().length() <= ERROR_MSG_MAX_LENGTH) {
                        ToastUtils.showToast(e.getMessage());

                    } else if (e instanceof RuntimeException) {
                        Logger.e(CommonTools.outputError(e));
                        ToastUtils.showToast(WRONG_MESSAGE_SYSTEM);

                    } else {
                        ToastUtils.showToast(WRONG_MESSAGE_SYSTEM);
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onNext(ResponseData<T> tResponseData) {
        try {
            if (Global.STATUS_SUCCESS == tResponseData.success) {
                onSuccess(tResponseData.data);
            } else {
                DialogManager.getInstance().cancelDialog();
                if (!dealHttpException(tResponseData.success, tResponseData.code, tResponseData.message, null)) {

                    //判断是否是需要拦截的code
                    if ("CODE_INTERCEPTOR".equals(tResponseData.code)) return;

                    if (StringUtils.isEmpty(tResponseData.message) || tResponseData.message.length() > ERROR_MSG_MAX_LENGTH) {
                        ToastUtils.showToast(WRONG_MESSAGE_NETOUT);
                    } else {
                        ToastUtils.showToast(tResponseData.message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            onError(e);
        }
    }

    protected abstract void onSuccess(T t);


    protected boolean dealHttpException(boolean isSuccess, String code, String errorMsg, Throwable e) {
        return false;
    }
}
