package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.ProductFormat;

import java.util.TreeMap;

/**
 * Created by yasic on 16-7-16.
 */
public interface PromotionStrategyInterface {
    TreeMap<String, ProductFormat> calculatePromotion(TreeMap<String, ProductFormat> formatTreeMap);
}
