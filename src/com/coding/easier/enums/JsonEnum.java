package com.coding.easier.enums;

/**
 * @author D丶Cheng
 * @description： 格式化类型枚举
 * @create: 2019-09-06 16:10
 */
public enum JsonEnum {

    /**
     * format
     */
    FORMAT("format"),
    /**
     * 压缩
     */
    COMPRESS("compress"),
    /**
     * 转义
     */
    ESCAPE("escape"),
    /**
     * 去转义
     */
    UNESCAPE("unescape");

    JsonEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
