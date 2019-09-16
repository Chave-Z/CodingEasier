package com.coding.easier.translate;

import com.coding.easier.ui.modules.ColorService;
import com.coding.easier.util.NoticeUtil;
import com.coding.easier.constant.TranslateConstant;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
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
                TranslateBalloon translateBalloon = new TranslateBalloon(result);
                BalloonBuilder balloonBuilder = factory.createBalloonBuilder(translateBalloon.getjPanel());
                balloonBuilder.setFillColor(ColorService.forCurrentTheme(ColorService.Background));
                balloonBuilder.setContentInsets(JBUI.insets(40, 40));
                balloonBuilder.setBorderInsets(JBUI.emptyInsets());
                balloonBuilder.setBorderColor(ColorService.forCurrentTheme(ColorService.Background));
                balloonBuilder.setShadow(true);
                Balloon balloon = balloonBuilder.createBalloon();
                setBounds(translateBalloon, balloon);
                balloon.show(factory.guessBestPopupLocation(editor), Balloon.Position.above);
            }
        });
    }

    /**
     * 设置了宽高，定位就无效了...
     *
     * @param balloon
     */
    private void setBounds(TranslateBalloon translateBalloon, Balloon balloon) {
        int width = translateBalloon.getWidth();
        int height = translateBalloon.getHeight() + 80;
        JFrame jFrame = WindowManager.getInstance().getFrame(project);
        int x = (int) (jFrame.getBounds().getWidth() / 2 - width / 2);
        int y = (int) (jFrame.getBounds().getHeight() / 2 - height / 2);
        balloon.setBounds(new Rectangle(x, y, width, height));
    }
}
