package com.tw.homework.Util;

/**
 * Created by Yasic on 2016/7/20.
 */
public enum PromotionPriorityUtil {
    DISCOUNT(1),
    FULLFREE(2);

    private int  priority;
    private PromotionPriorityUtil(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
