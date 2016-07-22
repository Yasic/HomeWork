package com.tw.homework.Model;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.WrongStringInputException;
import com.tw.homework.JavaBean.FormatData;
import com.tw.homework.Util.ProductInfoUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by lichenqiang on 16/7/16.
 */
public class TransformModelTest {
    TransformModel transformModel;

    @Before
    public void setUp() throws Exception {
        transformModel = new TransformModel();
    }

    @Test(expected = EmptyInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenEncounterEmptyInput() throws Exception{
        transformModel.transformToBarcodeAndNumberMap("");
    }

    @Test(expected = EmptyInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenEncounterNullInput() throws Exception{
        transformModel.transformToBarcodeAndNumberMap(null);
    }

    @Test(expected = WrongStringInputException.class)
    public void TransformToHashMapShouldThrowExceptionWhenInputFormatIsWrong() throws Exception{
        transformModel.transformToBarcodeAndNumberMap("1234");
    }

    @Test(expected = WrongStringInputException.class)
    public void ShouldThrowErrorIfProductBarcodeIsNotInProductInfoList() throws Exception {
        TreeMap<String, Integer> testList = new TreeMap<String, Integer>();
        testList.put("ITEM000000", 1);
        transformModel.transformToBarcodeAndFormatMap(testList);
    }

    @Test
    public void shouldIgnoreProductIfTheNumberIsZero() throws Exception {
        //When
        String barcodeInput = "[ 'ITEM000001-0']";

        //Expect to
        LinkedHashMap<String, Integer> expectList = new LinkedHashMap<String, Integer>();

        //Then
        assertEquals(transformModel.transformToBarcodeAndNumberMap(barcodeInput), expectList);
    }

    @Test
    public void shouldGetBarcodeAndNumberHashMapFromInputString() throws Exception {

        //When
        String barcodeInput = "[ 'ITEM000001']";

        //Expect to
        LinkedHashMap<String, Integer> expectList = new LinkedHashMap<String, Integer>();
        expectList.put("ITEM000001", 1);

        //Then
        assertEquals(transformModel.transformToBarcodeAndNumberMap(barcodeInput), expectList);
    }

    @Test
    public void shouldGetBarcodeAndNumberListWithoutRepeat() throws Exception {
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
        assertEquals(transformModel.transformToBarcodeAndNumberMap(barcodeInput), expectList);
    }

    @Test(expected = EmptyInputException.class)
    public void TransformToBarcodeAndFormatHashMapShouldThrowExceptionWhenEncounterNullInput() throws Exception{
        transformModel.transformToBarcodeAndFormatMap(null);
    }

    @Test
    public void shouldGetFormatHashMapFromHashMapByMethodOfTransformToFormatList() throws Exception {
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
        TreeMap<String, Integer> beforeList = transformModel.transformToBarcodeAndNumberMap(barcodeInput);

        TreeMap<String, FormatData> exceptList = transformModel.transformToBarcodeAndFormatMap(beforeList);

        TreeMap<String, FormatData> testList = new TreeMap<String, FormatData>();
        testList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(ProductInfoUtil.getInstance().getProductInfoList().get("ITEM000001").getPrice() * 5)
                .build());
        testList.put("ITEM000003", new FormatData.Builder()
                .setBarcode("ITEM000003")
                .setNumberScope(2)
                .setTotalMoneyScope(ProductInfoUtil.getInstance().getProductInfoList().get("ITEM000003").getPrice() * 2)
                .build());
        testList.put("ITEM000005", new FormatData.Builder()
                .setBarcode("ITEM000005")
                .setNumberScope(3)
                .setTotalMoneyScope(ProductInfoUtil.getInstance().getProductInfoList().get("ITEM000005").getPrice() * 3)
                .build());
        assertEquals(testList, exceptList);
    }
}