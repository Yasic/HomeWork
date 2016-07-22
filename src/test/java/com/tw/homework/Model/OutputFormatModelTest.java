package com.tw.homework.Model;

import com.tw.homework.JavaBean.FormatData;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.TreeMap;

/**
 * Created by Yasic on 2016/7/17.
 */
public class OutputFormatModelTest {

    @Test
    public void shouldReturnFormatString() throws Exception {
        OutputFormatModel outputFormatModel = new OutputFormatModel.Builder().build();
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
        String actualString = outputFormatModel.getFormatOutput(transformModel.transformToBarcodeAndFormatMap(transformModel.transformToBarcodeAndNumberMap(barcodeInput)));
        String exceptString = "***<没钱赚商店>购物清单***\n" +
                "名称：可口可乐，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n" +
                "名称：羽毛球，数量：2个，单价：2.00(元)，小计：4.00(元)\n" +
                "名称：苹果，数量：3斤，单价：1.00(元)，小计：2.85(元)，节省0.15(元)\n" +
                "----------------------\n" +
                "买二赠一商品：\n" +
                "名称：可口可乐，数量：1瓶\n" +
                "----------------------\n" +
                "总计：18.85(元)\n" +
                "节省：3.15(元)\n";
        Assert.assertEquals(exceptString, actualString);
    }

    @Test
    public void shouldReturnFormatProductArray() throws Exception {
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
        Class<OutputFormatModel> outputModelClass = OutputFormatModel.class;
        //Object instance = outputModelClass.newInstance();
        Method method = outputModelClass.getDeclaredMethod("getProductArrayInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputFormatModel.Builder().build(), transformModel.transformToBarcodeAndFormatMap(transformModel.transformToBarcodeAndNumberMap(barcodeInput)));
        String exceptString = "名称：可口可乐，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n" +
                "名称：羽毛球，数量：2个，单价：2.00(元)，小计：4.00(元)\n" +
                "名称：苹果，数量：3斤，单价：1.00(元)，小计：2.85(元)，节省0.15(元)\n";
        Assert.assertEquals(actualString, exceptString);
    }

    @Test
    public void shouldGetFullFreeStringWhenProductInputIsInPromotionList() throws Exception{
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        Class<OutputFormatModel> outputModelClass = OutputFormatModel.class;
        Method method = outputModelClass.getDeclaredMethod("getFullFreeInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputFormatModel.Builder().build(), givenList);
        String exceptString = "----------------------\n买二赠一商品：\n" + "名称：可口可乐，数量：1瓶\n";
        Assert.assertEquals(actualString, exceptString);
    }

    @Test
    public void shouldGetEmptyStringWhenProductInputIsNotInPromotionList() throws Exception{
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000005", new FormatData.Builder()
                .setBarcode("ITEM000005")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        Class<OutputFormatModel> outputModelClass = OutputFormatModel.class;
        Method method = outputModelClass.getDeclaredMethod("getFullFreeInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputFormatModel.Builder().build(), givenList);
        String exceptString = "";
        Assert.assertEquals(actualString, exceptString);
    }

    @Test
    public void shouldReturnTotalMoneyAndSavedMoney() throws Exception {
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000001", new FormatData.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        Class<OutputFormatModel> outputModelClass = OutputFormatModel.class;
        Method method = outputModelClass.getDeclaredMethod("getMoneyInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputFormatModel.Builder().build(), givenList);
        String exceptString = "总计：12.00(元)\n" +
                "节省：3.00(元)\n";
        Assert.assertEquals(actualString, exceptString);
    }

    @Test
    public void shouldReturnOnlyTotalMoney() throws Exception{
        TreeMap<String, FormatData> givenList = new TreeMap<String, FormatData>();
        givenList.put("ITEM000002", new FormatData.Builder()
                .setBarcode("ITEM000002")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        Class<OutputFormatModel> outputModelClass = OutputFormatModel.class;
        Method method = outputModelClass.getDeclaredMethod("getMoneyInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputFormatModel.Builder().build(), givenList);
        String exceptString = "总计：15.00(元)\n";
        Assert.assertEquals(actualString, exceptString);
    }
}