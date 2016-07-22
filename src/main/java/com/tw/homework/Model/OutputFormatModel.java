package com.tw.homework.Model;

import com.tw.homework.JavaBean.FormatData;
import com.tw.homework.Strategy.BasePromotionStrategy;
import com.tw.homework.Strategy.DiscountStrategy;
import com.tw.homework.Strategy.FullFreeStrategy;
import com.tw.homework.Util.ProductInfoUtil;
import com.tw.homework.Util.StaticStringScopeUtil;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Yasic on 2016/7/17.
 */
public class OutputFormatModel {
    private TreeMap<String, FormatData> productFormatTreeMap;
    private TreeSet<BasePromotionStrategy> strategyTreeSet;

    private OutputFormatModel() {}

    public static class Builder{
        private OutputFormatModel outputFormatModel = new OutputFormatModel();

        public Builder setStrategyTreeSet(TreeSet<BasePromotionStrategy> strategyTreeSet) {
            outputFormatModel.strategyTreeSet = strategyTreeSet;
            return this;
        }

        public OutputFormatModel build(){
            return outputFormatModel;
        }
    }

    public String getFormatOutput(TreeMap<String, FormatData> productFormatTreeMap){
        setProductFormatTreeMap(productFormatTreeMap);
        String totalString = StaticStringScopeUtil.HEADTITLETEXT
                + getProductArrayInfoScope(getProductFormatTreeMap())
                + getFullFreeInfoScope(getProductFormatTreeMap())
                + StaticStringScopeUtil.PARTINGLINETEXT
                + getMoneyInfoScope(getProductFormatTreeMap());
        return totalString;
    }

    public String getFormatOutput(TreeMap<String, FormatData> productFormatTreeMap, TreeSet<BasePromotionStrategy> strategyTreeSet) {
        setStrategyTreeSet(strategyTreeSet);
        return getFormatOutput(productFormatTreeMap);
    }

    private void setProductFormatTreeMap(TreeMap<String, FormatData> productFormatTreeMap) {
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
            promotionStrategyTreeSet.add(new FullFreeStrategy());
            promotionStrategyTreeSet.add(new DiscountStrategy());
            return promotionStrategyTreeSet;
        }
    }

    private TreeMap<String, FormatData> getProductFormatTreeMap() {
        TreeMap<String, FormatData> cloneMap = new TreeMap<String, FormatData>();
        for (String key: productFormatTreeMap.keySet()) {
            cloneMap.put(key, new FormatData.Builder().setBarcode(key)
                    .setNumberScope(productFormatTreeMap.get(key).getNumberScope())
                    .setTotalMoneyScope(productFormatTreeMap.get(key).getTotalMoneyScope())
                    .setSaveMoneyScope(productFormatTreeMap.get(key).getSaveMoneyScope())
                    .build());
        }
        return cloneMap;
    }

    private String getProductArrayInfoScope(TreeMap<String, FormatData> productFormatTreeMap) {
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

    private String getFullFreeInfoScope(TreeMap<String, FormatData> formatHashMap) {
        FullFreeStrategy fullFreeStrategy = new FullFreeStrategy();
        TreeMap<String, Integer> forFreeTreeMap = fullFreeStrategy.getFullFreeProductInfo(formatHashMap);
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
            return StaticStringScopeUtil.PARTINGLINETEXT + StaticStringScopeUtil.FULLFREEHEADTITLETEXT + result.toString();
        }
    }

    private String getMoneyInfoScope(TreeMap<String, FormatData> productFormatTreeMap){
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
