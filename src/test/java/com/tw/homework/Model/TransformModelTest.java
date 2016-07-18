package com.tw.homework.Model;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.WrongStringInputException;
import com.tw.homework.JavaBean.Format;
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

        TreeMap<String, Format> exceptList = transformModel.transformBarcodeAndNumberToBarcodeAndFormat(beforeList);

        LinkedHashMap<String, Format> testList = new LinkedHashMap<String, Format>();
        testList.put("ITEM000001", new Format.Builder()
                .setNameScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000001").getName())
                .setNumberScope(5)
                .setPriceScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000001").getPrice())
                .setUnitScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000001").getUnitType())
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());
        testList.put("ITEM000003", new Format.Builder()
                .setNameScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000003").getName())
                .setNumberScope(2)
                .setPriceScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000003").getPrice())
                .setUnitScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000003").getUnitType())
                .setTotalMoneyScope(4.0f).setSaveMoneyScope(0)
                .build());
        testList.put("ITEM000005", new Format.Builder()
                .setNameScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000005").getName())
                .setNumberScope(3)
                .setPriceScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000005").getPrice())
                .setUnitScope(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000005").getUnitType())
                .setTotalMoneyScope(3.0f).setSaveMoneyScope(0)
                .build());
        assertEquals(testList, exceptList);
    }


}