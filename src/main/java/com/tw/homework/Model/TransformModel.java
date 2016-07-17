package com.tw.homework.Model;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.WrongStringInputException;
import com.tw.homework.JavaBean.Format;

import java.util.HashMap;

/**
 * Created by yasic on 16-7-16.
 */
public class TransformModel{

    public HashMap<String, Integer> transformToBarcodeAndNumberHashMap (String input) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
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

    public HashMap<String, Format> transformHashMapToBarcodeFormatHashMap(HashMap<String, Integer> barcodeHashMap){
        if (barcodeHashMap == null){
            throw new EmptyInputException();
        }
        HashMap<String, Format> testList = new HashMap<String, Format>();
        for (String key : barcodeHashMap.keySet()){
            if (key.equals("ITEM000001")){
                testList.put("ITEM000001", new Format("1", barcodeHashMap.get(key), 3.0f, 3 * barcodeHashMap.get(key), 0));
            }
            if (key.equals("ITEM000003")){
                testList.put("ITEM000003", new Format("3", barcodeHashMap.get(key), 1.0f, 1 * barcodeHashMap.get(key), 0));
            }
            if (key.equals("ITEM000005")){
                testList.put("ITEM000005", new Format("5", barcodeHashMap.get(key), 1.0f, 1 * barcodeHashMap.get(key), 0));
            }
        }
        return testList;
    }

}
