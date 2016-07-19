package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.ProductFormat;
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
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(1)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, ProductFormat> testList = new HashMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
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
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(2)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, ProductFormat> testList = new HashMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
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
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(5)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, ProductFormat> testList = new HashMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(7)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldReturnForFreeTreeMapWhenProductInputIsInPromotionList() throws Exception{
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(5)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, Integer> expectList = builder.getForFreeProductInfo(givenList);
        HashMap<String, Integer> testList = new HashMap<String, Integer>();
        testList.put("ITEM000001", 2);

        assertEquals(testList, expectList);
    }

}