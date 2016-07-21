package com.tw.homework.Model;

import com.tw.homework.JavaBean.ProductFormat;
import com.tw.homework.Strategy.BasePromotionStrategy;
import com.tw.homework.Strategy.DiscountStrategy;
import com.tw.homework.Strategy.ForFreeStrategy;
import com.tw.homework.Util.ProductInfoUtil;
import com.tw.homework.Util.StaticStringScopeUtil;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Yasic on 2016/7/17.
 */
public class OutputModel {
    private TreeMap<String, ProductFormat> productFormatTreeMap;
    private TreeSet<BasePromotionStrategy> strategyTreeSet;

    private OutputModel() {}

    public static class Builder{
        private OutputModel outputModel = new OutputModel();

        public Builder setStrategyTreeSet(TreeSet<BasePromotionStrategy> strategyTreeSet) {
            outputModel.strategyTreeSet = strategyTreeSet;
            return this;
        }

        public OutputModel build(){
            return outputModel;
        }
    }

    public String getFormatOutput(TreeMap<String, ProductFormat> productFormatTreeMap){
        setProductFormatTreeMap(productFormatTreeMap);
        String totalString = StaticStringScopeUtil.HEADTITLETEXT
                + getProductArrayInfoScope(getProductFormatTreeMap())
                + getForFreeInfoScope(getProductFormatTreeMap())
                + StaticStringScopeUtil.PARTINGLINETEXT
                + getMoneyInfoScope(getProductFormatTreeMap());
        return totalString;
    }

    public String getFormatOutput(TreeMap<String, ProductFormat> productFormatTreeMap, TreeSet<BasePromotionStrategy> strategyTreeSet) {
        setStrategyTreeSet(strategyTreeSet);
        return getFormatOutput(productFormatTreeMap);
    }

    private void setProductFormatTreeMap(TreeMap<String, ProductFormat> productFormatTreeMap) {
        this.productFormatTreeMap = productFormatTreeMap;
    }

    private void setStrategyTreeSet(TreeSet<BasePromotionStrategy> strategyTreeSet) {
        this.strategyTreeSet = strategyTreeSet;
    }

    private TreeSet<BasePromotionStrategy> getStrategyTreeSet() {
        if (strategyTreeSet != null){
            return strategyTreeSet;
        }
        else {
            TreeSet<BasePromotionStrategy> promotionStrategyTreeSet = new TreeSet<BasePromotionStrategy>();
            promotionStrategyTreeSet.add(new ForFreeStrategy());
            promotionStrategyTreeSet.add(new DiscountStrategy());
            return promotionStrategyTreeSet;
        }
    }

    private TreeMap<String, ProductFormat> getProductFormatTreeMap() {
        TreeMap<String, ProductFormat> cloneMap = new TreeMap<String, ProductFormat>();
        for (String key: productFormatTreeMap.keySet()) {
            cloneMap.put(key, new ProductFormat.Builder().setBarcode(key)
                    .setNumberScope(productFormatTreeMap.get(key).getNumberScope())
                    .setTotalMoneyScope(productFormatTreeMap.get(key).getTotalMoneyScope())
                    .setSaveMoneyScope(productFormatTreeMap.get(key).getSaveMoneyScope())
                    .build());
        }
        return cloneMap;
    }

    private String getProductArrayInfoScope(TreeMap<String, ProductFormat> productFormatTreeMap) {
        java.text.DecimalFormat decimalFormat =  new java.text.DecimalFormat("0.00#");

        Iterator iterator = getStrategyTreeSet().iterator();
        while (iterator.hasNext()){
            BasePromotionStrategy basePromotionStrategy = (BasePromotionStrategy) iterator.next();
            basePromotionStrategy.calculatePromotion(productFormatTreeMap);
        }

        StringBuffer productArrayBuffer = new StringBuffer();
        for (String barcode : productFormatTreeMap.keySet()) {
            productArrayBuffer.append(StaticStringScopeUtil.NAMETEXT)
                    .append(ProductInfoUtil.getInstance().getProductInfoList().get(productFormatTreeMap.get(barcode).getBarcode()).getName())
                    .append("，")
                    .append(StaticStringScopeUtil.NUMBERTEXT)
                    .append(productFormatTreeMap.get(barcode).getNumberScope())
                    .append(ProductInfoUtil.getInstance().getProductInfoList().get(productFormatTreeMap.get(barcode).getBarcode()).getUnitType())
                    .append("，")
                    .append(StaticStringScopeUtil.PRICETEXT)
                    .append(decimalFormat.format(ProductInfoUtil.getInstance().getProductInfoList().get(productFormatTreeMap.get(barcode).getBarcode()).getPrice()))
                    .append(StaticStringScopeUtil.MONEYUNITTEXT)
                    .append("，")
                    .append(StaticStringScopeUtil.SUBTOTALTEXT)
                    .append(decimalFormat.format(productFormatTreeMap.get(barcode).getTotalMoneyScope()))
                    .append(StaticStringScopeUtil.MONEYUNITTEXT);
            if (productFormatTreeMap.get(barcode).getPromotionPriority() == 1){
                productArrayBuffer.append("，")
                        .append(StaticStringScopeUtil.SAVETEXT)
                        .append(decimalFormat.format(productFormatTreeMap.get(barcode).getSaveMoneyScope()))
                        .append(StaticStringScopeUtil.MONEYUNITTEXT);
            }
            productArrayBuffer.append("\n");
        }
        return productArrayBuffer.toString();
    }

    private String getForFreeInfoScope(TreeMap<String, ProductFormat> formatHashMap) {
        ForFreeStrategy forFreeStrategy = new ForFreeStrategy();
        TreeMap<String, Integer> forFreeTreeMap = forFreeStrategy.getForFreeProductInfo(formatHashMap);
        StringBuffer result = new StringBuffer();
        for (String item : forFreeTreeMap.keySet()) {
            result.append(StaticStringScopeUtil.NAMETEXT)
                    .append(ProductInfoUtil.getInstance().getProductInfoList().get(item).getName())
                    .append("，")
                    .append(StaticStringScopeUtil.NUMBERTEXT)
                    .append(forFreeTreeMap.get(item))
                    .append(ProductInfoUtil.getInstance().getProductInfoList().get(item).getUnitType())
                    .append("\n");
        }
        if (result.toString().equals("")){
            return "";
        }
        else {
            return StaticStringScopeUtil.PARTINGLINETEXT + StaticStringScopeUtil.FORFREEHEADTITLETEXT + result.toString();
        }
    }

    private String getMoneyInfoScope(TreeMap<String, ProductFormat> productFormatTreeMap){
        java.text.DecimalFormat decimalFormat =  new java.text.DecimalFormat("0.00#");
        Iterator iterator = getStrategyTreeSet().iterator();
        while (iterator.hasNext()){
            BasePromotionStrategy basePromotionStrategy = (BasePromotionStrategy) iterator.next();
            basePromotionStrategy.calculatePromotion(productFormatTreeMap);
        }

        float totalMoney = 0;
        float savedMoney = 0;

        for (String barcode : productFormatTreeMap.keySet()) {
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
        result.append(StaticStringScopeUtil.TOTALTEXT)
                .append(decimalFormat.format(totalMoney))
                .append(StaticStringScopeUtil.MONEYUNITTEXT)
                .append("\n");
        if (savedMoney != 0){
            result.append(StaticStringScopeUtil.SAVETEXT)
                    .append("：")
                    .append(decimalFormat.format(savedMoney))
                    .append(StaticStringScopeUtil.MONEYUNITTEXT)
                    .append("\n");
        }
        return result.toString();
    }
}
