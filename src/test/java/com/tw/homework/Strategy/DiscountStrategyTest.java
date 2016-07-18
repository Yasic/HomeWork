package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.Format;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

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
    public void shouldGetDiscountWhen1ItemInList() throws Exception {
        // given
        HashMap<String, Format> givenList = new HashMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(1)
                .setPriceScope(3.0f)
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
                .setPriceScope(3.0f)
                .setUnitScope(" ")
                .setTotalMoneyScope(2.85f).setSaveMoneyScope(0.15f)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhen3ItemInList() throws Exception {
        // given
        HashMap<String, Format> givenList = new HashMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(" ")
                .setNumberScope(3)
                .setPriceScope(0)
                .setUnitScope(" ")
                .setTotalMoneyScope(9.0f).setSaveMoneyScope(0)
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
                .setTotalMoneyScope(8.55f).setSaveMoneyScope(0.45f)
                .build());
        // then
        assertEquals(testList, expectList);
    }
}