package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.ProductFormat;
import com.tw.homework.Util.ProductInfoHelper;
import com.tw.homework.Util.PromotionPriority;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by yasic on 16-7-16.
 */
public class ForFreeStrategy extends BasePromotionStrategy {
    private static final int PRIORITY = PromotionPriority.FORFREE.getPriority();
    private static final int NUMBERFORFREE = 2;


    public TreeMap<String, ProductFormat> calculatePromotion(TreeMap<String, ProductFormat> formatHashMap) {
        java.text.DecimalFormat decimalFormat =  new java.text.DecimalFormat("0.00#");
        List<String> promotionList = getPromotionList();
        for (String item : formatHashMap.keySet()){
            if ( promotionList.contains(item) && formatHashMap.get(item).getPromotionFlag() <= PRIORITY && formatHashMap.get(item).getNumberScope() >= NUMBERFORFREE){
                int newProductNumber = formatHashMap.get(item).getNumberScope() / NUMBERFORFREE + formatHashMap.get(item).getNumberScope();
                formatHashMap.get(item).setNumberScope(newProductNumber);
                BigDecimal b1 = new BigDecimal(Float.toString(newProductNumber * ProductInfoHelper.getInstance().getProductInfoList().get(item).getPrice()));
                BigDecimal b2 = new BigDecimal(Float.toString(formatHashMap.get(item).getTotalMoneyScope()));
                float ss = b1.subtract(b2).floatValue();
                formatHashMap.get(item).setSaveMoneyScope(Float.parseFloat(decimalFormat.format(ss)));
                formatHashMap.get(item).setPromotionFlag(PRIORITY);
            }
        }
        return formatHashMap;
    }

    public TreeMap<String, Integer> getForFreeProductInfo(TreeMap<String, ProductFormat> productFormatTreeMap) {
        List<String> promotionList = getPromotionList();
        TreeMap<String, Integer> resultList = new TreeMap<String, Integer>();
        for (String item : productFormatTreeMap.keySet()){
            if (promotionList.contains(item) && productFormatTreeMap.get(item).getPromotionFlag() <= PRIORITY && productFormatTreeMap.get(item).getNumberScope() >= NUMBERFORFREE){
                int forFreeNumber = productFormatTreeMap.get(item).getNumberScope() / NUMBERFORFREE;
                resultList.put(item, forFreeNumber);
            }
        }
        return resultList;
    }

    protected int getPriority() {
        return PRIORITY;
    }

    protected List<String> getPromotionList() {
        List<String> promotionList = new ArrayList<String>();
        promotionList.add("ITEM000001");
        promotionList.add("ITEM000003");
        return promotionList;
    }
}
