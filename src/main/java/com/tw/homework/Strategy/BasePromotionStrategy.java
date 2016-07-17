package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;

import java.util.HashMap;

/**
 * Created by Yasic on 2016/7/18.
 */
public abstract class BasePromotionStrategy implements PromotionStrategyInterface, Comparable {
    private int priority;

    public HashMap<String, Format> calculatePromotion(HashMap<String, Format> formatHashMap) {
        return null;
    }

    public abstract int getPriority();

    public int compareTo(Object o) {
        return (this.priority - ((BasePromotionStrategy)o).getPriority());
    }
}
