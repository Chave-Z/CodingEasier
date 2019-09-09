package com.coding.easier.ui;

import com.coding.easier.enums.FormatEnum;
import com.coding.easier.format.FormatAction;
import com.coding.easier.util.GsonUtil;
import com.coding.easier.util.NoticeUtil;
import com.intellij.openapi.editor.Editor;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author D丶Cheng
 * @description： format
 * @create: 2019-09-06 16:08
 */
public class FormatDialog extends JDialog {

    private final FormatAction formatAction;
    private final Editor editor;

    public JPanel contentPanel;
    private JButton formatBtn;
    private JComboBox comboBox;
    private JTextPane textPanel;
    private JPanel centerPanel;

    private String selectedType = FormatEnum.JSON.getValue();

    public FormatDialog(FormatAction formatAction, Editor editor) {
        this.formatAction = formatAction;
        this.editor = editor;
        textPanel.setFont(new Font("Serif", 1, 14));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPanel);
        setModal(true);
        getRootPane().setDefaultButton(formatBtn);
        comboBox.addItem(FormatEnum.JSON.getValue());
//        comboBox.addItem(FormatEnum.XML.getValue());
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        initListener();
    }

    public void initListener() {
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                selectedType = comboBox.getSelectedItem().toString();
            }
        });
        formatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isBlank(textPanel.getText())) {
                    NoticeUtil.error("请输入数据");
                    return;
                }
                String formattedString = "";
                if (FormatEnum.JSON.getValue().equals(selectedType)) {
                    formattedString = formatJson(textPanel.getText());
                } else {
                    NoticeUtil.error("正在开发中...");
                    return;
                }
                if ("".equals(formattedString)) {
                    return;
                }
                textPanel.setText(formattedString);
            }
        });

//        compressBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPanel.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * 格式化json
     *
     * @param text
     */
    private String formatJson(String text) {
        String str = "";
        try {
            str = GsonUtil.toPrettyFormat(text);
        } catch (Exception e) {
            System.out.println(NoticeUtil.getStackTrace(e));
            NoticeUtil.error("JSON格式有误");
        }
        return str;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        FormatDialog dialog = new FormatDialog(null, null);
        dialog.setSize(870, 515);
        dialog.setTitle("CodingEasier Format");
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.pack();
    }
}
