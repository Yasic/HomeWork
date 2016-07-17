package com.tw.homework.Tactic;

import com.tw.homework.JavaBean.Format;
import com.tw.homework.Util.ProductInfoHelper;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by lichenqiang on 16/7/18.
 */
public class ForFreeStrategyTest {


    @Test
    public void shouldGetDiscountWhenTwo() throws Exception {
        // given
        HashMap<String, Format> givenList = new HashMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getName())
                .setNumberScope(2)
                .setPriceScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getPrice())
                .setUnitScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getUnitType())
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(0)
                .build());
        // when
        HashMap<String, Format> expectList = DiscountStrategy.methodName(givenList);
        // expect to
        HashMap<String, Format> testList = new HashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getName())
                .setNumberScope(3)
                .setPriceScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getPrice())
                .setUnitScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getUnitType())
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(3.0f)
                .build());
        // then
        assertEquals(testList, givenList);
    }


    @Test
    public void shouldNotGetDiscountWhenOne() throws Exception {
        // given
        HashMap<String, Format> givenList = new HashMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getName())
                .setNumberScope(1)
                .setPriceScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getPrice())
                .setUnitScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getUnitType())
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        // when
        HashMap<String, Format> expectList = DiscountStrategy.methodName(givenList);
        // expect to
        HashMap<String, Format> testList = new HashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getName())
                .setNumberScope(1)
                .setPriceScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getPrice())
                .setUnitScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getUnitType())
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0f)
                .build());
        // then
        assertEquals(testList, givenList);
    }


    @Test
    public void shouldGetOnlyOneDiscountWhenFive() throws Exception {
        // given
        HashMap<String, Format> givenList = new HashMap<String, Format>();
        givenList.put("ITEM000001", new Format.Builder()
                .setNameScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getName())
                .setNumberScope(5)
                .setPriceScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getPrice())
                .setUnitScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getUnitType())
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        // when
        HashMap<String, Format> expectList = DiscountStrategy.methodName(givenList);
        // expect to
        HashMap<String, Format> testList = new HashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getName())
                .setNumberScope(5)
                .setPriceScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getPrice())
                .setUnitScope(ProductInfoHelper.getProductInfoList().get("ITEM000001").getUnitType())
                .setTotalMoneyScope(12.0f).setSaveMoneyScope(3.0f)
                .build());
        // then
        assertEquals(testList, givenList);
    }

}