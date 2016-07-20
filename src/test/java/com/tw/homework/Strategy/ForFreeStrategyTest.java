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
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, ProductFormat> testList = new HashMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
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
                .setBarcode("ITEM000001")
                .setNumberScope(2)
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, ProductFormat> testList = new TreeMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(3)
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(3)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenFive() throws Exception {
        // given
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, ProductFormat> testList = new TreeMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(7)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(6)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenNight() throws Exception {
        // given
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(8)
                .setTotalMoneyScope(24.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, ProductFormat> testList = new TreeMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(12)
                .setTotalMoneyScope(24.0f).setSaveMoneyScope(12)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenNine() throws Exception {
        // given
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(9)
                .setTotalMoneyScope(27.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, ProductFormat> testList = new TreeMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(13)
                .setTotalMoneyScope(27.0f).setSaveMoneyScope(12)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldNotGetDiscountWhenBarcodeDifferent() throws Exception {
        // given
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000003")
                .setNumberScope(8)
                .setTotalMoneyScope(24.0f).setSaveMoneyScope(0)
                .build());
        // when
        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, ProductFormat> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, ProductFormat> testList = new TreeMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(12)
                .setTotalMoneyScope(24.0f).setSaveMoneyScope(12)
                .build());
        // then
        assertNotEquals(testList, expectList);
    }


    @Test
    public void shouldReturnForFreeTreeMapWhenProductInputIsInPromotionList() throws Exception{
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, Integer> expectList = builder.getForFreeProductInfo(givenList);
        HashMap<String, Integer> testList = new HashMap<String, Integer>();
        testList.put("ITEM000001", 2);

        assertEquals(testList, expectList);
    }

    @Test
    public void shouldReturnNullWhenProductInputIsNotInPromotionList() throws Exception{
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000005", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, Integer> expectList = builder.getForFreeProductInfo(givenList);
        HashMap<String, Integer> testList = new HashMap<String, Integer>();

        assertEquals(testList, expectList);
    }

    @Test
    public void shouldReturnFroFreePromotionList() {
        // given
        ForFreeStrategy forFreeStrategy = new ForFreeStrategy();
        HashMap<String, ProductFormat> givenList = new HashMap<String, ProductFormat>();
        // when
        List<String> expectList = forFreeStrategy.getPromotionList();
        List<String> testList = Arrays.asList("ITEM000001", "ITEM000003");
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldNotReturnForFreePromotionList() {
        // given
        ForFreeStrategy forFreeStrategy = new ForFreeStrategy();
        HashMap<String, ProductFormat> givenList = new HashMap<String, ProductFormat>();
        // when
        List<String> expectList = forFreeStrategy.getPromotionList();
        List<String> testList = Arrays.asList(" ");
        // then
        assertNotEquals(testList, expectList);
    }

    @Test
    public void shouldNotReturnForFreeStringIfProductNumberIsLessThanForFreeNumber() throws Exception{
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000005", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        ForFreeStrategy builder = new ForFreeStrategy();
        TreeMap<String, Integer> expectList = builder.getForFreeProductInfo(givenList);
        HashMap<String, Integer> testList = new HashMap<String, Integer>();
        assertEquals(testList, expectList);
    }

}