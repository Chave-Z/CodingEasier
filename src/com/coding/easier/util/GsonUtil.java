package com.coding.easier.util;

import com.google.gson.*;

/**
 * @author: D丶Cheng
 * @description:
 * @create: 2019-09-02 10:10
 **/
public class GsonUtil {

    public static Gson gson;
    public static Gson prettyGson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = gsonBuilder.create();
        GsonBuilder prettyGsonBuilder = new GsonBuilder();
        prettyGsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        prettyGson = prettyGsonBuilder.setPrettyPrinting().create();
    }

    /**
     * json 转对象，可以兼容不认识的属性
     *
     * @param json
     * @param objectType
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> objectType) {
        return gson.fromJson(json, objectType);
    }

    /**
     * 格式化
     *
     * @param json
     * @return
     */
    public static String format(String json) {
        json = unescape(json);
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
            return prettyGson.toJson(jsonArray);
        } else if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            return prettyGson.toJson(jsonObject);
        } else {
            throw new RuntimeException("Json 格式有误");
        }
    }

    /**
     * 压缩
     */
    public static String compress(String json) {
        json = unescape(json);
        JsonParser parser = new JsonParser();
        JsonElement je = parser.parse(json);
        return gson.toJson(je);
    }


    public static String escape(String json) {
        return json.replaceAll("\"", "\\\\\"");
    }

    public static String unescape(String json) {
        return json.replaceAll("\\\\\"", "\"");
    }


    public static void main(String[] args) {
        String json = "{\"orders Store\":{\"isServer Rendered\":true,\"isFinishInitLoading\":true,\"errorRedirectUrl\":\"\\u002F\",\"bannerDataList\":{\"0\":[],\"1\":null,\"2\":null,\"3\":null,\"4\":null,\"5\":null},\"recommendGoodsList\":[],\"orders\":[{\"type\":1,\"groupId\":\"45471597194\",\"groupOrderId\":\"1345333292176383721\",\"addressId\":\"19268609966\",\"shippingId\":\"0\",\"orderSn\":\"201220-333292176383721\",\"parentOrderSn\":\"\",\"orderAmount\":\"8\",\"shippingAmount\":0,\"discountAmount\":0,\"serviceAmount\":0,\"displayAmount\":800,\"usedCoupons\":false,\"sourceBizSn\":\"201220-333292176383721\",\"bizType\":0,\"status\":7,\"groupStatus\":99,\"orderStatus\":2,\"payStatus\":0,\"shippingStatus\":0,\"commentStatus\":0,\"combinedOrderStatus\":5,\"urgeShipmentStatus\":0,\"shippingTime\":0,\"orderTime\":1608447871,\"receiveTime\":0,\"trackingNumber\":\"\",\"orderLinkUrl\":\"order.html?order_sn=201220-333292176383721\",\"orderGoods\":[{\"goodsId\":\"198510980415\",\"skuId\":\"742238811466\",\"goodsName\":\"干脆面整箱特价批发网红零食小吃掌心脆干吃面火鸡面方便面香脆面\",\"goodsPrice\":\"8\",\"goodsNumber\":1,\"thumbUrl\":\"https:\\u002F\\u002Fimg.pddpic.com\\u002Fmms-material-img\\u002F2020-11-09\\u002F9cbf7b95-2e0a-494f-99da-536de657ff27.jpeg.a.jpeg\",\"spec\":\"混合口味【8种口味随机5味混装】,整箱10包\",\"eventType\":0,\"isPreSale\":0,\"goodsType\":1,\"catId1\":6398,\"catId2\":6469,\"catId3\":6471,\"catId4\":7553,\"goods\":{}}],\"groupOrder\":{\"groupStatus\":99,\"customerNum\":1,\"expireTime\":1608534271},\"mall\":{\"id\":\"825762120\",\"logo\":\"http:\\u002F\\u002Ft16img.yangkeduo.com\\u002Fpdd_ims\\u002Fimg_check\\u002Fv2\\u002FFFFFFF469B460320200112174741261\\u002F32f59c0d82fa48ecab13d6816a490d84.png\",\"mallName\":\"宸邦零食专营店\",\"tagList\":[{\"logoUrl\":\"https:\\u002F\\u002Ft16img.yangkeduo.com\\u002Fmms_static\\u002F2019-12-04\\u002Fea0eaa26-203d-487b-9d7e-9c920c0b8d51.gif\",\"logoHeight\":48,\"logoWidth\":174}],\"mallUrl\":\"mall_page.html?mall_id=825762120&msn=vr5gemiwnkc7ycu7opssstpayy_axbuy&goods_id=198510980415\"},\"priceDesc\":{\"prefix\":\"实付:\",\"suffix\":\"(免运费)\",\"suffixStyle\":0,\"prefixStyle\":0,\"hidePrice\":false,\"prefixV2\":null,\"suffixFold\":false,\"extraInfo\":null},\"orderStatusPrompt\":\"交易已取消\",\"activityType\":0,\"isSuperGroup\":false,\"tabsBelong\":[\"all\"],\"goodsTags\":[],\"orderTags\":[],\"orderButtons\":[{\"type\":1,\"typeValue\":{\"url\":\"goods.html?goods_id=198510980415&show_sku_selector=1&sku_id=742238811466&page_from=100&page_el_sn=99671&goods_id=198510980415&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"再次拼单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99671\",\"goods_id\":\"198510980415\",\"sale_status\":\"0\",\"growth_tip_scene\":\"0\"},\"id\":7000,\"subId\":-1},{\"type\":4,\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"删除订单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99662\"},\"id\":17000,\"subId\":-1}],\"alertInfo\":{\"confirmReceipt\":{\"message\":\"为保障您的售后权益，请收到货确认无误后，再确认收货哦！\"}},\"fpId\":\"99giVEyZ51ytzQJqoHtU-WVJnwPsQC1W-40stQ9NvLI\",\"payMethod\":[],\"shippingName\":\"\",\"showLotteryResultButton\":false,\"buttonLabels\":[{\"type\":1,\"typeValue\":{\"url\":\"goods.html?goods_id=198510980415&show_sku_selector=1&sku_id=742238811466&page_from=100&page_el_sn=99671&goods_id=198510980415&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"再次拼单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99671\",\"goods_id\":\"198510980415\",\"sale_status\":\"0\",\"growth_tip_scene\":\"0\"},\"id\":7000,\"subId\":-1},{\"type\":4,\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"删除订单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99662\"},\"id\":17000,\"subId\":-1}],\"extraInfo\":{},\"listItemName\":\"ordersListItem\"},{\"type\":1,\"groupId\":\"14994814637\",\"groupOrderId\":\"1148375125607382954\",\"addressId\":\"19268609966\",\"shippingId\":\"85\",\"orderSn\":\"200606-354224375003721\",\"parentOrderSn\":\"\",\"orderAmount\":\"6.52\",\"shippingAmount\":0,\"discountAmount\":0,\"serviceAmount\":0,\"displayAmount\":652,\"usedCoupons\":false,\"sourceBizSn\":\"200606-354224375003721\",\"bizType\":0,\"status\":4,\"groupStatus\":1,\"orderStatus\":1,\"payStatus\":2,\"shippingStatus\":2,\"commentStatus\":3,\"combinedOrderStatus\":4,\"urgeShipmentStatus\":0,\"shippingTime\":1591496203,\"orderTime\":1591428178,\"receiveTime\":1592792204,\"trackingNumber\":\"YT9187010860441\",\"orderLinkUrl\":\"order.html?order_sn=200606-354224375003721\",\"orderGoods\":[{\"goodsId\":\"8848168430\",\"skuId\":\"247561740270\",\"goodsName\":\"不锈钢强力鸡骨厨房家用剪刀剪骨剪肉多功能多用省力日用剪刀\",\"goodsPrice\":\"6.52\",\"goodsNumber\":1,\"thumbUrl\":\"https:\\u002F\\u002Ft00img.yangkeduo.com\\u002Fgoods\\u002Fimages\\u002F2019-05-16\\u002F7096d6a1-8af3-4f86-a107-3c8468ed2c60.jpg\",\"spec\":\"黑色强力鸡骨剪\",\"eventType\":0,\"isPreSale\":0,\"goodsType\":1,\"catId1\":16989,\"catId2\":16997,\"catId3\":17054,\"catId4\":17124,\"goods\":{}}],\"groupOrder\":{\"groupStatus\":1,\"customerNum\":2,\"expireTime\":1591514064,\"createAt\":1591427664,\"captainUid\":4249885578,\"groupMemberNum\":2,\"successTime\":1591428184,\"avatars\":[\"https:\\u002F\\u002Favatar2.pddpic.com\\u002Fa\\u002Fb0ccf5d9d7fad0ac966c2430fbd2ae4a2cbd6cf8-1563017906?imageMogr2\\u002Fthumbnail\\u002F100x\",\"https:\\u002F\\u002Favatar2.pddpic.com\\u002Favatar\\u002Fdefault\\u002F8.png\"]},\"mall\":{\"id\":\"560395019\",\"logo\":\"https:\\u002F\\u002Ft00img.yangkeduo.com\\u002Fgoods\\u002Fimages\\u002F2020-07-03\\u002F03c78269-9ab6-45a5-b213-1735f940e64c_suffix.jpg\",\"mallName\":\"高美加生活用品\",\"mallUrl\":\"mall_page.html?mall_id=560395019&msn=z6i5pwvbvgtdxwapeb7hl5ptwe_axbuy&goods_id=8848168430\"},\"priceDesc\":{\"prefix\":\"实付:\",\"suffix\":\"(免运费)\",\"suffixStyle\":0,\"prefixStyle\":0,\"hidePrice\":false,\"prefixV2\":null,\"suffixFold\":false,\"extraInfo\":null},\"orderStatusPrompt\":\"交易成功\",\"activityType\":33,\"isSuperGroup\":false,\"tabsBelong\":[\"all\"],\"goodsTags\":[],\"orderTags\":[],\"orderButtons\":[{\"type\":1,\"typeValue\":{\"url\":\"goods.html?goods_id=8848168430&show_sku_selector=1&sku_id=247561740270&page_from=100&page_el_sn=99671&goods_id=8848168430&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"再次拼单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99671\",\"goods_id\":\"8848168430\",\"sale_status\":\"0\",\"growth_tip_scene\":\"0\"},\"id\":7000,\"subId\":-1},{\"type\":21,\"typeValue\":{\"url\":\"\",\"msg\":\"\",\"path\":\"api\\u002Fasm\\u002Ftrigger\",\"param\":{\"order_sn\":\"200606-354224375003721\"}},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"申请退款\",\"supportVersion\":\"4.70.9\",\"metricInfo\":{\"page_el_sn\":\"96433\"},\"id\":13000,\"subId\":-1},{\"type\":4,\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"删除订单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99662\"},\"id\":17000,\"subId\":-1}],\"alertInfo\":{\"confirmReceipt\":{\"message\":\"为保障您的售后权益，请收到货确认无误后，再确认收货哦！\"}},\"extraInfo\":{\"preRenderParam\":\"5\"},\"fpId\":\"UvSuVOo4bePsshddBUIOjq2hGkvRE3LslUxnGGrJka4\",\"payMethod\":[],\"shippingName\":\"\",\"showLotteryResultButton\":false,\"buttonLabels\":[{\"type\":1,\"typeValue\":{\"url\":\"goods.html?goods_id=8848168430&show_sku_selector=1&sku_id=247561740270&page_from=100&page_el_sn=99671&goods_id=8848168430&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"再次拼单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99671\",\"goods_id\":\"8848168430\",\"sale_status\":\"0\",\"growth_tip_scene\":\"0\"},\"id\":7000,\"subId\":-1},{\"type\":21,\"typeValue\":{\"url\":\"\",\"msg\":\"\",\"path\":\"api\\u002Fasm\\u002Ftrigger\",\"param\":{\"order_sn\":\"200606-354224375003721\"}},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"申请退款\",\"supportVersion\":\"4.70.9\",\"metricInfo\":{\"page_el_sn\":\"96433\"},\"id\":13000,\"subId\":-1},{\"type\":4,\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"删除订单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99662\"},\"id\":17000,\"subId\":-1}],\"listItemName\":\"ordersListItem\"}],\"currentType\":0,\"currentList\":{\"0\":[{\"type\":1,\"groupId\":\"45471597194\",\"groupOrderId\":\"1345333292176383721\",\"addressId\":\"19268609966\",\"shippingId\":\"0\",\"orderSn\":\"201220-333292176383721\",\"parentOrderSn\":\"\",\"orderAmount\":\"8\",\"shippingAmount\":0,\"discountAmount\":0,\"serviceAmount\":0,\"displayAmount\":800,\"usedCoupons\":false,\"sourceBizSn\":\"201220-333292176383721\",\"bizType\":0,\"status\":7,\"groupStatus\":99,\"orderStatus\":2,\"payStatus\":0,\"shippingStatus\":0,\"commentStatus\":0,\"combinedOrderStatus\":5,\"urgeShipmentStatus\":0,\"shippingTime\":0,\"orderTime\":1608447871,\"receiveTime\":0,\"trackingNumber\":\"\",\"orderLinkUrl\":\"order.html?order_sn=201220-333292176383721\",\"orderGoods\":[{\"goodsId\":\"198510980415\",\"skuId\":\"742238811466\",\"goodsName\":\"干脆面整箱特价批发网红零食小吃掌心脆干吃面火鸡面方便面香脆面\",\"goodsPrice\":\"8\",\"goodsNumber\":1,\"thumbUrl\":\"https:\\u002F\\u002Fimg.pddpic.com\\u002Fmms-material-img\\u002F2020-11-09\\u002F9cbf7b95-2e0a-494f-99da-536de657ff27.jpeg.a.jpeg\",\"spec\":\"混合口味【8种口味随机5味混装】,整箱10包\",\"eventType\":0,\"isPreSale\":0,\"goodsType\":1,\"catId1\":6398,\"catId2\":6469,\"catId3\":6471,\"catId4\":7553,\"goods\":{}}],\"groupOrder\":{\"groupStatus\":99,\"customerNum\":1,\"expireTime\":1608534271},\"mall\":{\"id\":\"825762120\",\"logo\":\"http:\\u002F\\u002Ft16img.yangkeduo.com\\u002Fpdd_ims\\u002Fimg_check\\u002Fv2\\u002FFFFFFF469B460320200112174741261\\u002F32f59c0d82fa48ecab13d6816a490d84.png\",\"mallName\":\"宸邦零食专营店\",\"tagList\":[{\"logoUrl\":\"https:\\u002F\\u002Ft16img.yangkeduo.com\\u002Fmms_static\\u002F2019-12-04\\u002Fea0eaa26-203d-487b-9d7e-9c920c0b8d51.gif\",\"logoHeight\":48,\"logoWidth\":174}],\"mallUrl\":\"mall_page.html?mall_id=825762120&msn=vr5gemiwnkc7ycu7opssstpayy_axbuy&goods_id=198510980415\"},\"priceDesc\":{\"prefix\":\"实付:\",\"suffix\":\"(免运费)\",\"suffixStyle\":0,\"prefixStyle\":0,\"hidePrice\":false,\"prefixV2\":null,\"suffixFold\":false,\"extraInfo\":null},\"orderStatusPrompt\":\"交易已取消\",\"activityType\":0,\"isSuperGroup\":false,\"tabsBelong\":[\"all\"],\"goodsTags\":[],\"orderTags\":[],\"orderButtons\":[{\"type\":1,\"typeValue\":{\"url\":\"goods.html?goods_id=198510980415&show_sku_selector=1&sku_id=742238811466&page_from=100&page_el_sn=99671&goods_id=198510980415&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"再次拼单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99671\",\"goods_id\":\"198510980415\",\"sale_status\":\"0\",\"growth_tip_scene\":\"0\"},\"id\":7000,\"subId\":-1},{\"type\":4,\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"删除订单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99662\"},\"id\":17000,\"subId\":-1}],\"alertInfo\":{\"confirmReceipt\":{\"message\":\"为保障您的售后权益，请收到货确认无误后，再确认收货哦！\"}},\"fpId\":\"99giVEyZ51ytzQJqoHtU-WVJnwPsQC1W-40stQ9NvLI\",\"payMethod\":[],\"shippingName\":\"\",\"showLotteryResultButton\":false,\"buttonLabels\":[{\"type\":1,\"typeValue\":{\"url\":\"goods.html?goods_id=198510980415&show_sku_selector=1&sku_id=742238811466&page_from=100&page_el_sn=99671&goods_id=198510980415&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"再次拼单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99671\",\"goods_id\":\"198510980415\",\"sale_status\":\"0\",\"growth_tip_scene\":\"0\"},\"id\":7000,\"subId\":-1},{\"type\":4,\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"删除订单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99662\"},\"id\":17000,\"subId\":-1}],\"extraInfo\":{},\"listItemName\":\"ordersListItem\"},{\"type\":1,\"groupId\":\"14994814637\",\"groupOrderId\":\"1148375125607382954\",\"addressId\":\"19268609966\",\"shippingId\":\"85\",\"orderSn\":\"200606-354224375003721\",\"parentOrderSn\":\"\",\"orderAmount\":\"6.52\",\"shippingAmount\":0,\"discountAmount\":0,\"serviceAmount\":0,\"displayAmount\":652,\"usedCoupons\":false,\"sourceBizSn\":\"200606-354224375003721\",\"bizType\":0,\"status\":4,\"groupStatus\":1,\"orderStatus\":1,\"payStatus\":2,\"shippingStatus\":2,\"commentStatus\":3,\"combinedOrderStatus\":4,\"urgeShipmentStatus\":0,\"shippingTime\":1591496203,\"orderTime\":1591428178,\"receiveTime\":1592792204,\"trackingNumber\":\"YT9187010860441\",\"orderLinkUrl\":\"order.html?order_sn=200606-354224375003721\",\"orderGoods\":[{\"goodsId\":\"8848168430\",\"skuId\":\"247561740270\",\"goodsName\":\"不锈钢强力鸡骨厨房家用剪刀剪骨剪肉多功能多用省力日用剪刀\",\"goodsPrice\":\"6.52\",\"goodsNumber\":1,\"thumbUrl\":\"https:\\u002F\\u002Ft00img.yangkeduo.com\\u002Fgoods\\u002Fimages\\u002F2019-05-16\\u002F7096d6a1-8af3-4f86-a107-3c8468ed2c60.jpg\",\"spec\":\"黑色强力鸡骨剪\",\"eventType\":0,\"isPreSale\":0,\"goodsType\":1,\"catId1\":16989,\"catId2\":16997,\"catId3\":17054,\"catId4\":17124,\"goods\":{}}],\"groupOrder\":{\"groupStatus\":1,\"customerNum\":2,\"expireTime\":1591514064,\"createAt\":1591427664,\"captainUid\":4249885578,\"groupMemberNum\":2,\"successTime\":1591428184,\"avatars\":[\"https:\\u002F\\u002Favatar2.pddpic.com\\u002Fa\\u002Fb0ccf5d9d7fad0ac966c2430fbd2ae4a2cbd6cf8-1563017906?imageMogr2\\u002Fthumbnail\\u002F100x\",\"https:\\u002F\\u002Favatar2.pddpic.com\\u002Favatar\\u002Fdefault\\u002F8.png\"]},\"mall\":{\"id\":\"560395019\",\"logo\":\"https:\\u002F\\u002Ft00img.yangkeduo.com\\u002Fgoods\\u002Fimages\\u002F2020-07-03\\u002F03c78269-9ab6-45a5-b213-1735f940e64c_suffix.jpg\",\"mallName\":\"高美加生活用品\",\"mallUrl\":\"mall_page.html?mall_id=560395019&msn=z6i5pwvbvgtdxwapeb7hl5ptwe_axbuy&goods_id=8848168430\"},\"priceDesc\":{\"prefix\":\"实付:\",\"suffix\":\"(免运费)\",\"suffixStyle\":0,\"prefixStyle\":0,\"hidePrice\":false,\"prefixV2\":null,\"suffixFold\":false,\"extraInfo\":null},\"orderStatusPrompt\":\"交易成功\",\"activityType\":33,\"isSuperGroup\":false,\"tabsBelong\":[\"all\"],\"goodsTags\":[],\"orderTags\":[],\"orderButtons\":[{\"type\":1,\"typeValue\":{\"url\":\"goods.html?goods_id=8848168430&show_sku_selector=1&sku_id=247561740270&page_from=100&page_el_sn=99671&goods_id=8848168430&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"再次拼单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99671\",\"goods_id\":\"8848168430\",\"sale_status\":\"0\",\"growth_tip_scene\":\"0\"},\"id\":7000,\"subId\":-1},{\"type\":21,\"typeValue\":{\"url\":\"\",\"msg\":\"\",\"path\":\"api\\u002Fasm\\u002Ftrigger\",\"param\":{\"order_sn\":\"200606-354224375003721\"}},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"申请退款\",\"supportVersion\":\"4.70.9\",\"metricInfo\":{\"page_el_sn\":\"96433\"},\"id\":13000,\"subId\":-1},{\"type\":4,\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"删除订单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99662\"},\"id\":17000,\"subId\":-1}],\"alertInfo\":{\"confirmReceipt\":{\"message\":\"为保障您的售后权益，请收到货确认无误后，再确认收货哦！\"}},\"extraInfo\":{\"preRenderParam\":\"5\"},\"fpId\":\"UvSuVOo4bePsshddBUIOjq2hGkvRE3LslUxnGGrJka4\",\"payMethod\":[],\"shippingName\":\"\",\"showLotteryResultButton\":false,\"buttonLabels\":[{\"type\":1,\"typeValue\":{\"url\":\"goods.html?goods_id=8848168430&show_sku_selector=1&sku_id=247561740270&page_from=100&page_el_sn=99671&goods_id=8848168430&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"再次拼单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99671\",\"goods_id\":\"8848168430\",\"sale_status\":\"0\",\"growth_tip_scene\":\"0\"},\"id\":7000,\"subId\":-1},{\"type\":21,\"typeValue\":{\"url\":\"\",\"msg\":\"\",\"path\":\"api\\u002Fasm\\u002Ftrigger\",\"param\":{\"order_sn\":\"200606-354224375003721\"}},\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"申请退款\",\"supportVersion\":\"4.70.9\",\"metricInfo\":{\"page_el_sn\":\"96433\"},\"id\":13000,\"subId\":-1},{\"type\":4,\"style\":{\"color\":0,\"clickType\":0},\"briefPrompt\":\"删除订单\",\"supportVersion\":\"4.15.0\",\"metricInfo\":{\"page_el_sn\":\"99662\"},\"id\":17000,\"subId\":-1}],\"listItemName\":\"ordersListItem\"}]},\"scrollTop\":{\"0\":0},\"commentInfo\":{},\"longPressGoodsId\":\"\",\"hasOrder\":{\"0\":true,\"1\":true,\"2\":true,\"3\":true,\"4\":true,\"5\":true},\"isActive\":\"0\",\"gotoAppUrl\":\"\",\"loadAll\":{\"0\":null,\"1\":null,\"2\":null,\"3\":null,\"4\":null,\"5\":null},\"hasMarketBanner\":{\"0\":true,\"1\":false,\"2\":false,\"3\":false,\"4\":false,\"5\":false},\"dialogInfo\":{},\"appIDSet\":{\"2\":9,\"9\":1,\"30\":3,\"31\":4,\"35\":5,\"38\":2,\"52\":7,\"97\":10,\"122\":12,\"135\":13,\"322\":11,\"-1\":6},\"type\":\"0\",\"recommendOffset\":0,\"isSupportWebp\":true,\"routeList\":[\"order.html\"],\"ordersService\":{\"size\":10,\"maxSize\":20,\"maxRounds\":4,\"offset\":{\"0\":\"200606-354224375003721\",\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}},\"generalService\":{},\"recommendService\":{\"goodsListOffset\":{},\"defaultGoodsCount\":20,\"defaultTabCount\":3},\"marketingBanner\":{},\"isNativePlatform\":false,\"ordersExtraInfo\":{},\"hostname\":\"mobile.yangkeduo.com\",\"uid\":\"4010930409097\",\"initOdersList\":{\"hehe\":[{\"type\":1,\"group_id\":\"45471597194\",\"group_order_id\":\"1345333292176383721\",\"address_id\":\"19268609966\",\"shipping_id\":\"0\",\"order_sn\":\"201220-333292176383721\",\"parent_order_sn\":\"\",\"order_amount\":800,\"shipping_amount\":0,\"discount_amount\":0,\"service_amount\":0,\"display_amount\":800,\"used_coupons\":false,\"source_biz_sn\":\"201220-333292176383721\",\"biz_type\":0,\"status\":7,\"group_status\":99,\"order_status\":2,\"pay_status\":0,\"shipping_status\":0,\"comment_status\":0,\"combined_order_status\":5,\"urge_shipment_status\":0,\"shipping_time\":0,\"order_time\":1608447871,\"receive_time\":0,\"tracking_number\":\"\",\"order_link_url\":\"order.html?order_sn=201220-333292176383721\",\"order_goods\":[{\"goods_id\":\"198510980415\",\"sku_id\":\"742238811466\",\"goods_name\":\"干脆面整箱特价批发网红零食小吃掌心脆干吃面火鸡面方便面香脆面\",\"goods_price\":800,\"goods_number\":1,\"thumb_url\":\"https:\\u002F\\u002Fimg.pddpic.com\\u002Fmms-material-img\\u002F2020-11-09\\u002F9cbf7b95-2e0a-494f-99da-536de657ff27.jpeg.a.jpeg\",\"spec\":\"混合口味【8种口味随机5味混装】,整箱10包\",\"event_type\":0,\"is_pre_sale\":0,\"goods_type\":1,\"cat_id_1\":6398,\"cat_id_2\":6469,\"cat_id_3\":6471,\"cat_id_4\":7553}],\"group_order\":{\"group_status\":99,\"customer_num\":1,\"expire_time\":1608534271},\"mall\":{\"id\":\"825762120\",\"logo\":\"http:\\u002F\\u002Ft16img.yangkeduo.com\\u002Fpdd_ims\\u002Fimg_check\\u002Fv2\\u002FFFFFFF469B460320200112174741261\\u002F32f59c0d82fa48ecab13d6816a490d84.png\",\"mall_name\":\"宸邦零食专营店\",\"tag_list\":[{\"logo_url\":\"https:\\u002F\\u002Ft16img.yangkeduo.com\\u002Fmms_static\\u002F2019-12-04\\u002Fea0eaa26-203d-487b-9d7e-9c920c0b8d51.gif\",\"logo_height\":48,\"logo_width\":174}],\"mall_url\":\"mall_page.html?mall_id=825762120&msn=vr5gemiwnkc7ycu7opssstpayy_axbuy&goods_id=198510980415\"},\"price_desc\":\"{\\\"prefix\\\":\\\"实付:\\\",\\\"suffix\\\":\\\"(免运费)\\\",\\\"suffixStyle\\\":0,\\\"prefixStyle\\\":0,\\\"hidePrice\\\":false,\\\"prefixV2\\\":null,\\\"suffixFold\\\":false,\\\"extraInfo\\\":null}\",\"order_status_prompt\":\"交易已取消\",\"activity_type\":0,\"is_super_group\":false,\"tabs_belong\":[\"all\"],\"goods_tags\":[],\"order_tags\":[],\"order_buttons\":[{\"type\":1,\"type_value\":{\"url\":\"goods.html?goods_id=198510980415&show_sku_selector=1&sku_id=742238811466&page_from=100&page_el_sn=99671&goods_id=198510980415&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"click_type\":0},\"brief_prompt\":\"再次拼单\",\"support_version\":\"4.15.0\",\"metric_info\":\"{\\\"page_el_sn\\\":\\\"99671\\\",\\\"goods_id\\\":\\\"198510980415\\\",\\\"sale_status\\\":\\\"0\\\",\\\"growth_tip_scene\\\":\\\"0\\\"}\",\"id\":7000,\"sub_id\":-1},{\"type\":4,\"style\":{\"color\":0,\"click_type\":0},\"brief_prompt\":\"删除订单\",\"support_version\":\"4.15.0\",\"metric_info\":\"{\\\"page_el_sn\\\":\\\"99662\\\"}\",\"id\":17000,\"sub_id\":-1}],\"alert_info\":{\"confirm_receipt\":{\"message\":\"为保障您的售后权益，请收到货确认无误后，再确认收货哦！\"}},\"fp_id\":\"99giVEyZ51ytzQJqoHtU-WVJnwPsQC1W-40stQ9NvLI\",\"payMethod\":[],\"priceDesc\":{\"prefix\":\"实付:\",\"suffix\":\"(免运费)\",\"suffixStyle\":0,\"prefixStyle\":0,\"hidePrice\":false,\"prefixV2\":null,\"suffixFold\":false,\"extraInfo\":null}},{\"type\":1,\"group_id\":\"14994814637\",\"group_order_id\":\"1148375125607382954\",\"address_id\":\"19268609966\",\"shipping_id\":\"85\",\"order_sn\":\"200606-354224375003721\",\"parent_order_sn\":\"\",\"order_amount\":652,\"shipping_amount\":0,\"discount_amount\":0,\"service_amount\":0,\"display_amount\":652,\"used_coupons\":false,\"source_biz_sn\":\"200606-354224375003721\",\"biz_type\":0,\"status\":4,\"group_status\":1,\"order_status\":1,\"pay_status\":2,\"shipping_status\":2,\"comment_status\":3,\"combined_order_status\":4,\"urge_shipment_status\":0,\"shipping_time\":1591496203,\"order_time\":1591428178,\"receive_time\":1592792204,\"tracking_number\":\"YT9187010860441\",\"order_link_url\":\"order.html?order_sn=200606-354224375003721\",\"order_goods\":[{\"goods_id\":\"8848168430\",\"sku_id\":\"247561740270\",\"goods_name\":\"不锈钢强力鸡骨厨房家用剪刀剪骨剪肉多功能多用省力日用剪刀\",\"goods_price\":652,\"goods_number\":1,\"thumb_url\":\"https:\\u002F\\u002Ft00img.yangkeduo.com\\u002Fgoods\\u002Fimages\\u002F2019-05-16\\u002F7096d6a1-8af3-4f86-a107-3c8468ed2c60.jpg\",\"spec\":\"黑色强力鸡骨剪\",\"event_type\":0,\"is_pre_sale\":0,\"goods_type\":1,\"cat_id_1\":16989,\"cat_id_2\":16997,\"cat_id_3\":17054,\"cat_id_4\":17124}],\"group_order\":{\"group_status\":1,\"customer_num\":2,\"expire_time\":1591514064,\"create_at\":1591427664,\"captain_uid\":4249885578,\"group_member_num\":2,\"success_time\":1591428184,\"avatars\":[\"https:\\u002F\\u002Favatar2.pddpic.com\\u002Fa\\u002Fb0ccf5d9d7fad0ac966c2430fbd2ae4a2cbd6cf8-1563017906?imageMogr2\\u002Fthumbnail\\u002F100x\",\"https:\\u002F\\u002Favatar2.pddpic.com\\u002Favatar\\u002Fdefault\\u002F8.png\"]},\"mall\":{\"id\":\"560395019\",\"logo\":\"https:\\u002F\\u002Ft00img.yangkeduo.com\\u002Fgoods\\u002Fimages\\u002F2020-07-03\\u002F03c78269-9ab6-45a5-b213-1735f940e64c_suffix.jpg\",\"mall_name\":\"高美加生活用品\",\"mall_url\":\"mall_page.html?mall_id=560395019&msn=z6i5pwvbvgtdxwapeb7hl5ptwe_axbuy&goods_id=8848168430\"},\"price_desc\":\"{\\\"prefix\\\":\\\"实付:\\\",\\\"suffix\\\":\\\"(免运费)\\\",\\\"suffixStyle\\\":0,\\\"prefixStyle\\\":0,\\\"hidePrice\\\":false,\\\"prefixV2\\\":null,\\\"suffixFold\\\":false,\\\"extraInfo\\\":null}\",\"order_status_prompt\":\"交易成功\",\"activity_type\":33,\"is_super_group\":false,\"tabs_belong\":[\"all\"],\"goods_tags\":[],\"order_tags\":[],\"order_buttons\":[{\"type\":1,\"type_value\":{\"url\":\"goods.html?goods_id=8848168430&show_sku_selector=1&sku_id=247561740270&page_from=100&page_el_sn=99671&goods_id=8848168430&sale_status=0&growth_tip_scene=0\",\"msg\":\"\"},\"style\":{\"color\":0,\"click_type\":0},\"brief_prompt\":\"再次拼单\",\"support_version\":\"4.15.0\",\"metric_info\":\"{\\\"page_el_sn\\\":\\\"99671\\\",\\\"goods_id\\\":\\\"8848168430\\\",\\\"sale_status\\\":\\\"0\\\",\\\"growth_tip_scene\\\":\\\"0\\\"}\",\"id\":7000,\"sub_id\":-1},{\"type\":21,\"type_value\":{\"url\":\"\",\"msg\":\"\",\"path\":\"api\\u002Fasm\\u002Ftrigger\",\"param\":\"{\\\"order_sn\\\":\\\"200606-354224375003721\\\"}\"},\"style\":{\"color\":0,\"click_type\":0},\"brief_prompt\":\"申请退款\",\"support_version\":\"4.70.9\",\"metric_info\":\"{\\\"page_el_sn\\\":\\\"96433\\\"}\",\"id\":13000,\"sub_id\":-1},{\"type\":4,\"style\":{\"color\":0,\"click_type\":0},\"brief_prompt\":\"删除订单\",\"support_version\":\"4.15.0\",\"metric_info\":\"{\\\"page_el_sn\\\":\\\"99662\\\"}\",\"id\":17000,\"sub_id\":-1}],\"alert_info\":{\"confirm_receipt\":{\"message\":\"为保障您的售后权益，请收到货确认无误后，再确认收货哦！\"}},\"extra_info\":{\"pre_render_param\":\"5\"},\"fp_id\":\"UvSuVOo4bePsshddBUIOjq2hGkvRE3LslUxnGGrJka4\",\"payMethod\":[],\"priceDesc\":{\"prefix\":\"实付:\",\"suffix\":\"(免运费)\",\"suffixStyle\":0,\"prefixStyle\":0,\"hidePrice\":false,\"prefixV2\":null,\"suffixFold\":false,\"extraInfo\":null}}]},\"webp\":true,\"isQQPlatform\":false,\"qqWalletPage\":false,\"isLitePreOrderTester\":true,\"isPreRenderAppVer\":\"4.80.0\",\"isNewComment\":false,\"lastOrderSn\":\"200606-354224375003721\",\"showFreshButtons\":true}}";
//        String json = "{\"sentences\":[{\"trans\":\"结果\",\"orig\":\"result\",\"backend\":2},{\"translit\":\"Jiéguǒ\",\"src_translit\":\"riˈzəlt\"}],\"dict\":[{\"pos\":\"名词\",\"terms\":[\"结果\",\"成果\",\"效果\",\"成绩\",\"产物\",\"成效\",\"收获\",\"功\",\"名堂\"],\"entry\":[{\"word\":\"结果\",\"reverse_translation\":[\"result\",\"outcome\",\"consequence\",\"effect\",\"consequent\",\"upshot\"],\"score\":0.67663383},{\"word\":\"成果\",\"reverse_translation\":[\"achievement\",\"result\",\"gain\",\"profit\",\"consequent\",\"sequel\"],\"score\":0.02749503},{\"word\":\"效果\",\"reverse_translation\":[\"effect\",\"result\",\"sound effects\",\"consequent\",\"sequel\"],\"score\":0.011642128},{\"word\":\"成绩\",\"reverse_translation\":[\"score\",\"achievement\",\"result\",\"mark\"],\"score\":0.0039610346},{\"word\":\"产物\",\"reverse_translation\":[\"product\",\"result\",\"outcome\"],\"score\":0.0010999396},{\"word\":\"成效\",\"reverse_translation\":[\"effect\",\"result\"],\"score\":6.074443E-4},{\"word\":\"收获\",\"reverse_translation\":[\"gain\",\"result\",\"acquisition\"],\"score\":5.921267E-5},{\"word\":\"功\",\"reverse_translation\":[\"merit\",\"achievement\",\"meritorious service\",\"accomplishment\",\"exploit\",\"result\"],\"score\":5.7390887E-5},{\"word\":\"名堂\",\"reverse_translation\":[\"variety\",\"result\",\"item\"],\"score\":2.123383E-6}],\"base_form\":\"result\",\"pos_enum\":1},{\"pos\":\"动词\",\"terms\":[\"导致\",\"致使\",\"酿\"],\"entry\":[{\"word\":\"导致\",\"reverse_translation\":[\"lead to\",\"cause\",\"result\",\"bring about\",\"create\"],\"score\":0.45783335},{\"word\":\"致使\",\"reverse_translation\":[\"cause\",\"result\",\"occasion\"],\"score\":8.174057E-4},{\"word\":\"酿\",\"reverse_translation\":[\"brew\",\"ferment\",\"lead to\",\"brew up\",\"result\",\"make wine\"],\"score\":4.6644533E-7}],\"base_form\":\"result\",\"pos_enum\":2}],\"src\":\"en\",\"ld_result\":{\"srclangs\":[\"en\"],\"srclangs_confidences\":[1.0],\"extended_srclangs\":[\"en\"]}}";
//        String json = "[{\"trans\":\"结果\",\"orig\":\"result\",\"backend\":2},{\"translit\":\"Jiéguǒ\",\"src_translit\":\"riˈzəlt\"}]";
        json = format(json);
        System.out.println(json);
        System.out.println(compress(json));
    }
}
