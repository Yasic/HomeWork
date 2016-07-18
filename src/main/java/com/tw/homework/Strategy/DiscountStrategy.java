package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;

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
    public TreeMap<String, Format> calculatePromotion(TreeMap<String, Format> formatHashMap) {
        java.text.DecimalFormat decimalFormat =new   java.text.DecimalFormat("#.00");
        List<String> promotionList = getPromotionList();
        for (String item : formatHashMap.keySet()){
            if (formatHashMap.get(item).getPromotionFlag() <= PRIORITY && promotionList.contains(item)){
                float originTotalMoney = formatHashMap.get(item).getTotalMoneyScope();
                formatHashMap.get(item).setTotalMoneyScope(Float.parseFloat(decimalFormat.format(originTotalMoney * DISCOUNT)));
                formatHashMap.get(item).setSaveMoneyScope(Float.parseFloat(decimalFormat.format(originTotalMoney - originTotalMoney * DISCOUNT)));
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
