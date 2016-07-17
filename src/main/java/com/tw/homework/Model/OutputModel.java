package com.tw.homework.Model;

import com.tw.homework.JavaBean.Format;
import com.tw.homework.Strategy.BasePromotionStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Yasic on 2016/7/17.
 */
public class OutputModel {
    private static final String HEADINFO = "***<没钱赚商店>购物清单***\n";

    public String getFormatProductArrray(LinkedHashMap<String, Format> formatHashMap) {
        StringBuffer productArrayBuffer = new StringBuffer();
        for (String barcode : formatHashMap.keySet()) {
            productArrayBuffer.append("名称：")
                    .append(formatHashMap.get(barcode).getNameScope())
                    .append("，")
                    .append("数量：")
                    .append(formatHashMap.get(barcode).getNumberScope())
                    .append(formatHashMap.get(barcode).getUnitScope())
                    .append("，")
                    .append("单价：")
                    .append(formatHashMap.get(barcode).getPriceScope())
                    .append("(元)")
                    .append("，")
                    .append("小计：")
                    .append(formatHashMap.get(barcode).getTotalMoneyScope())
                    .append("(元)")
                    .append("\n");
        }
        return productArrayBuffer.toString();
    }

    public String getFormatProductArrray(LinkedHashMap<String, Format> formatHashMap, List<BasePromotionStrategy> promotionList) {
        StringBuffer productArrayBuffer = new StringBuffer();
        Collections.sort(promotionList);
        for (String barcode : formatHashMap.keySet()) {
            productArrayBuffer.append("名称：")
                    .append(formatHashMap.get(barcode).getNameScope())
                    .append("，")
                    .append("数量：")
                    .append(formatHashMap.get(barcode).getNumberScope())
                    .append(formatHashMap.get(barcode).getUnitScope())
                    .append("，")
                    .append("单价：")
                    .append(formatHashMap.get(barcode).getPriceScope())
                    .append("(元)")
                    .append("，")
                    .append("小计：")
                    .append(formatHashMap.get(barcode).getTotalMoneyScope())
                    .append("(元)")
                    .append("\n");
        }
        return productArrayBuffer.toString();
    }

    public String getFormatString(HashMap<String, Format> formatHashMap){
        return "***<没钱赚商店>购物清单***\n" +
                "\n" +
                "名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n" +
                "\n" +
                "名称：羽毛球，数量：6个，单价：1.00(元)，小计：4.00(元)\n" +
                "\n" +
                "名称：苹果，数量：2斤，单价：5.50(元)，小计：10.45(元)，节省0.55(元)\n" +
                "\n" +
                "----------------------\n" +
                "\n" +
                "买二赠一商品：\n" +
                "\n" +
                "名称：可口可乐，数量：1瓶\n" +
                "\n" +
                "名称：羽毛球，数量：2个\n" +
                "\n" +
                "----------------------\n" +
                "\n" +
                "总计：20.45(元)\n" +
                "\n" +
                "节省：5.55(元)\n" +
                "\n" +
                "**********************";
    }
}
