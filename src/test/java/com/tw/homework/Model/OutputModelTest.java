package com.tw.homework.Model;

import com.tw.homework.JavaBean.ProductFormat;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.TreeMap;

/**
 * Created by Yasic on 2016/7/17.
 */
public class OutputModelTest {

    @Test
    public void shouldReturnFormatString() throws Exception {
        OutputModel outputModel = new OutputModel.Builder().build();
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
        String actualString = outputModel.getFormatOutput(transformModel.transformBarcodeAndNumberToBarcodeAndFormat(transformModel.transformInputToBarcodeAndNumber(barcodeInput)));
        String exceptString = "***<没钱赚商店>购物清单***\n" +
                "名称：可口可乐，数量：7瓶，单价：3.00(元)，小计：15.00(元)\n" +
                "名称：羽毛球，数量：3个，单价：2.00(元)，小计：4.00(元)\n" +
                "名称：苹果，数量：3斤，单价：1.00(元)，小计：2.85(元)，节省0.15(元)\n" +
                "----------------------\n" +
                "买二赠一商品：\n" +
                "名称：可口可乐，数量：2瓶\n" +
                "名称：羽毛球，数量：1个\n" +
                "----------------------\n" +
                "总计：21.85(元)\n" +
                "节省：8.15(元)\n";
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
        Class<OutputModel> outputModelClass = OutputModel.class;
        //Object instance = outputModelClass.newInstance();
        Method method = outputModelClass.getDeclaredMethod("getProductArrayInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputModel.Builder().build(), transformModel.transformBarcodeAndNumberToBarcodeAndFormat(transformModel.transformInputToBarcodeAndNumber(barcodeInput)));
        String exceptString = "名称：可口可乐，数量：7瓶，单价：3.00(元)，小计：15.00(元)\n" +
                "名称：羽毛球，数量：3个，单价：2.00(元)，小计：4.00(元)\n" +
                "名称：苹果，数量：3斤，单价：1.00(元)，小计：2.85(元)，节省0.15(元)\n";
        Assert.assertEquals(actualString, exceptString);
    }

    @Test
    public void shouldGetForFreeStringWhenProductInputIsInPromotionList() throws Exception{
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        Class<OutputModel> outputModelClass = OutputModel.class;
        Method method = outputModelClass.getDeclaredMethod("getForFreeInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputModel.Builder().build(), givenList);
        String exceptString = "----------------------\n买二赠一商品：\n" + "名称：可口可乐，数量：2瓶\n";
        Assert.assertEquals(actualString, exceptString);
    }

    @Test
    public void shouldGetEmptyStringWhenProductInputIsNotInPromotionList() throws Exception{
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000005", new ProductFormat.Builder()
                .setBarcode("ITEM000005")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        Class<OutputModel> outputModelClass = OutputModel.class;
        Method method = outputModelClass.getDeclaredMethod("getForFreeInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputModel.Builder().build(), givenList);
        String exceptString = "";
        Assert.assertEquals(actualString, exceptString);
    }

    @Test
    public void shouldReturnTotalMoneyAndSavedMoney() throws Exception {
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000001", new ProductFormat.Builder()
                .setBarcode("ITEM000001")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        Class<OutputModel> outputModelClass = OutputModel.class;
        Method method = outputModelClass.getDeclaredMethod("getMoneyInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputModel.Builder().build(), givenList);
        String exceptString = "总计：15.00(元)\n" +
                "节省：6.00(元)\n";
        Assert.assertEquals(actualString, exceptString);
    }

    @Test
    public void shouldReturnOnlyTotalMoney() throws Exception{
        TreeMap<String, ProductFormat> givenList = new TreeMap<String, ProductFormat>();
        givenList.put("ITEM000002", new ProductFormat.Builder()
                .setBarcode("ITEM000002")
                .setNumberScope(5)
                .setTotalMoneyScope(15.0f).setSaveMoneyScope(0)
                .build());

        Class<OutputModel> outputModelClass = OutputModel.class;
        Method method = outputModelClass.getDeclaredMethod("getMoneyInfoScope", TreeMap.class);
        method.setAccessible(true);

        String actualString = (String) method.invoke(new OutputModel.Builder().build(), givenList);
        String exceptString = "总计：15.00(元)\n";
        Assert.assertEquals(actualString, exceptString);
    }
}