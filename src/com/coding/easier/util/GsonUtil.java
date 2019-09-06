package com.coding.easier.util;

import com.google.gson.*;

/**
 * @author: D丶Cheng
 * @description:
 * @create: 2019-09-02 10:10
 **/
public class GsonUtil {
    public static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = gsonBuilder.create();
    }

    public static String toPrettyFormat(String json) {
        json = json.trim();
        JsonParser jsonParser = new JsonParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (json.startsWith("[")) {
            JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
            json = gson.toJson(jsonArray);
        } else if (json.startsWith("{")) {
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            json = gson.toJson(jsonObject);
        }
        return json;
    }

    public static void main(String[] args) {
        String responseText = "{\"sentences\":[{\"trans\":\"结果\",\"orig\":\"result\",\"backend\":2},{\"translit\":\"Jiéguǒ\",\"src_translit\":\"riˈzəlt\"}],\"dict\":[{\"pos\":\"名词\",\"terms\":[\"结果\",\"成果\",\"效果\",\"成绩\",\"产物\",\"成效\",\"收获\",\"功\",\"名堂\"],\"entry\":[{\"word\":\"结果\",\"reverse_translation\":[\"result\",\"outcome\",\"consequence\",\"effect\",\"consequent\",\"upshot\"],\"score\":0.67663383},{\"word\":\"成果\",\"reverse_translation\":[\"achievement\",\"result\",\"gain\",\"profit\",\"consequent\",\"sequel\"],\"score\":0.02749503},{\"word\":\"效果\",\"reverse_translation\":[\"effect\",\"result\",\"sound effects\",\"consequent\",\"sequel\"],\"score\":0.011642128},{\"word\":\"成绩\",\"reverse_translation\":[\"score\",\"achievement\",\"result\",\"mark\"],\"score\":0.0039610346},{\"word\":\"产物\",\"reverse_translation\":[\"product\",\"result\",\"outcome\"],\"score\":0.0010999396},{\"word\":\"成效\",\"reverse_translation\":[\"effect\",\"result\"],\"score\":6.074443E-4},{\"word\":\"收获\",\"reverse_translation\":[\"gain\",\"result\",\"acquisition\"],\"score\":5.921267E-5},{\"word\":\"功\",\"reverse_translation\":[\"merit\",\"achievement\",\"meritorious service\",\"accomplishment\",\"exploit\",\"result\"],\"score\":5.7390887E-5},{\"word\":\"名堂\",\"reverse_translation\":[\"variety\",\"result\",\"item\"],\"score\":2.123383E-6}],\"base_form\":\"result\",\"pos_enum\":1},{\"pos\":\"动词\",\"terms\":[\"导致\",\"致使\",\"酿\"],\"entry\":[{\"word\":\"导致\",\"reverse_translation\":[\"lead to\",\"cause\",\"result\",\"bring about\",\"create\"],\"score\":0.45783335},{\"word\":\"致使\",\"reverse_translation\":[\"cause\",\"result\",\"occasion\"],\"score\":8.174057E-4},{\"word\":\"酿\",\"reverse_translation\":[\"brew\",\"ferment\",\"lead to\",\"brew up\",\"result\",\"make wine\"],\"score\":4.6644533E-7}],\"base_form\":\"result\",\"pos_enum\":2}],\"src\":\"en\",\"ld_result\":{\"srclangs\":[\"en\"],\"srclangs_confidences\":[1.0],\"extended_srclangs\":[\"en\"]}}";
        String responseText2 = "{\"sentences\":[{\"trans\":\"结果\",\"orig\":\"result\",\"backend\":2},{\"translit\":\"Jiéguǒ\",\"src_translit\":\"riˈzəlt\"}],\"dict\":[{\"pos\":\"名词\",\"terms\":[\"结果\",\"成果\",\"效果\",\"成绩\",\"产物\",\"成效\",\"收获\",\"功\",\"名堂\"],\"entry\":[{\"word\":\"结果\",\"reverse_translation\":[\"result\",\"outcome\",\"consequence\",\"effect\",\"consequent\",\"upshot\"],\"score\":0.67663383},{\"word\":\"成果\",\"reverse_translation\":[\"achievement\",\"result\",\"gain\",\"profit\",\"consequent\",\"sequel\"],\"score\":0.02749503},{\"word\":\"效果\",\"reverse_translation\":[\"effect\",\"result\",\"sound effects\",\"consequent\",\"sequel\"],\"score\":0.011642128},{\"word\":\"成绩\",\"reverse_translation\":[\"score\",\"achievement\",\"result\",\"mark\"],\"score\":0.0039610346},{\"word\":\"产物\",\"reverse_translation\":[\"product\",\"result\",\"outcome\"],\"score\":0.0010999396},{\"word\":\"成效\",\"reverse_translation\":[\"effect\",\"result\"],\"score\":6.074443E-4},{\"word\":\"收获\",\"reverse_translation\":[\"gain\",\"result\",\"acquisition\"],\"score\":5.921267E-5},{\"word\":\"功\",\"reverse_translation\":[\"merit\",\"achievement\",\"meritorious service\",\"accomplishment\",\"exploit\",\"result\"],\"score\":5.7390887E-5},{\"word\":\"名堂\",\"reverse_translation\":[\"variety\",\"result\",\"item\"],\"score\":2.123383E-6}],\"base_form\":\"result\",\"pos_enum\":1},{\"pos\":\"动词\",\"terms\":[\"导致\",\"致使\",\"酿\"],\"entry\":[{\"word\":\"导致\",\"reverse_translation\":[\"lead to\",\"cause\",\"result\",\"bring about\",\"create\"],\"score\":0.45783335},{\"word\":\"致使\",\"reverse_translation\":[\"cause\",\"result\",\"occasion\"],\"score\":8.174057E-4},{\"word\":\"酿\",\"reverse_translation\":[\"brew\",\"ferment\",\"lead to\",\"brew up\",\"result\",\"make wine\"],\"score\":4.6644533E-7}],\"base_form\":\"result\",\"pos_enum\":2}],\"src\":\"en\",\"ld_result\":{\"srclangs\":[\"en\"],\"srclangs_confidences\":[1.0],\"extended_srclangs\":[\"en\"]}}";
        System.out.println(toPrettyFormat(responseText2));
    }
}
