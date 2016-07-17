package com.tw.homework.Strategy;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yasic on 2016/7/18.
 */
public class ForFreeStrategyTest {
    @Test
    public void shouldReturnTwoWhenGetPriority() throws Exception {
        ForFreeStrategy forFreeStrategy = new ForFreeStrategy();
        Assert.assertTrue(forFreeStrategy.getPriority() == 2);
    }

}