package com.coding.easier.string;

import com.coding.easier.util.StringUtil;

/**
 * @author D丶Cheng
 * @description 普通字符串转换大驼峰
 */
public class PascalCaseAction extends AbstractStringAction {

    @Override
    protected String transformStr(String selectedText) {
        return StringUtil.textToCamelCase(selectedText, true);
    }
}
