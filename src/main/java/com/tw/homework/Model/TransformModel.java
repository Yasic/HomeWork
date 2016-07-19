package com.tw.homework.Model;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.WrongStringInputException;
import com.tw.homework.JavaBean.ProductFormat;
import com.tw.homework.Util.ProductInfoHelper;

import java.util.TreeMap;

/**
 * Created by yasic on 16-7-16.
 */
public class TransformModel{

    public TreeMap<String, Integer> transformInputToBarcodeAndNumber(String input) {
        TreeMap<String, Integer> result = new TreeMap<String, Integer>();
        String[] temp = null;
        if (input == null || input.equals("")){
            throw new EmptyInputException();
        }
        temp = input.split("'");
        if (temp.length == 1) {
            throw new WrongStringInputException();
        }
        int productNumber = 0;
        String singleBarcode = "";
        for (int index = 1; index < temp.length - 1; index += 2) {
            productNumber = 0;
            singleBarcode = "";
            try {
                String[] barcodeTemp = temp[index].split("-");
                productNumber = Integer.parseInt(barcodeTemp[1]);
                singleBarcode = barcodeTemp[0];
            } catch (Exception e) {
                productNumber = 1;
                singleBarcode = temp[index];
            } finally {
                if (result.containsKey(singleBarcode)) {
                    result.put(singleBarcode, result.get(singleBarcode) + productNumber);
                } else {
                    result.put(singleBarcode, productNumber);
                }
            }
        }
        return result;
    }

    public TreeMap<String, ProductFormat> transformBarcodeAndNumberToBarcodeAndFormat(TreeMap<String, Integer> barcodeHashMap){
        if (barcodeHashMap == null){
            throw new EmptyInputException();
        }
        TreeMap<String, ProductFormat> testList = new TreeMap<String, ProductFormat>();
        for (String key : barcodeHashMap.keySet()){
            testList.put(key,new ProductFormat.Builder().setNameScope(ProductInfoHelper.getInstance().getProductInfoList().get(key).getName())
                    .setNumberScope(barcodeHashMap.get(key))
                    .setPriceScope(ProductInfoHelper.getInstance().getProductInfoList().get(key).getPrice())
                    .setUnitScope(ProductInfoHelper.getInstance().getProductInfoList().get(key).getUnitType())
                    .setTotalMoneyScope(ProductInfoHelper.getInstance().getProductInfoList().get(key).getPrice() * barcodeHashMap.get(key))
                    .build());
        }
        return testList;
    }

}
