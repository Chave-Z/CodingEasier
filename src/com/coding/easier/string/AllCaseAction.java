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
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.openapi.util.NlsContexts;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.coding.easier.util.StringUtil.getAllCase;
import static java.util.regex.Pattern.compile;

/**
 * @author D丶Cheng
 * @description 候选所有字符格式
 */
public class AllCaseAction extends AnAction {

    public static final Pattern p = compile("[\u4e00-\u9fa5]");

    public static Editor editor;
    public static Project project;
    public static SelectionModel selectionModel;

    @Override
    public void actionPerformed(AnActionEvent e) {
        selectionModel = editor.getSelectionModel();
        String selectedText = selectionModel.getSelectedText();
        if (StringUtils.isEmpty(selectedText)) {
            NoticeUtil.error("请选择要转换的字符");
            return;
        }
        Matcher m = p.matcher(selectedText.trim());
        if (m.find()) {
            NoticeUtil.error("所选内容不能带有中文");
            return;
        }
        showPopupBalloon(selectedText);
    }

    @Override
    public void update(AnActionEvent e) {
        project = e.getData(CommonDataKeys.PROJECT);
        editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null
                && editor.getSelectionModel().hasSelection());
    }


    /**
     * 气泡显示
     *
     * @param selectedText
     */
    protected void showPopupBalloon(String selectedText) {
        ApplicationManager.getApplication().invokeLater(() -> {
            final JBPopupFactory factory = JBPopupFactory.getInstance();
            LinkedHashSet<String> set = getAllCase(selectedText);
            SelectListStep step = new SelectListStep("", new ArrayList(set));
            step.setDefaultOptionIndex(0);
            ListPopup popup = factory.createListPopup(step, 20);
            popup.setRequestFocus(true);
            popup.show(factory.guessBestPopupLocation(editor));
        });
    }

    class SelectListStep extends BaseListPopupStep {

        public SelectListStep(@Nullable @NlsContexts.PopupTitle String title, List values) {
            super(title, values);
        }

        @Nullable
        @Override
        public PopupStep onChosen(Object selectedValue, boolean finalChoice) {
            final SelectionModel selectionModel = editor.getSelectionModel();
            replaceStr(selectionModel, selectedValue.toString());
            System.out.println(selectedValue.toString());
            return super.onChosen(selectedValue, finalChoice);
        }

        @Override
        public boolean isSpeedSearchEnabled() {
            return true;
        }
    }

    /**
     * 替换选中的字符串
     *
     * @param selectionModel
     * @param newText
     */
    public static void replaceStr(SelectionModel selectionModel, String newText) {
        Runnable runnable = () -> editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), newText);
        WriteCommandAction.runWriteCommandAction(project, runnable);
        selectionModel.removeSelection();
    }


}
