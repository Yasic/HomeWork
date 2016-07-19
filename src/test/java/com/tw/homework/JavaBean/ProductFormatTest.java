package com.tw.homework.JavaBean;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lichenqiang on 16/7/18.
 */
public class ProductFormatTest {

    private ProductFormat item1;
    private ProductFormat item2;

    @Before
    public void before() {
        item1 = new ProductFormat.Builder()
                .setNameScope("name")
                .setNumberScope(1)
                .setPriceScope(0)
                .setUnitScope("unit")
                .setTotalMoneyScope(3.0f)
                .build();
    }

    @Test
    public void shouldGetName() throws Exception {
        assertEquals(item1.getNameScope(), "name");
    }

    @Test
    public void shouldGetNumber() throws Exception {
        assertEquals(item1.getNumberScope(), 1);
    }

    @Test
    public void shouldGetPrice() throws Exception {
        assertEquals(item1.getPriceScope(), 0, 0.0001);
    }

    @Test
    public void shouldGetUnit() throws Exception {
        assertEquals(item1.getUnitScope(), "unit");
    }

    @Test
    public void shouldGetTotal() throws Exception {
        assertEquals(item1.getTotalMoneyScope(), 3.0f, 0.0001);
    }

    @Test
    public void shouldGetSavedMoney() throws Exception {
        assertEquals(item1.getSaveMoneyScope(), 0, 0.0001);
    }

    @Test
    public void shouldReturnTrueWhenEquals() throws Exception {
        // given
        item2 = new ProductFormat.Builder()
                .setNameScope("name")
                .setNumberScope(1)
                .setPriceScope(0)
                .setUnitScope("unit")
                .setTotalMoneyScope(3.0f)
                .build();
        // then
        assertTrue(item1.equals(item2));
    }

    @Test
    public void shouldReturnFalseWhenDifferent() throws Exception {
        // given
        item2 = new ProductFormat.Builder()
                .setNameScope(" ")
                .setNumberScope(1)
                .setPriceScope(0)
                .setUnitScope("unit")
                .setTotalMoneyScope(3.0f)
                .build();
        // then
        assertFalse(item1.equals(item2));
    }

    @Test
    public void shouldReturnStringWhenGiven() throws Exception {
        // given
        String result = "name 1 0.0 unit 3.0 0.0";
        assertEquals(item1.toString(), result);
    }
}