package com.tw.homework.Model;

import com.tw.homework.JavaBean.ProductFormat;
import com.tw.homework.Strategy.BasePromotionStrategy;
import com.tw.homework.Strategy.DiscountStrategy;
import com.tw.homework.Strategy.ForFreeStrategy;
import com.tw.homework.Util.ProductInfoHelper;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Yasic on 2016/7/17.
 */
public class OutputModel {
    private static final String HEADTITLE = "***<没钱赚商店>购物清单***\n";
    private static final String PARTINGLINE = "----------------------\n";
    private static final String FORFREEHEADTITLE = "买二赠一商品：\n";
    private TreeMap<String, ProductFormat> productFormatTreeMap;

    public void setProductFormatTreeMap(TreeMap<String, ProductFormat> productFormatTreeMap) {
        this.productFormatTreeMap = productFormatTreeMap;
    }

    public TreeMap<String, ProductFormat> getProductFormatTreeMap() {
        TreeMap<String, ProductFormat> cloneMap = new TreeMap<String, ProductFormat>();
        for (String key: productFormatTreeMap.keySet()) {
            cloneMap.put(key, new ProductFormat.Builder().setNameScope(productFormatTreeMap.get(key).getNameScope())
                    .setNumberScope(productFormatTreeMap.get(key).getNumberScope())
                    .setPriceScope(productFormatTreeMap.get(key).getPriceScope())
                    .setUnitScope(productFormatTreeMap.get(key).getUnitScope())
                    .setTotalMoneyScope(productFormatTreeMap.get(key).getTotalMoneyScope())
                    .setSaveMoneyScope(productFormatTreeMap.get(key).getSaveMoneyScope())
                    .build());
        }
        return cloneMap;
    }

    public String getProductArrayInfoScope(TreeMap<String, ProductFormat> productFormatTreeMap) {
        TreeSet<BasePromotionStrategy> promotionStrategyTreeSet = new TreeSet<BasePromotionStrategy>();
        promotionStrategyTreeSet.add(new ForFreeStrategy());
        promotionStrategyTreeSet.add(new DiscountStrategy());
        Iterator iterator = promotionStrategyTreeSet.iterator();

        while (iterator.hasNext()){
            BasePromotionStrategy basePromotionStrategy = (BasePromotionStrategy) iterator.next();
            basePromotionStrategy.calculatePromotion(productFormatTreeMap);
        }

        StringBuffer productArrayBuffer = new StringBuffer();
        for (String barcode : productFormatTreeMap.keySet()) {
            productArrayBuffer.append("名称：")
                    .append(productFormatTreeMap.get(barcode).getNameScope())
                    .append("，")
                    .append("数量：")
                    .append(productFormatTreeMap.get(barcode).getNumberScope())
                    .append(productFormatTreeMap.get(barcode).getUnitScope())
                    .append("，")
                    .append("单价：")
                    .append(productFormatTreeMap.get(barcode).getPriceScope())
                    .append("(元)")
                    .append("，")
                    .append("小计：")
                    .append(productFormatTreeMap.get(barcode).getTotalMoneyScope())
                    .append("(元)");
            if (productFormatTreeMap.get(barcode).getPromotionFlag() == 1){
                productArrayBuffer.append("，")
                        .append("节省")
                        .append(productFormatTreeMap.get(barcode).getSaveMoneyScope())
                        .append("(元)");
            }
            productArrayBuffer.append("\n");
        }
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
        ForFreeStrategy forFreeStrategy = new ForFreeStrategy();
        TreeMap<String, Integer> forFreeTreeMap = forFreeStrategy.getForFreeProductInfo(formatHashMap);
        StringBuffer result = new StringBuffer();
        for (String item : forFreeTreeMap.keySet()) {
            result.append("名称：")
                    .append(ProductInfoHelper.getInstance().getProductInfoList().get(item).getName())
                    .append("，数量：")
                    .append(forFreeTreeMap.get(item))
                    .append(ProductInfoHelper.getInstance().getProductInfoList().get(item).getUnitType())
                    .append("\n");
        }
        if (result.toString().equals("")){
            return result.toString();
        }
        else {
            return PARTINGLINE + FORFREEHEADTITLE + result.toString();
        }
    }

    public String getMoneyInfoScope(TreeMap<String, ProductFormat> productFormatTreeMap){
        TreeSet<BasePromotionStrategy> promotionStrategyTreeSet = new TreeSet<BasePromotionStrategy>();
        promotionStrategyTreeSet.add(new ForFreeStrategy());
        promotionStrategyTreeSet.add(new DiscountStrategy());
        Iterator iterator = promotionStrategyTreeSet.iterator();

        while (iterator.hasNext()){
            BasePromotionStrategy basePromotionStrategy = (BasePromotionStrategy) iterator.next();
            basePromotionStrategy.calculatePromotion(productFormatTreeMap);
        }

        float totalMoney = 0;
        float savedMoney = 0;

        for (String barcode : productFormatTreeMap.keySet()) {
            //System.out.println(Float.toString(productFormatTreeMap.get(barcode).getTotalMoneyScope()));
            //System.out.println(Float.toString(productFormatTreeMap.get(barcode).getSaveMoneyScope()));
            BigDecimal b1 = new BigDecimal(Float.toString(productFormatTreeMap.get(barcode).getTotalMoneyScope()));
            BigDecimal b2 = new BigDecimal(Float.toString(totalMoney));
            float ss1 = b1.add(b2).floatValue();
            BigDecimal b3 = new BigDecimal(Float.toString(productFormatTreeMap.get(barcode).getSaveMoneyScope()));
            BigDecimal b4 = new BigDecimal(Float.toString(savedMoney));
            float ss2 = b3.add(b4) .floatValue();
            totalMoney = ss1;
            savedMoney = ss2;
        }
        StringBuffer result = new StringBuffer();
        result.append("总计：")
                .append(totalMoney)
                .append("(元)")
                .append("\n");
        if (savedMoney != 0){
            result.append("节省：")
                    .append(savedMoney)
                    .append("(元)")
                    .append("\n");
        }
        return result.toString();
    }

    public String getFormatString(TreeMap<String, ProductFormat> formatHashMap){
        setProductFormatTreeMap(formatHashMap);
        String totalString = HEADTITLE
                + getProductArrayInfoScope(getProductFormatTreeMap())
                + getForFreeInfoScope(getProductFormatTreeMap())
                + PARTINGLINE
                + getMoneyInfoScope(getProductFormatTreeMap());
        return totalString;
    }
}
