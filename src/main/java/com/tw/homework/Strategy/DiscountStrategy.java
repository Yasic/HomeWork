package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;

import java.util.HashMap;

/**
 * Created by yasic on 16-7-16.
 */
public class DiscountStrategy extends BasePromotionStrategy {
    private static final int PRIORITY = 1;

    public HashMap<String, Format> calculatePromotion(HashMap<String, Format> formatHashMap) {
        return null;
    }

    public int getPriority() {
        return PRIORITY;
    }
}
