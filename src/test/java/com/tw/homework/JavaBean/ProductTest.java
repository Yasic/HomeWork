package com.tw.homework.JavaBean;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by lichenqiang on 16/7/16.
 */
public class ProductTest {
    private Product product;

    @Before
    public void before() {
        product = new Product.Builder()
                .setBarcode("barcode")
                .setName("name")
                .setPrice(0)
                .setUnitType("unit")
                .build();
    }

    @Test
    public void shouldGetBarcode() throws Exception {
        assertEquals(product.getBarcode(), "barcode");
    }

    @Test
    public void shouldGetName() throws Exception {
        assertEquals(product.getName(), "name");
    }

    @Test
    public void shouldGetPrice() throws Exception {
        assertEquals(product.getPrice(), 0, 0.0001);
    }

    @Test
    public void shouldGetUnitType() throws Exception {
        assertEquals(product.getUnitType(), "unit");
    }
}