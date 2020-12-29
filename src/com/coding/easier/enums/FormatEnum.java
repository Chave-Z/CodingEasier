package com.coding.easier.enums;

/**
 * @author D丶Cheng
 * @description： 格式化类型枚举
 * @create: 2019-09-06 16:10
 */
public enum FormatEnum {

    /**
     * json
     */
    JSON("json"),

    /**
     * xml
     */
    XML("xml");

    FormatEnum(String value) {
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
