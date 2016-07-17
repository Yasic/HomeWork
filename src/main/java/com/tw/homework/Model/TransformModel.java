package com.tw.homework.Model;

import com.tw.homework.Exception.EmptyStringInputException;
import com.tw.homework.Exception.WrongStringInputException;

import java.util.HashMap;

/**
 * Created by yasic on 16-7-16.
 */
public class TransformModel{

    public HashMap<String, Integer> transformToBarcodeAndNumberHashMap (String input) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        String[] temp = null;
        if (input == null || input.equals("")){
            throw new EmptyStringInputException();
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
                System.out.println(barcodeTemp[1]);
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

}
