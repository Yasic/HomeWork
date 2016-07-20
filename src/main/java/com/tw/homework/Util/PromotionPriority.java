package com.tw.homework.Util;

/**
 * Created by Yasic on 2016/7/20.
 */
public enum PromotionPriority {
    DISCOUNT(1),
    FORFREE(2);

    private int  priority;
    private PromotionPriority(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
