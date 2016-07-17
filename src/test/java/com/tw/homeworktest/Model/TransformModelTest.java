package com.tw.homeworktest.Model;

import com.tw.homework.Exception.EmptyStringInputException;
import com.tw.homework.Exception.WrongStringInputException;
import com.tw.homework.Model.TransformModel;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by lichenqiang on 16/7/16.
 */
public class TransformModelTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = EmptyStringInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenEncounterEmptyInput() throws Exception{
        TransformModel transformModel = new TransformModel();
        transformModel.transformToBarcodeAndNumberHashMap("");
    }

    @Test(expected = EmptyStringInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenEncounterNullInput() throws Exception{
        TransformModel transformModel = new TransformModel();
        transformModel.transformToBarcodeAndNumberHashMap(null);
    }

    @Test(expected = WrongStringInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenInputFormatIsWrong() throws Exception{
        TransformModel transformModel = new TransformModel();
        transformModel.transformToBarcodeAndNumberHashMap("1234");
    }

    @Test
    public void shouldGetBarcodeAndNumberHashMapFromInputString() throws Exception {
        TransformModel transformModel = new TransformModel();

        //When
        String barcodeInput = "[ 'ITEM000001']";

        //Expect to
        HashMap<String, Integer> expectList = new HashMap<String, Integer>();
        expectList.put("ITEM000001", 1);

        //Then
        assertEquals(transformModel.transformToBarcodeAndNumberHashMap(barcodeInput), expectList);
    }

    @Test
    public void shouldGetBarcodeAndNumberListWithoutRepeat() throws Exception {

        TransformModel transformModel = new TransformModel();

        // when
        String barcodeInput = "[" +
                "    'ITEM000001',\n" +
                "    'ITEM000001',\n" +
                "    'ITEM000001',\n" +
                "    'ITEM000001',\n" +
                "    'ITEM000001',\n" +
                "    'ITEM000003-2',\n" +
                "    'ITEM000005',\n" +
                "    'ITEM000005',\n" +
                "    'ITEM000005'\n" +
                "]";

        // expect to
        HashMap<String, Integer> expectList = new HashMap<String, Integer>();
        expectList.put("ITEM000001", 5);
        expectList.put("ITEM000003", 2);
        expectList.put("ITEM000005", 3);

        // then
        assertEquals(transformModel.transformToBarcodeAndNumberHashMap(barcodeInput), expectList);
    }

    @Test
    public void shouldGetNonRepeatProductListFromHashMapByMethodOfTransformToProductList() throws Exception {

    }

}