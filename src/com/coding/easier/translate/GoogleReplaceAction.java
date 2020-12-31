package com.coding.easier.translate;

import com.coding.easier.constant.TranslateConstant;
import com.coding.easier.util.NoticeUtil;
import com.coding.easier.util.StringUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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
                StringBuilder text = new StringBuilder();
                boolean addFlag = true;
                if (result.getDict() == null) {
                    if (result.getSentences().size() == 1 || result.getSentences().size() == 2) {
                        text = new StringBuilder(result.getSentences().get(0).getTrans());
                    } else {
                        addFlag = false;
                        for (GoogleTranslateResult.SentencesBean sentence : result.getSentences()) {
                            if (sentence.getTrans() != null) {
                                text.append(sentence.getTrans());
                            }
                        }
                    }
                    set.add(text.toString());
                } else {
                    for (GoogleTranslateResult.DictBean dictBean : result.getDict()) {
                        for (GoogleTranslateResult.DictBean.EntryBean entryBean : dictBean.getEntry()) {
                            text = new StringBuilder(entryBean.getWord());
                            set.add(text.toString());
                        }
                    }
                }
                if (addFlag) {
                    LinkedHashSet<String> candidateWords = new LinkedHashSet<>();
                    // 填充结果
                    for (String s : set) {
                        addWord(candidateWords, s.trim(), translateType);
                    }
                    set.addAll(candidateWords);
                }
                SelectListStep step = new SelectListStep("", new ArrayList(set));
                step.setDefaultOptionIndex(0);
                ListPopup popup = factory.createListPopup(step, 20);
                popup.setRequestFocus(true);
                popup.show(factory.guessBestPopupLocation(editor));
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
     * @param selectionModel
     * @param newText
     */
    public static void replaceStr(SelectionModel selectionModel, String newText) {
        Runnable runnable = () -> editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), newText);
        WriteCommandAction.runWriteCommandAction(project, runnable);
        selectionModel.removeSelection();
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
}
