package com.mickey.dingtalk.exception;

/**
 * 自定义报错
 */
public class DingTalkException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer errcode;

    public DingTalkException() {
        super();
    }

    public DingTalkException(Integer errcode, String errmsg) {
        super(errmsg);
        this.errcode = errcode;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return this.getMessage();
    }

}
