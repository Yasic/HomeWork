package com.tw.homework.Util;

import com.tw.homework.JavaBean.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

/**
 * Created by Yasic on 2016/7/21.
 */
public class ProductInfoHelperTest {

    @Test
    public void shouldGetProductTreeMap() throws Exception{
        TreeMap<String, Product> exceptTreeMap = new TreeMap<String, Product>();
        exceptTreeMap.put("ITEM000001", new Product.Builder()
                .setBarcode("ITEM000001")
                .setName("可口可乐")
                .setPrice(3.0f)
                .setUnitType("瓶")
                .build());
        exceptTreeMap.put("ITEM000003", new Product.Builder()
                .setBarcode("ITEM000003")
                .setName("羽毛球")
                .setPrice(2.0f)
                .setUnitType("个")
                .build());
        exceptTreeMap.put("ITEM000005", new Product.Builder()
                .setBarcode("ITEM000005")
                .setName("苹果")
                .setPrice(1.0f)
                .setUnitType("斤")
                .build());
        Assert.assertEquals(exceptTreeMap, ProductInfoHelper.getInstance().getProductInfoList());
    }

}