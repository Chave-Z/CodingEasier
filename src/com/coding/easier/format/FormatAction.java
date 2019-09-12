package com.coding.easier.format;

import com.coding.easier.ui.FormatDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author: D丶Cheng
 * @description:
 * @create: 2019-08-29 15:46
 **/
public class FormatAction extends AnAction {

    public static final Pattern p = compile("[\u4e00-\u9fa5]");

    @Override
    public void actionPerformed(AnActionEvent e) {
//        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        showDialog(editor);
    }

    private void showDialog(Editor editor) {
        final FormatDialog dialog = new FormatDialog(this, editor);
        dialog.setSize(880, 515);
        dialog.setTitle("CodingEasier Format");
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
