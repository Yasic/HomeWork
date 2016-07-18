package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;

import java.util.TreeMap;

/**
 * Created by yasic on 16-7-16.
 */
public interface PromotionStrategyInterface {

    TreeMap<String, Format> calculatePromotion(TreeMap<String, Format> formatTreeMap);

}
