package com.coding.easier.translate;

import com.coding.easier.util.NoticeUtil;
import com.coding.easier.util.GsonUtil;
import com.coding.easier.constant.TranslateConstant;
import com.coding.easier.util.StringUtil;
import com.coding.easier.util.TkTools;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author: D丶Cheng
 * @description: 翻译基础类
 * @create: 2019-09-03 10:15
 **/
public abstract class AbstractTranslateAction extends AnAction {

    public static final Pattern p = compile("[\u4e00-\u9fa5]");

    public static Editor editor;
    public static Project project;
    public static SelectionModel selectionModel;

    @Override
    public void actionPerformed(AnActionEvent event) {
        try {
            selectionModel = editor.getSelectionModel();
            String selectedText = selectionModel.getSelectedText();
            if (StringUtils.isBlank(selectedText)) {
                NoticeUtil.error("请选择要翻译的字符");
                return;
            }
            NoticeUtil.init(this.getClass().getSimpleName(), 1);
            executeTranslate(event);
        } catch (Exception e) {
            NoticeUtil.error(e);
        }
    }

    @Override
    public void update(AnActionEvent e) {
        project = e.getData(CommonDataKeys.PROJECT);
        editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null
                && editor.getSelectionModel().hasSelection());
    }

    /**
     * 执行翻译操作
     *
     * @param event
     */
    public void executeTranslate(AnActionEvent event) {
        final SelectionModel selectionModel = editor.getSelectionModel();
        String selectText = StringUtil.textToWords(selectionModel.getSelectedText());
        if (!"".equals(selectText.trim())) {
            doTranslate(selectText);
        }
    }

    /**
     * 调用接口,翻译并返回值
     *
     * @param word
     * @param translateType
     * @param mEditor
     * @return
     * @throws Exception
     */
    public static GoogleTranslateResult translate(String word, String translateType, Editor mEditor) throws Exception {

        CloseableHttpClient client = HttpClients.createDefault();
        //replace填坑参数地址值
        String url = getTranslateUrl(translateType, TkTools.tk(word), URLEncoder.encode(word, "utf-8"));
        HttpGet get = new HttpGet(url);
        get.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        get.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9");
        //发起请求
        CloseableHttpResponse response = client.execute(get);
        if (200 == response.getStatusLine().getStatusCode()) {
            editor = mEditor;
            String responseText = EntityUtils.toString(response.getEntity(), "utf-8");
            GoogleTranslateResult translateResult = GsonUtil.gson.fromJson(responseText, GoogleTranslateResult.class);
            System.out.println(translateResult.toString());
            return translateResult;
        }
        return null;
    }


    /**
     * 根据类型选择接口地址
     *
     * @param translateType
     * @return
     */
    private static String getTranslateUrl(String translateType, String tk, String word) {
        if (translateType.equals(TranslateConstant.ZH_CN_TO_EN)) {
            return String.format(TranslateConstant.GOOGLE_TRANSLATE_URL, "zh-CN", "en", tk, word);
        } else {
            return String.format(TranslateConstant.GOOGLE_TRANSLATE_URL, "en", "zh-CN", tk, word);
        }
    }

    /**
     * 执行翻译
     *
     * @param selectText
     * @return
     */
    protected abstract void doTranslate(String selectText);

    /**
     * 文本弹出显示
     *
     * @param result
     */
    protected abstract void showPopupBalloon(GoogleTranslateResult result, String translateType);
}
