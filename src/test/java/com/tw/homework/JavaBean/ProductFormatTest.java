package com.tw.homework.JavaBean;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.IllegalParameter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lichenqiang on 16/7/18.
 */
public class ProductFormatTest {

    private ProductFormat item1;
    private ProductFormat item2;

    @Before
    public void before() {
        item1 = new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(3.0f)
                .setSaveMoneyScope(1.0f)
                .build();
    }

    @Test
    public void shouldGetBarcode() throws Exception {
        assertEquals(item1.getBarcode(), "ITEM000001");
    }

    @Test
    public void shouldGetNumber() throws Exception {
        assertEquals(item1.getNumberScope(), 1);
    }

    @Test
    public void shouldGetTotal() throws Exception {
        assertEquals(item1.getTotalMoneyScope(), 3.0f, 0.0001);
    }

    @Test
    public void shouldGetSavedMoney() throws Exception {
        assertEquals(item1.getSaveMoneyScope(), 1.0f, 0.0001);
    }

    @Test
    public void shouldGetPromotionFlag() throws Exception {
        item1.setPromotionPriority(3);
        assertEquals(item1.getPromotionPriority(), 3);
    }

    @Test(expected = EmptyInputException.class)
    public void shouldThrowExceptionWhenAttemptToSetBarcodeToNull() throws Exception{
        item2 = new ProductFormat.Builder()
                .setBarcode(null)
                .setNumberScope(1)
                .setTotalMoneyScope(3.0f)
                .setSaveMoneyScope(1.0f)
                .build();
    }

    @Test(expected = IllegalParameter.class)
    public void shouldThrowExceptionWhenAttemptToSetNumberScopeToTheNumberLessThanZero() throws Exception{
        item2 = new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(-1)
                .setTotalMoneyScope(3.0f)
                .setSaveMoneyScope(1.0f)
                .build();
    }

    @Test(expected = IllegalParameter.class)
    public void shouldThrowExceptionWhenAttemptToSetTotalMoneyScopeToTheNumberLessThanZero() throws Exception{
        item2 = new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(-3.0f)
                .setSaveMoneyScope(1.0f)
                .build();
    }

    @Test(expected = IllegalParameter.class)
    public void shouldThrowExceptionWhenAttemptToSetSavedMoneyScopeToTheNumberLessThanZero() throws Exception{
        item2 = new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(-3.0f)
                .setSaveMoneyScope(1.0f)
                .build();
    }

    @Test
    public void shouldSetTotalMoney() throws Exception {
        item1.setTotalMoneyScope(2.0f);
        assertEquals(item1.getTotalMoneyScope(), 2.0f, 0.0001);
    }

    @Test(expected = IllegalParameter.class)
    public void shouldThrowExceptionWhenAttemptToSetToTalMoneyToTheNumberLessThanZero() throws  Exception{
        item1.setTotalMoneyScope(-2.0f);
    }

    @Test
    public void shouldSetSavedMoney() throws Exception {
        item1.setSaveMoneyScope(2.0f);
        assertEquals(item1.getSaveMoneyScope(), 2.0f, 0.0001);
    }

    @Test(expected = IllegalParameter.class)
    public void shouldThrowExceptionWhenAttemptToSetSavedMoneyToTheNumberLessThanZero() throws  Exception{
        item1.setSaveMoneyScope(-2.0f);
    }

    @Test
    public void shouldSetNumber() throws Exception {
        item1.setNumberScope(2);
        assertEquals(item1.getNumberScope(), 2);
    }

    @Test(expected = IllegalParameter.class)
    public void shouldThrowExceptionWhenAttemptToSetNumberToTheNumberLessThanZero() throws  Exception{
        item1.setNumberScope(-1);
    }

    @Test
    public void shouldSetPromotionFlag() throws Exception {
        item1.setPromotionPriority(3);
        assertEquals(item1.getPromotionPriority(), 3);
    }

    @Test(expected = IllegalParameter.class)
    public void shouldThrowExceptionWhenAttemptToSetPriorityToTheNumberLessThanZero() throws  Exception{
        item1.setPromotionPriority(-1);
    }

    @Test
    public void shouldReturnTrueWhenEquals() throws Exception {
        // given
        item2 = new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(3.0f)
                .setSaveMoneyScope(1.0f)
                .build();
        // then
        assertTrue(item1.equals(item2));
    }

    @Test
    public void shouldReturnFalseWhenDifferentValue() throws Exception {
        // given
        item2 = new ProductFormat.Builder()
                .setBarcode("ITEM000002")
                .setNumberScope(1)
                .setTotalMoneyScope(3.0f)
                .setSaveMoneyScope(1.0f)
                .build();
        // then
        assertFalse(item1.equals(item2));
    }

    @Test
    public void shouldReturnFalseWhenDifferentLength() throws Exception {
        // given
        item2 = new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(1)
                .setTotalMoneyScope(3.0f)
                .build();
        // then
        assertFalse(item1.equals(item2));
    }

    @Test
    public void shouldReturnStringWhenGiven() throws Exception {
        // given
        String result = "ITEM000001 1   3.0 1.0";
        assertEquals(item1.toString(), result);
    }
}