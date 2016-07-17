package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;

import java.util.HashMap;

/**
 * Created by yasic on 16-7-16.
 */
public interface PromotionStrategyInterface {

    HashMap<String, Format> calculatePromotion(HashMap<String, Format> formatHashMap);

}
