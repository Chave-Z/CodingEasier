package com.coding.easier.translate;

import com.coding.easier.util.NoticeUtil;
import com.coding.easier.constant.TranslateConstant;
import com.coding.easier.util.StringUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author D丶Cheng
 * @description: 谷歌翻译并选择替换内容
 */
public class GoogleReplaceAction extends AbstractTranslateAction {

    @Override
    protected void doTranslate(String selectText) {
        Matcher m = p.matcher(selectText.trim());
        String translateType = m.find() ? TranslateConstant.ZH_CN_TO_EN : TranslateConstant.EN_TO_ZH_CN;
        try {
            GoogleTranslateResult googleTranslateResult = translate(selectText, translateType, editor);
            if (googleTranslateResult == null) {
                NoticeUtil.error("翻译错误,请重试!");
                return;
            }
            showPopupBalloon(googleTranslateResult, translateType);
//            Logger.info(translateType, googleTranslateResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void showPopupBalloon(GoogleTranslateResult result, String translateType) {
        ApplicationManager.getApplication().invokeLater((Runnable) new Runnable() {
            @Override
            public void run() {
                final JBPopupFactory factory = JBPopupFactory.getInstance();
                List<String> list = new ArrayList<>();
                String text;
                if (result.getDict() == null) {
                    text = result.getSentences().get(0).getTrans();
                    addWord(list, text, translateType);
                } else {
                    for (GoogleTranslateResult.DictBean dictBean : result.getDict()) {
                        for (GoogleTranslateResult.DictBean.EntryBean entryBean : dictBean.getEntry()) {
                            text = entryBean.getWord();
                            addWord(list, text, translateType);
                        }
                    }
                }
                JList jList = new JList(list.toArray());
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(jList, BorderLayout.CENTER);
                factory.createComponentPopupBuilder(panel, jList).createPopup().show(factory.guessBestPopupLocation(editor));
                jList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            System.out.println(jList.getSelectedValue());
                            final SelectionModel selectionModel = editor.getSelectionModel();
                            replaceStr(project, editor, selectionModel, jList.getSelectedValue().toString().trim());
                        }
                    }
                });
            }
        });
    }

    /**
     * 将可能需要的单词添加到候选list中
     *
     * @param list
     * @param text
     * @param translateType
     */
    private void addWord(List<String> list, String text, String translateType) {
        if (TranslateConstant.ZH_CN_TO_EN.equals(translateType) && text.contains(" ")) {
            list.add("  " + text + "    ");
            list.add("  " + StringUtil.toConstant(text) + "    ");
            text = text.trim().toLowerCase();
            list.add("  " + StringUtil.blankToUnderscoreCase(text) + "    ");
            list.add("  " + StringUtil.blankToCamelCase(text) + "    ");
        } else {
            list.add("  " + text + "    ");
        }
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
