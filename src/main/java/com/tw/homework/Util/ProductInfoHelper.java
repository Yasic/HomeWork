package com.tw.homework.Util;

import com.tw.homework.JavaBean.Product;

import java.util.TreeMap;

/**
 * Created by Yasic on 2016/7/17.
 */
public class ProductInfoHelper {
    private static TreeMap<String, Product> productList;
    private ProductInfoHelper(){
    }

    public TreeMap<String, Product> getProductInfoList(){
        if (productList == null){
            productList = new TreeMap<String, Product>();
            productList.put("ITEM000001", new Product.Builder()
                    .setBarcode("ITEM000001")
                    .setName("可口可乐")
                    .setPrice(3.0f)
                    .setUnitType("瓶")
                    .build());
            productList.put("ITEM000003", new Product.Builder()
                    .setBarcode("ITEM000003")
                    .setName("羽毛球")
                    .setPrice(2.0f)
                    .setUnitType("个")
                    .build());
            productList.put("ITEM000005", new Product.Builder()
                    .setBarcode("ITEM000005")
                    .setName("苹果")
                    .setPrice(1.0f)
                    .setUnitType("斤")
                    .build());
        }
        return productList;
    }

    public static ProductInfoHelper getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final ProductInfoHelper instance = new ProductInfoHelper();
    }
}
