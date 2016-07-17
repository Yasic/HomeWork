package com.tw.homework.Strategy;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yasic on 2016/7/18.
 */
public class DiscountStrategyTest {
    @Test
    public void shouldReturnOneWhenGetPriority() throws Exception {
        DiscountStrategy discountStrategy = new DiscountStrategy();
        Assert.assertTrue(discountStrategy.getPriority() == 1);
    }
}