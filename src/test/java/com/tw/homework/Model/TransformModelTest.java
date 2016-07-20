package com.tw.homework.Model;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.WrongStringInputException;
import com.tw.homework.JavaBean.ProductFormat;
import com.tw.homework.Util.ProductInfoHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by lichenqiang on 16/7/16.
 */
public class TransformModelTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = EmptyInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenEncounterEmptyInput() throws Exception{
        TransformModel transformModel = new TransformModel();
        transformModel.transformInputToBarcodeAndNumber("");
    }

    @Test(expected = EmptyInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenEncounterNullInput() throws Exception{
        TransformModel transformModel = new TransformModel();
        transformModel.transformInputToBarcodeAndNumber(null);
    }

    @Test(expected = WrongStringInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenInputFormatIsWrong() throws Exception{
        TransformModel transformModel = new TransformModel();
        transformModel.transformInputToBarcodeAndNumber("1234");
    }

    @Test(expected = WrongStringInputException.class)
    public void ShouldThrowErrorIfProductBarcodeIsNotInProductInfoList() throws Exception {
        TransformModel transformModel = new TransformModel();
        TreeMap<String, Integer> testList = new TreeMap<String, Integer>();
        testList.put("ITEM000000", 1);
        transformModel.transformBarcodeAndNumberToBarcodeAndFormat(testList);
    }

    @Test
    public void shouldIgnoreProductIfTheNumberIsZero() throws Exception {
        TransformModel transformModel = new TransformModel();

        //When
        String barcodeInput = "[ 'ITEM000001-0']";

        //Expect to
        LinkedHashMap<String, Integer> expectList = new LinkedHashMap<String, Integer>();
        //expectList.put("ITEM000001", 1);

        //Then
        assertEquals(transformModel.transformInputToBarcodeAndNumber(barcodeInput), expectList);
    }

    @Test
    public void shouldGetBarcodeAndNumberHashMapFromInputString() throws Exception {
        TransformModel transformModel = new TransformModel();

        //When
        String barcodeInput = "[ 'ITEM000001']";

        //Expect to
        LinkedHashMap<String, Integer> expectList = new LinkedHashMap<String, Integer>();
        expectList.put("ITEM000001", 1);

        //Then
        assertEquals(transformModel.transformInputToBarcodeAndNumber(barcodeInput), expectList);
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
        LinkedHashMap<String, Integer> expectList = new LinkedHashMap<String, Integer>();
        expectList.put("ITEM000001", 5);
        expectList.put("ITEM000003", 2);
        expectList.put("ITEM000005", 3);

        // then
        assertEquals(transformModel.transformInputToBarcodeAndNumber(barcodeInput), expectList);
    }

    @Test(expected = EmptyInputException.class)
    public void TransformToBarcodeAndFormatHashMapShouldThrowExceptionWhenEncounterNullInput() throws Exception{
        TransformModel transformModel = new TransformModel();
        transformModel.transformBarcodeAndNumberToBarcodeAndFormat(null);
    }

    @Test
    public void shouldGetFormatHashMapFromHashMapByMethodOfTransformToFormatList() throws Exception {
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
        TreeMap<String, Integer> beforeList = transformModel.transformInputToBarcodeAndNumber(barcodeInput);

        TreeMap<String, ProductFormat> exceptList = transformModel.transformBarcodeAndNumberToBarcodeAndFormat(beforeList);

        TreeMap<String, ProductFormat> testList = new TreeMap<String, ProductFormat>();
        testList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000001").getPrice() * 5)
                .build());
        testList.put("ITEM000003", new ProductFormat.Builder()
                .setBarcode("ITEM000003")
                .setNumberScope(2)
                .setTotalMoneyScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000003").getPrice() * 2)
                .build());
        testList.put("ITEM000005", new ProductFormat.Builder()
                .setBarcode("ITEM000005")
                .setNumberScope(3)
                .setTotalMoneyScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000005").getPrice() * 3)
                .build());
        assertEquals(testList, exceptList);
    }


}