package com.tw.homework;

import com.tw.homework.JavaBean.ProductFormat;
import com.tw.homework.Model.OutputModel;
import com.tw.homework.Model.TransformModel;

import java.util.TreeMap;

/**
 * Created by Yasic on 2016/7/18.
 */
public class main {
    public static void main(String[] args) {
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
        TransformModel transformModel = new TransformModel();
        TreeMap<String, ProductFormat> productFormatTreeMap =  transformModel.transformBarcodeAndNumberToBarcodeAndFormat(transformModel.transformInputToBarcodeAndNumber(barcodeInput));
        OutputModel outputModel = new OutputModel();
        System.out.println(outputModel.getFormatString(productFormatTreeMap));
    }
}
