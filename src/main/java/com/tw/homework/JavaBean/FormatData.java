package com.tw.homework.JavaBean;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.IllegalParameter;

/**
 * Created by Yasic on 2016/7/17.
 */
public class FormatData {
    private String barcode;
    private int numberScope;
    private float totalMoneyScope;
    private float saveMoneyScope;
    private int promotionPriority;
    private volatile int hashCode;

    private FormatData(){}

    public static class Builder{
        private FormatData formatData = new FormatData();

        public Builder setBarcode(String barcode) {
            if (barcode == null || barcode.equals("")){
                throw new EmptyInputException();
            }
            formatData.barcode = barcode;
            return this;
        }

        public Builder setNumberScope(int numberScope) {
            if (numberScope < 0){
                throw new IllegalParameter();
            }
            formatData.numberScope = numberScope;
            return this;
        }

        public Builder setTotalMoneyScope(float totalMoneyScope) {
            if (totalMoneyScope < 0){
                throw new IllegalParameter();
            }
            formatData.totalMoneyScope = totalMoneyScope;
            return this;
        }

        public Builder setSaveMoneyScope(float saveMoneyScope) {
            if (saveMoneyScope < 0){
                throw new IllegalParameter();
            }
            formatData.saveMoneyScope = saveMoneyScope;
            return this;
        }

        public FormatData build(){
            return formatData;
        }
    }

    public int getNumberScope() {
        return numberScope;
    }

    public String getBarcode() {
        return barcode;
    }

    public float getTotalMoneyScope() {
        return totalMoneyScope;
    }

    public float getSaveMoneyScope() {
        return saveMoneyScope;
    }

    public int getPromotionPriority() {
        return promotionPriority;
    }

    public void setTotalMoneyScope(float totalMoneyScope) {
        if (totalMoneyScope < 0){
            throw new IllegalParameter();
        }
        this.totalMoneyScope = totalMoneyScope;
    }

    public void setSaveMoneyScope(float saveMoneyScope) {
        if (saveMoneyScope < 0){
            throw new IllegalParameter();
        }
        this.saveMoneyScope = saveMoneyScope;
    }

    public void setPromotionPriority(int promotionPriority) {
        if (promotionPriority < 0){
            throw new IllegalParameter();
        }
        this.promotionPriority = promotionPriority;
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (hashCode == 0){
            result = 17;
            result = 31 * result + numberScope;
            result = 31 * result + barcode.hashCode();
            result = 31 * result + Float.floatToIntBits(totalMoneyScope);
            result = 31 * result + Float.floatToIntBits(saveMoneyScope);
            result = 31 * result + promotionPriority;
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FormatData){
            FormatData formatData = (FormatData)obj;
            boolean isEqual = true;
            isEqual = isEqual & (this.barcode.equals(formatData.barcode)) & (this.numberScope == formatData.numberScope)
                    & (this.totalMoneyScope == formatData.totalMoneyScope)
                    & (this.saveMoneyScope == formatData.saveMoneyScope);
            return isEqual;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.barcode + " " + this.numberScope + " "  + " " + " " + this.totalMoneyScope + " " + this.saveMoneyScope;
    }
}
