package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.ProductFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by yasic on 16-7-16.
 */
public class DiscountStrategy extends BasePromotionStrategy {
    private static final int PRIORITY = 1;
    private static final float DISCOUNT = 0.95f;

    @Override
    public TreeMap<String, ProductFormat> calculatePromotion(TreeMap<String, ProductFormat> formatHashMap) {
        List<String> promotionList = getPromotionList();
        java.text.DecimalFormat decimalFormat =  new java.text.DecimalFormat("0.00#");
        for (String item : formatHashMap.keySet()){
            if (formatHashMap.get(item).getPromotionFlag() <= PRIORITY && promotionList.contains(item)){
                float originTotalMoney = formatHashMap.get(item).getTotalMoneyScope();
                BigDecimal b1 = new BigDecimal(originTotalMoney);
                BigDecimal b2 = new BigDecimal(DISCOUNT);
                float ss = b1.subtract(b1.multiply(b2)).floatValue();

                formatHashMap.get(item).setTotalMoneyScope(originTotalMoney * DISCOUNT);
                formatHashMap.get(item).setSaveMoneyScope(Float.parseFloat(decimalFormat.format(ss)));
                formatHashMap.get(item).setPromotionFlag(PRIORITY);
            }
        }
        return formatHashMap;
    }

    public int getPriority() {
        return PRIORITY;
    }

    public List<String> getPromotionList() {
        List<String> promotionList = new ArrayList<String>();
        promotionList.add("ITEM000001");
        promotionList.add("ITEM000005");
        return promotionList;
    }
}
