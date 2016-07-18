package com.tw.homework.Model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yasic on 2016/7/17.
 */
public class OutputModelTest {

    @Test
    public void shouldReturnFormatProductArray() throws Exception {
        OutputModel outputModel = new OutputModel();
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
        String test = outputModel.getFormatProductArray(transformModel.transformBarcodeAndNumberToBarcodeAndFormat(transformModel.transformInputToBarcodeAndNumber(barcodeInput)));
        String exceptString = "名称：可口可乐，数量：7瓶，单价：3.0(元)，小计：15.0(元)\n" +
                "名称：羽毛球，数量：3个，单价：2.0(元)，小计：4.0(元)\n" +
                "名称：苹果，数量：3斤，单价：1.0(元)，小计：2.85(元)，节省0.15(元)\n";
        Assert.assertTrue(test.equals(exceptString));
    }

    @Test
    public void shouldReturnFormatString() throws Exception {
        OutputModel outputModel = new OutputModel();
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
        String output = outputModel.getFormatString(transformModel.transformBarcodeAndNumberToBarcodeAndFormat(transformModel.transformInputToBarcodeAndNumber(barcodeInput)));
        String exceptOutput = "***<没钱赚商店>购物清单***\n" +
                "\n" +
                "名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n" +
                "\n" +
                "名称：羽毛球，数量：6个，单价：1.00(元)，小计：4.00(元)\n" +
                "\n" +
                "名称：苹果，数量：2斤，单价：5.50(元)，小计：10.45(元)，节省0.55(元)\n" +
                "\n" +
                "----------------------\n" +
                "\n" +
                "买二赠一商品：\n" +
                "\n" +
                "名称：可口可乐，数量：1瓶\n" +
                "\n" +
                "名称：羽毛球，数量：2个\n" +
                "\n" +
                "----------------------\n" +
                "\n" +
                "总计：20.45(元)\n" +
                "\n" +
                "节省：5.55(元)\n" +
                "\n" +
                "**********************";
        Assert.assertEquals(exceptOutput, output);
    }
}