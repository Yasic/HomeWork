package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.FormatData;
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
public class FullFreeStrategyTest {
    @Test
    public void shouldReturnTwoWhenGetPriority() throws Exception {
        FullFreeStrategy fullFreeStrategy = new FullFreeStrategy();
        Assert.assertTrue(fullFreeStrategy.getPriority() == 2);
    }

    @Test
    public void shouldNotGetDiscountWhenOne() throws Exception {
        // given
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        // when
        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, FormatData> expectList = builder.calculatePromotion(givenList);
        // expect to
        HashMap<String, FormatData> testList = new HashMap<String, FormatData>();
        testList.put("ITEM000001", new FormatData.Builder()
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
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(2)
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(0)
                .build());
        // when
        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, FormatData> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, FormatData> testList = new TreeMap<String, FormatData>();
        testList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(2)
                .setTotalMoneyScope(6.0f).setSaveMoneyScope(0)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenFive() throws Exception {
        // given
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        // when
        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, FormatData> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, FormatData> testList = new TreeMap<String, FormatData>();
        testList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(12.0f).setSaveMoneyScope(3)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenEight() throws Exception {
        // given
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(8)
                .setTotalMoneyScope(24.0f).setSaveMoneyScope(0)
                .build());
        // when
        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, FormatData> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, FormatData> testList = new TreeMap<String, FormatData>();
        testList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(8)
                .setTotalMoneyScope(18.0f).setSaveMoneyScope(6)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldGetDiscountWhenNine() throws Exception {
        // given
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(9)
                .setTotalMoneyScope(27.0f).setSaveMoneyScope(0)
                .build());
        // when
        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, FormatData> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, FormatData> testList = new TreeMap<String, FormatData>();
        testList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(9)
                .setTotalMoneyScope(18.0f).setSaveMoneyScope(9)
                .build());
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldNotGetDiscountWhenBarcodeDifferent() throws Exception {
        // given
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000003")
                .setNumberScope(8)
                .setTotalMoneyScope(24.0f).setSaveMoneyScope(0)
                .build());
        // when
        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, FormatData> expectList = builder.calculatePromotion(givenList);
        // expect to
        TreeMap<String, FormatData> testList = new TreeMap<String, FormatData>();
        testList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(12)
                .setTotalMoneyScope(24.0f).setSaveMoneyScope(12)
                .build());
        // then
        assertNotEquals(testList, expectList);
    }


    @Test
    public void shouldReturnFullFreeTreeMapWhenProductInputIsInPromotionList() throws Exception{
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, Integer> expectList = builder.getFullFreeProductInfo(givenList);
        HashMap<String, Integer> testList = new HashMap<String, Integer>();
        testList.put("ITEM000001", 1);

        assertEquals(testList, expectList);
    }

    @Test
    public void shouldReturnNullWhenProductInputIsNotInPromotionList() throws Exception{
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000005", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, Integer> expectList = builder.getFullFreeProductInfo(givenList);
        HashMap<String, Integer> testList = new HashMap<String, Integer>();

        assertEquals(testList, expectList);
    }

    @Test
    public void shouldReturnFroFreePromotionList() {
        // given
        FullFreeStrategy fullFreeStrategy = new FullFreeStrategy();
        HashMap<String, FormatData> givenList = new HashMap<String, FormatData>();
        // when
        List<String> expectList = fullFreeStrategy.getPromotionList();
        List<String> testList = Arrays.asList("ITEM000001", "ITEM000003");
        // then
        assertEquals(testList, expectList);
    }

    @Test
    public void shouldNotReturnFullFreePromotionList() {
        // given
        FullFreeStrategy fullFreeStrategy = new FullFreeStrategy();
        HashMap<String, FormatData> givenList = new HashMap<String, FormatData>();
        // when
        List<String> expectList = fullFreeStrategy.getPromotionList();
        List<String> testList = Arrays.asList(" ");
        // then
        assertNotEquals(testList, expectList);
    }

    @Test
    public void shouldNotReturnFullFreeStringIfProductNumberIsLessThanFullFreeNumber() throws Exception{
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000005", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        FullFreeStrategy builder = new FullFreeStrategy();
        TreeMap<String, Integer> expectList = builder.getFullFreeProductInfo(givenList);
        HashMap<String, Integer> testList = new HashMap<String, Integer>();
        assertEquals(testList, expectList);
    }

}