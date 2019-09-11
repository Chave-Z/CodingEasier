package com.coding.easier.translate;

import javax.swing.*;
import java.awt.*;

import com.coding.easier.ui.modules.ColorService;
import com.coding.easier.ui.modules.IdeaColorService;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.wm.WindowManager;

/**
 * @author: D丶Cheng
 * @description: 翻译ui
 * @create: 2019-09-10 10:56
 **/
public class TranslateBalloon {

    static {
        ColorService.install(new IdeaColorService());
    }

    private GoogleTranslateResult result;

    private JPanel jPanel;

    private int origLength;

    private int transLength;

    private int height;

    private int width;

    public JPanel getjPanel() {
        return jPanel;
    }

    public int getOrigLength() {
        return origLength;
    }

    public int getTransLength() {
        return transLength;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    TranslateBalloon(GoogleTranslateResult result) {
        this.result = result;
        init();
    }

    private void init() {
        jPanel = new JPanel(new BorderLayout());
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        StringBuilder origBuilder = new StringBuilder("<body style='font-size:14px;font-weight: bold;color: #CAA923;'>");
        StringBuilder transBuilder = new StringBuilder("<body style='font-size:14px;font-weight: bold;color: #CAA923;'>");
//        origBuilder.append("一个提升开发效率的idea插件，初衷就是将一些常用的功能写入到这个插件，提高编码效率。但是因为这方面的资料过少加上需要工作的原因，" +
//                "所以新功能更新可能会相对缓慢，但是一定会慢慢加入更多的功能，我会在代码中加入注释，尽量让代码简单易懂，也算是为了尽量让它成为让一个更多人" +
//                "能自己编写插件的参考资料吧。当然也欢迎提交反馈，有想要实现的功能也可以建议，要是建议确实不错且时间允许，我就会尝试添加。");
//        transBuilder.append("一个提升开发效率的idea插件，初衷就是将一些常用的功能写入到这个插件，提高编码效率。但是因为这方面的资料过少加上需要工作的原因，" +
//                "所以新功能更新可能会相对缓慢，但是一定会慢慢加入更多的功能，我会在代码中加入注释，尽量让代码简单易懂，也算是为了尽量让它成为让一个更多人" +
//                "能自己编写插件的参考资料吧。当然也欢迎提交反馈，有想要实现的功能也可以建议，要是建议确实不错且时间允许，我就会尝试添加。"
//                + "一个提升开发效率的idea插件，初衷就是将一些常用的功能写入到这个插件，提高编码效率。但是因为这方面的资料过少加上需要工作的原因，" +
//                "所以新功能更新可能会相对缓慢，但是一定会慢慢加入更多的功能，我会在代码中加入注释，尽量让代码简单易懂，也算是为了尽量让它成为让一个更多人" +
//                "能自己编写插件的参考资料吧。当然也欢迎提交反馈，有想要实现的功能也可以建议，要是建议确实不错且时间允许，我就会尝试添加。");
        if (result.getSentences().size() == 1 || result.getSentences().size() == 2) {
            GoogleTranslateResult.SentencesBean sentencesBean = result.getSentences().get(0);
            origBuilder.append(result.getSentences().get(0).getOrig());
            transBuilder.append(result.getSentences().get(0).getTrans()).append("<br>");
            if (!sentencesBean.getOrig().equals(sentencesBean.getTrans()) && result.getDict() != null) {
                for (int i = 0, len = result.getDict().size(); i < len; i++) {
                    transBuilder.append(result.getDict().get(i));
                }
            }
        } else {
            for (GoogleTranslateResult.SentencesBean sentence : result.getSentences()) {
                if (sentence.getTrans() != null) {
                    origBuilder.append(sentence.getOrig());
                    transBuilder.append(sentence.getTrans());
                }
            }
        }
        origBuilder.append("</body>");
        transBuilder.append("</body>");
        origLength = origBuilder.length();
        transLength = transBuilder.length();
        createPanel("原文：", origBuilder, BorderLayout.NORTH);
        createPanel("翻译结果：", transBuilder, BorderLayout.CENTER);
    }

    private void createPanel(String text, StringBuilder builder, String position) {
        System.out.println(builder.toString());
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text);
        label.setForeground(new Color(16, 187, 100));
        label.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(builder.toString());
        editorPane.setEditable(false);
        editorPane.setBackground(ColorService.forCurrentTheme(ColorService.Background));
//        editorPane.setEnabled(false);
//        editorPane.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
//        editorPane.setForeground(new Color(255, 135, 15));
        editorPane.setSelectionStart(0);
        editorPane.setSelectionEnd(0);
        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setBorder(null);
        scrollPane.setMaximumSize(new Dimension(520, 200));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane, BorderLayout.CENTER);
        setPreferredSize(builder.length(), panel);
        jPanel.add(panel, position);
//        jPanel.add(panel);
    }

    private void setPreferredSize(int len, JPanel panel) {
        boolean flag = len * 16 > 540;
        int width = flag ? 520 : (len * 16 + 80);
        int height = flag ? Math.min(((len * 16 / 520 + 2) * 16), 200) : 32;
        System.out.println("height--->" + height);
        this.height += height;
        this.width = Math.max(this.width, width);
        panel.setPreferredSize(new Dimension(width, height));
    }

    public static void main(String[] args) {
        TranslateBalloon translateBalloon = new TranslateBalloon(null);
//        TranslateBalloon translateBalloon = new TranslateBalloon();
        JFrame jFrame = new JFrame();
        jFrame.add(translateBalloon.jPanel);
        jFrame.setSize(520, 400);
        jFrame.setVisible(true);
    }
}
