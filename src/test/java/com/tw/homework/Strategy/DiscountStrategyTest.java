package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.ProductFormat;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Yasic on 2016/7/18.
 */
public class DiscountStrategyTest {

    @Test
    public void shouldReturnOneWhenGetPriority() throws Exception {
        DiscountStrategy discountStrategy = new DiscountStrategy();
        Assert.assertTrue(discountStrategy.getPriority() == 1);
    }

    @Test
    public void shouldGetDiscountWhenOneItemInList() throws Exception {
        // given
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(1)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        // when
        DiscountStrategy builder = new DiscountStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, ProductFormat> testList = new TreeMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(1)
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(2.85f).setSaveMoneyScope(0.15f)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenThreeItemInList() throws Exception {
        // given
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(3)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(9.0f).setSaveMoneyScope(0)
                .build());
        // when
        DiscountStrategy builder = new DiscountStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, ProductFormat> testList = new HashMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(3)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(8.55f).setSaveMoneyScope(0.45f)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldReturnDiscountPromotionList() {
        // given
        DiscountStrategy discountStrategy = new DiscountStrategy();
        HashMap<String, ProductFormat> givenList = new HashMap<String, ProductFormat>();
        // when
        List<String> expectList = discountStrategy.getPromotionList();
        List<String> testList = Arrays.asList("ITEM000001", "ITEM000005");
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldNotReturnDiscountPromotionList() {
        // given
        DiscountStrategy discountStrategy = new DiscountStrategy();
        HashMap<String, ProductFormat> givenList = new HashMap<String, ProductFormat>();
        // when
        List<String> expectList = discountStrategy.getPromotionList();
        List<String> testList = Arrays.asList(" ");
        // then
        assertNotEquals(testList, expectList);
    }

}