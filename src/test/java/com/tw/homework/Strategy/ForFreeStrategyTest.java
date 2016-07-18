package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

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
        TreeMap<String, Format> givenList = new TreeMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(1)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, Format> expectList = builder.calculatePromotion(givenList);
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
        TreeMap<String, Format> givenList = new TreeMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(2)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, Format> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, Format> testList = new HashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(3)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(0)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenFive() throws Exception {
        // given
        TreeMap<String, Format> givenList = new TreeMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(5)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, Format> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, Format> testList = new HashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(7)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        // then
        assertEquals(testList, expectList);
    }

}