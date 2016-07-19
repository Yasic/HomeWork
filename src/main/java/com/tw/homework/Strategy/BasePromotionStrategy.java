package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.ProductFormat;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by Yasic on 2016/7/18.
 */
public abstract class BasePromotionStrategy implements PromotionStrategyInterface, Comparable {
    private int priority;

    public TreeMap<String, ProductFormat> calculatePromotion(TreeMap<String, ProductFormat> formatHashMap) {
        return formatHashMap;
    }

    public abstract int getPriority();

    public abstract List<String> getPromotionList();

    public int compareTo(Object o) {
        return -(this.priority - ((BasePromotionStrategy)o).getPriority());
    }
}
