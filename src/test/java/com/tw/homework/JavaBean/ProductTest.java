package com.tw.homework.JavaBean;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.IllegalParameter;
import org.junit.Before;
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

    @Test(expected = EmptyInputException.class)
    public void shouldThrowExceptionWhenAttemptToInitializeBarcodeToNull() throws Exception{
        product = new Product.Builder()
                .setBarcode(null)
                .setName("name")
                .setPrice(0)
                .setUnitType("unit")
                .build();
    }

    @Test(expected = EmptyInputException.class)
    public void shouldThrowExceptionWhenAttemptToInitializeBarcodeToEmptyString() throws Exception{
        product = new Product.Builder()
                .setBarcode("")
                .setName("name")
                .setPrice(0)
                .setUnitType("unit")
                .build();
    }

    @Test(expected = EmptyInputException.class)
    public void shouldThrowExceptionWhenAttemptToInitializeNameToNull() throws Exception{
        product = new Product.Builder()
                .setBarcode("barcode")
                .setName(null)
                .setPrice(0)
                .setUnitType("unit")
                .build();
    }

    @Test(expected = EmptyInputException.class)
    public void shouldThrowExceptionWhenAttemptToInitializeNameToEmptyString() throws Exception{
        product = new Product.Builder()
                .setBarcode("barcode")
                .setName("")
                .setPrice(0)
                .setUnitType("unit")
                .build();
    }

    @Test(expected = EmptyInputException.class)
    public void shouldThrowExceptionWhenAttemptToInitializeUnitTypeToNull() throws Exception{
        product = new Product.Builder()
                .setBarcode("barcode")
                .setName("name")
                .setPrice(0)
                .setUnitType(null)
                .build();
    }

    @Test(expected = EmptyInputException.class)
    public void shouldThrowExceptionWhenAttemptToInitializeUnitTypeToEmptyString() throws Exception{
        product = new Product.Builder()
                .setBarcode("barcode")
                .setName("name")
                .setPrice(0)
                .setUnitType("")
                .build();
    }

    @Test(expected = IllegalParameter.class)
    public void shouldThrowExceptionWhenAttemptToInitializePriceToTheNumberLessThanZero() throws Exception{
        product = new Product.Builder()
                .setBarcode("barcode")
                .setName("name")
                .setPrice(-1)
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