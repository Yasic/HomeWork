package com.tw.homework.Strategy;

import com.tw.homework.JavaBean.FormatData;
import com.tw.homework.Util.ProductInfoUtil;
import com.tw.homework.Util.PromotionPriorityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by yasic on 16-7-16.
 */
public class FullFreeStrategy extends BasePromotionStrategy {
    private static final int PRIORITY = PromotionPriorityUtil.FULLFREE.getPriority();
    private static final int NUMBERFULLFREE = 3;


    public TreeMap<String, FormatData> calculatePromotion(TreeMap<String, FormatData> formatHashMap) {
        java.text.DecimalFormat decimalFormat =  new java.text.DecimalFormat("0.00#");
        List<String> promotionList = getPromotionList();
        for (String item : formatHashMap.keySet()){
            if ( promotionList.contains(item) && formatHashMap.get(item).getPromotionPriority() <= PRIORITY && formatHashMap.get(item).getNumberScope() >= NUMBERFULLFREE){
                int freeNumber = formatHashMap.get(item).getNumberScope() / NUMBERFULLFREE;
                formatHashMap.get(item).setTotalMoneyScope((formatHashMap.get(item).getNumberScope() - freeNumber) * ProductInfoUtil.getInstance().getProductInfoList().get(item).getPrice());
                formatHashMap.get(item).setSaveMoneyScope(freeNumber * ProductInfoUtil.getInstance().getProductInfoList().get(item).getPrice());
                formatHashMap.get(item).setPromotionPriority(PRIORITY);

                /*int newProductNumber = formatHashMap.get(item).getNumberScope() / NUMBERFULLFREE + formatHashMap.get(item).getNumberScope();
                formatHashMap.get(item).setNumberScope(newProductNumber);
                BigDecimal b1 = new BigDecimal(Float.toString(newProductNumber * ProductInfoUtil.getInstance().getProductInfoList().get(item).getPrice()));
                BigDecimal b2 = new BigDecimal(Float.toString(formatHashMap.get(item).getTotalMoneyScope()));
                float ss = b1.subtract(b2).floatValue();
                formatHashMap.get(item).setSaveMoneyScope(Float.parseFloat(decimalFormat.format(ss)));
                formatHashMap.get(item).setPromotionPriority(PRIORITY);*/
            }
        }
        return formatHashMap;
    }

    public TreeMap<String, Integer> getFullFreeProductInfo(TreeMap<String, FormatData> productFormatTreeMap) {
        List<String> promotionList = getPromotionList();
        TreeMap<String, Integer> resultList = new TreeMap<String, Integer>();
        for (String item : productFormatTreeMap.keySet()){
            if (promotionList.contains(item) && productFormatTreeMap.get(item).getPromotionPriority() <= PRIORITY && productFormatTreeMap.get(item).getNumberScope() >= NUMBERFULLFREE){
                int forFreeNumber = productFormatTreeMap.get(item).getNumberScope() / NUMBERFULLFREE;
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
