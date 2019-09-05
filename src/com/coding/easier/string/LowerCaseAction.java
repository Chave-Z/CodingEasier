package com.coding.easier.string;

/**
 * @author D丶Cheng
 * @description 普通字符串转换小写格式
 */
public class LowerCaseAction extends AbstractStringAction {

    @Override
    protected String transformStr(String selectedText) {
        return selectedText.toLowerCase();
    }
}
