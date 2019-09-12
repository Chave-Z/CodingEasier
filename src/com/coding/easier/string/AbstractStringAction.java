package com.coding.easier.string;

import com.coding.easier.util.NoticeUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author: D丶Cheng
 * @description:
 * @create: 2019-08-29 15:46
 **/
public abstract class AbstractStringAction extends AnAction {

    public static final Pattern p = compile("[\u4e00-\u9fa5]");

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        SelectionModel selectionModel = editor.getSelectionModel();
        String selectedText = selectionModel.getSelectedText();
        if (StringUtils.isBlank(selectedText)) {
            NoticeUtil.error("请选择要转换的字符");
        }
        Matcher m = p.matcher(selectedText.trim());
        if (m.find()) {
            NoticeUtil.error("所选内容不能带有中文");
            return;
        }
        String newText = transformStr(selectedText);
        replaceStr(project, editor, selectionModel, newText);
    }

    @Override
    public void update(AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null
                && editor.getSelectionModel().hasSelection());
    }


    /**
     * 替换选中的字符串
     *
     * @param editor
     * @param selectionModel
     * @param project
     * @param newText
     */
    public static void replaceStr(Project project, Editor editor, SelectionModel selectionModel, String newText) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), newText);
            }
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
        selectionModel.removeSelection();
    }

    /**
     * 转化字符串
     *
     * @param selectedText
     * @return
     */
    protected abstract String transformStr(String selectedText);
}
