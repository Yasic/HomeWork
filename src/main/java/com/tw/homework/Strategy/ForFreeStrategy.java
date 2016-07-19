package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.ProductFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by yasic on 16-7-16.
 */
public class ForFreeStrategy extends BasePromotionStrategy {
    private static final int PRIORITY = 2;
    private static final int FORFREENUMBER = 2;


    public TreeMap<String, ProductFormat> calculatePromotion(TreeMap<String, ProductFormat> formatHashMap) {
        List<String> promotionList = getPromotionList();
        for (String item : formatHashMap.keySet()){
            if (formatHashMap.get(item).getPromotionFlag() <= PRIORITY && promotionList.contains(item)){
                int newProductNumber = formatHashMap.get(item).getNumberScope() / FORFREENUMBER + formatHashMap.get(item).getNumberScope();
                formatHashMap.get(item).setNumberScope(newProductNumber);
                formatHashMap.get(item).setPromotionFlag(PRIORITY);
            }
        }
        return formatHashMap;
    }

    public TreeMap<String, Integer> getForFreeProductInfo(TreeMap<String, ProductFormat> productFormatTreeMap) {
        List<String> promotionList = getPromotionList();
        TreeMap<String, Integer> resultList = new TreeMap<String, Integer>();
        for (String item : productFormatTreeMap.keySet()){
            if (productFormatTreeMap.get(item).getPromotionFlag() <= PRIORITY && promotionList.contains(item)){
                int forFreeNumber = productFormatTreeMap.get(item).getNumberScope() / FORFREENUMBER;
                resultList.put(item, forFreeNumber);
            }
        }
        return resultList;
    }

    public int getPriority() {
        return PRIORITY;
    }

    public List<String> getPromotionList() {
        List<String> promotionList = new ArrayList<String>();
        promotionList.add("ITEM000001");
        promotionList.add("ITEM000003");
        return promotionList;
    }
}
