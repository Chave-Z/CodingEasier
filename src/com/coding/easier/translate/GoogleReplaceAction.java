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
import java.util.LinkedHashSet;
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
            NoticeUtil.info(translateType, googleTranslateResult.toString());
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
                LinkedHashSet<String> set = new LinkedHashSet<>();
                String text;
                if (result.getDict() == null) {
                    text = result.getSentences().get(0).getTrans();
                    set.add(text);
                } else {
                    for (GoogleTranslateResult.DictBean dictBean : result.getDict()) {
                        for (GoogleTranslateResult.DictBean.EntryBean entryBean : dictBean.getEntry()) {
                            text = entryBean.getWord();
                            set.add(text);
                        }
                    }
                }
                LinkedHashSet<String> candidateWords = new LinkedHashSet<>();
                // 填充结果
                for (String s : set) {
                    addWord(candidateWords, s.trim(), translateType);
                }
                set.addAll(candidateWords);
                JList jList = new JList(set.toArray());
                JScrollPane scrollPane = new JScrollPane(jList);
                scrollPane.setPreferredSize(new Dimension(170, 0));
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
     * 将可能需要的单词添加到候选list中
     *
     * @param set
     * @param text
     * @param translateType
     */
    private void addWord(LinkedHashSet<String> set, String text, String translateType) {
        if (TranslateConstant.ZH_CN_TO_EN.equals(translateType) && text.contains(" ")) {
            set.addAll(StringUtil.getAllTranslateCase(text));
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
