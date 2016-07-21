package com.tw.homework.Util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yasic on 2016/7/21.
 */
public class PromotionPriorityTest {
    @Test
    public void shouldReturnOneWhenGetDiscountPriority() throws Exception {
        Assert.assertEquals(PromotionPriority.DISCOUNT.getPriority(), 1);
    }

    @Test
    public void shouldReturnTwoWhenGetForFreePriority() throws Exception {
        Assert.assertEquals(PromotionPriority.FORFREE.getPriority(), 2);
    }


}