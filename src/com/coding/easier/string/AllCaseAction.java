package com.coding.easier.string;

import com.coding.easier.util.NoticeUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.coding.easier.util.StringUtil.*;
import static java.util.regex.Pattern.compile;

/**
 * @author D丶Cheng
 * @description 候选所有字符格式
 */
public class AllCaseAction extends AnAction {

    public static final Pattern p = compile("[\u4e00-\u9fa5]");

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        SelectionModel selectionModel = editor.getSelectionModel();
        String selectedText = selectionModel.getSelectedText();
        if (StringUtils.isBlank(selectedText)) {
            NoticeUtil.error("请选择要转换的字符");
            return;
        }
        Matcher m = p.matcher(selectedText.trim());
        if (m.find()) {
            NoticeUtil.error("所选内容不能带有中文");
            return;
        }
        showPopupBalloon(project, editor, selectedText);
    }

    @Override
    public void update(AnActionEvent e) {
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null
                && editor.getSelectionModel().hasSelection());
    }


    /**
     * 气泡显示
     *
     * @param project
     * @param editor
     * @param selectedText
     */
    protected void showPopupBalloon(Project project, Editor editor, String selectedText) {
        ApplicationManager.getApplication().invokeLater((Runnable) new Runnable() {
            @Override
            public void run() {
                final JBPopupFactory factory = JBPopupFactory.getInstance();
                LinkedHashSet<String> set = getAllCase(selectedText);
                JList jList = new JList(set.toArray());
                JScrollPane scrollPane = new JScrollPane(jList);
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(scrollPane, BorderLayout.CENTER);
                factory.createComponentPopupBuilder(panel, jList).createPopup().show(factory.guessBestPopupLocation(editor));
                jList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            System.out.println(jList.getSelectedValue());
                            final SelectionModel selectionModel = editor.getSelectionModel();
                            replaceStr(project, editor, selectionModel, jList.getSelectedValue().toString());
                        }
                    }
                });
            }
        });
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


}
