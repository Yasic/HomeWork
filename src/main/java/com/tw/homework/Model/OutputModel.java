package com.tw.homework.Model;

import com.tw.homework.JavaBean.ProductFormat;
import com.tw.homework.Strategy.BasePromotionStrategy;
import com.tw.homework.Strategy.DiscountStrategy;
import com.tw.homework.Strategy.ForFreeStrategy;

import java.util.*;

/**
 * Created by Yasic on 2016/7/17.
 */
public class OutputModel {
    private static final String HEADINFO = "***<没钱赚商店>购物清单***\n";
    private static final String PARTINGLINE = "----------------";

    public String getProductArrayInfoScope(TreeMap<String, ProductFormat> formatHashMap) {
        TreeSet<BasePromotionStrategy> promotionStrategyTreeSet = new TreeSet<BasePromotionStrategy>();
        promotionStrategyTreeSet.add(new ForFreeStrategy());
        promotionStrategyTreeSet.add(new DiscountStrategy());
        Iterator iterator = promotionStrategyTreeSet.iterator();

        while (iterator.hasNext()){
            BasePromotionStrategy basePromotionStrategy = (BasePromotionStrategy) iterator.next();
            basePromotionStrategy.calculatePromotion(formatHashMap);
        }

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
                    .append("(元)");
            if (formatHashMap.get(barcode).getPromotionFlag() == 1){
                productArrayBuffer.append("，")
                        .append("节省")
                        .append(formatHashMap.get(barcode).getSaveMoneyScope())
                        .append("(元)");
            }
            productArrayBuffer.append("\n");
        }
        System.out.println(productArrayBuffer.toString());
        return productArrayBuffer.toString();
    }

    public String getProductArrayInfoScope(TreeMap<String, ProductFormat> formatHashMap, TreeSet<BasePromotionStrategy> promotionSet) {
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

    public String getForFreeInfoScope(TreeMap<String, ProductFormat> formatHashMap) {
        TreeSet<BasePromotionStrategy> promotionStrategyTreeSet = new TreeSet<BasePromotionStrategy>();
        promotionStrategyTreeSet.add(new ForFreeStrategy());
        promotionStrategyTreeSet.add(new DiscountStrategy());
        Iterator iterator = promotionStrategyTreeSet.iterator();



        return "";
    }

    public String getFormatString(TreeMap<String, ProductFormat> formatHashMap){
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
