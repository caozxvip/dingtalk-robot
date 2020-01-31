package com.mickey.dingtalk.type;

/**
 * 自定义接口返回类型
 */
public enum ResponseCodeType {

    /**
     * 消息发送成功
     */
    OK(0);

    ResponseCodeType(Integer value) {
        this.value = value;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }
}
