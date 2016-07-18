package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yasic on 2016/7/18.
 */
public class ForFreeStrategyTest {
    @Test
    public void shouldReturnTwoWhenGetPriority() throws Exception {
        ForFreeStrategy forFreeStrategy = new ForFreeStrategy();
        Assert.assertTrue(forFreeStrategy.getPriority() == 2);
    }

    @Test
    public void shouldNotGetDiscountWhenOne() throws Exception {
        // given
        HashMap<String, Format> givenList = new HashMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(1)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        // when
        DiscountStrategy builder = new DiscountStrategy();
        HashMap<String, Format> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, Format> testList = new HashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(1)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        // then
        assertEquals(testList, expectList);
    }


    @Test
    public void shouldGetDiscountWhenTwo() throws Exception {
        // given
        HashMap<String, Format> givenList = new HashMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(2)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(0)
                .build());
        // when
        DiscountStrategy builder = new DiscountStrategy();
        HashMap<String, Format> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, Format> testList = new HashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(3)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(3.0f)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenFive() throws Exception {
        // given
        HashMap<String, Format> givenList = new HashMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(5)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        // when
        DiscountStrategy builder = new DiscountStrategy();
        HashMap<String, Format> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, Format> testList = new HashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(5)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(12.0f).setSaveMoneyScope(3.0f)
                .build());
        // then
        assertEquals(testList, expectList);
    }

}