package com.juziwl.commonlibrary.rx;

public class HttpException extends Exception {

    private boolean success = false;
    private String code = "";

    public HttpException(String message, String code) {
        super(message);
        this.code = code;
    }

    public HttpException(boolean success, String message, String code) {
        super(message);
        this.success = success;
        this.code = code;
    }

    public boolean getIsSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }
}
