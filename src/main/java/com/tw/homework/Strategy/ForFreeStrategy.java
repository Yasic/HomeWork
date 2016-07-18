package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yasic on 16-7-16.
 */
public class ForFreeStrategy extends BasePromotionStrategy {
    private static final int PRIORITY = 2;
    private static final int FORFREENUMBER = 2;


    public HashMap<String, Format> calculatePromotion(HashMap<String, Format> formatHashMap) {
        List<String> promotionList = getPromotionList();
        for (String item : formatHashMap.keySet()){
            if (promotionList.contains(item)){
                int newProductNumber = formatHashMap.get(item).getNumberScope() / FORFREENUMBER + formatHashMap.get(item).getNumberScope();
                formatHashMap.get(item).setNumberScope(newProductNumber);
                //formatHashMap.get(item).setSaveMoneyScope(0);
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
        promotionList.add("ITEM000003");
        return promotionList;
    }
}
