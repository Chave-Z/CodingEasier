package com.coding.easier.ui.modules;

import com.intellij.util.ui.UIUtil;

/**
 * @author: D丶Cheng
 * @description:
 * @create: 2019-09-10 15:39
 **/
public class IdeaColorService extends ColorService{
    @Override
    protected <T> T internalForCurrentTheme(T[] objects) {
        if (objects == null) {
            return null;
        } else if (UIUtil.isUnderDarcula() && objects.length > 1) {
            return objects[1];
        } else {
            return objects[0];
        }
    }
}
