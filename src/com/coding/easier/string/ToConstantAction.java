package com.coding.easier.string;

import com.coding.easier.util.StringUtil;

/**
 * @author D丶Cheng
 * @description 普通字符串转换常量
 */
public class ToConstantAction extends AbstractStringAction {

    @Override
    protected String transformStr(String selectedText) {
        return StringUtil.toConstant(selectedText);
    }
}
