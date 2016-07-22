package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.FormatData;

import java.util.TreeMap;

/**
 * Created by yasic on 16-7-16.
 */
public interface PromotionStrategyInterface {
    TreeMap<String, FormatData> calculatePromotion(TreeMap<String, FormatData> formatTreeMap);
}
