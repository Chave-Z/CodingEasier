package com.coding.easier.string;

import com.coding.easier.util.StringUtil;

/**
 * @author D丶Cheng
 * @description 普通字符串转换短横线字体
 */
public class KebabCaseAction extends AbstractStringAction {

    @Override
    protected String transformStr(String selectedText) {
        return StringUtil.textToKebabCase(selectedText, false);
    }
}
