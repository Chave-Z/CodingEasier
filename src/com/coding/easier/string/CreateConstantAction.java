package com.coding.easier.string;


import com.coding.easier.util.StringUtil;

/**
 * @author D丶Cheng
 * @description 创建一个常量
 */
public class CreateConstantAction extends AbstractStringAction {

    private final static String CONSTANT_MODAL = "public static final String %s = \"%s\";";

    @Override
    protected String transformStr(String selectedText) {
        String constant = StringUtil.toConstant(selectedText);
        return String.format(CONSTANT_MODAL, constant, selectedText);
    }
}
