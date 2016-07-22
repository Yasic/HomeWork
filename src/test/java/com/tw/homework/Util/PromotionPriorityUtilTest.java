package com.tw.homework.Util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yasic on 2016/7/21.
 */
public class PromotionPriorityUtilTest {
    @Test
    public void shouldReturnOneWhenGetDiscountPriority() throws Exception {
        Assert.assertEquals(PromotionPriorityUtil.DISCOUNT.getPriority(), 1);
    }

    @Test
    public void shouldReturnTwoWhenGetFullFreePriority() throws Exception {
        Assert.assertEquals(PromotionPriorityUtil.FULLFREE.getPriority(), 2);
    }


}