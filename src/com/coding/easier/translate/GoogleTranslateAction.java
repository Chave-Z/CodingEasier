package com.coding.easier.translate;

import com.coding.easier.util.NoticeUtil;
import com.coding.easier.constant.TranslateConstant;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;

import javax.swing.*;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.util.regex.Matcher;

/**
 * @author D丶Cheng
 * @description: 谷歌翻译工具类
 */
public class GoogleTranslateAction extends AbstractTranslateAction {

    @Override
    protected void doTranslate(String selectText) {
        Matcher m = p.matcher(selectText.trim());
        String translateType = m.find() ? TranslateConstant.ZH_CN_TO_EN : TranslateConstant.EN_TO_ZH_CN;
        String result = null;
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
                factory.createHtmlTextBalloonBuilder(result.toString(), (Icon) null, (Color) new JBColor(Color.GRAY, Color.DARK_GRAY), (HyperlinkListener) null).setFadeoutTime(5000L).createBalloon().show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
            }
        });
    }
}
