package com.coding.easier.ui;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import java.util.List;


/**
 * @author D丶Cheng
 * @Description:
 * @date 2020/12/29 12:18 下午
 */
public class Popups extends BaseListPopupStep {

    public Popups(@Nullable @NlsContexts.PopupTitle String title, List values) {
        super(title, values);
    }

    //    public Popups(@Nullable @NlsContexts.PopupTitle String title, Object[] values, Icon[] icons) {
//        super(title, values, icons);
//    }
//
//    public Popups(@Nullable @NlsContexts.PopupTitle String title, @NotNull List aValues, Icon aSameIcon) {
//        super(title, aValues, aSameIcon);
//    }
//
//    @NotNull
//    @Override
//    public String getTextFor(Object value) {
//        return super.getTextFor(value);
//    }
//
//    @Nullable
//    @Override
//    public PopupStep onChosen(Object selectedValue, boolean finalChoice) {
//        return super.onChosen(selectedValue, finalChoice);
//    }


    @Override
    public boolean isSpeedSearchEnabled() {
        return true;
    }
}
