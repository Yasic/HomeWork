package com.tw.homework;

import com.tw.homework.Util.ProductInfoHelper;

/**
 * Created by Yasic on 2016/7/18.
 */
public class main {
    public static void main(String[] args) {
        System.out.println(ProductInfoHelper.getInstance().getProductInfoList().get("ITEM000005").getName());
    }
}
