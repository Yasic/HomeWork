package com.tw.homework.JavaBean;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.IllegalParameter;

/**
 * Created by Yasic on 2016/7/17.
 */
public class ProductFormat{
    private String barcode;
    private int numberScope;
    private float totalMoneyScope;
    private float saveMoneyScope;
    private int promotionPriority;

    private ProductFormat(){}

    public static class Builder{
        private ProductFormat productFormat = new ProductFormat();

        public Builder setBarcode(String barcode) {
            if (barcode == null || barcode.equals("")){
                throw new EmptyInputException();
            }
            productFormat.barcode = barcode;
            return this;
        }

        public Builder setNumberScope(int numberScope) {
            if (numberScope < 0){
                throw new IllegalParameter();
            }
            productFormat.numberScope = numberScope;
            return this;
        }

        public Builder setTotalMoneyScope(float totalMoneyScope) {
            if (totalMoneyScope < 0){
                throw new IllegalParameter();
            }
            productFormat.totalMoneyScope = totalMoneyScope;
            return this;
        }

        public Builder setSaveMoneyScope(float saveMoneyScope) {
            if (saveMoneyScope < 0){
                throw new IllegalParameter();
            }
            productFormat.saveMoneyScope = saveMoneyScope;
            return this;
        }

        public ProductFormat build(){
            return productFormat;
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

    public void setNumberScope(int numberScope) {
        if (numberScope < 0){
            throw new IllegalParameter();
        }
        this.numberScope = numberScope;
    }

    public void setPromotionPriority(int promotionPriority) {
        if (promotionPriority < 0){
            throw new IllegalParameter();
        }
        this.promotionPriority = promotionPriority;
    }

    @Override
    public boolean equals(Object obj) {
        ProductFormat productFormat = (ProductFormat)obj;
        boolean isEqual = true;
        isEqual = isEqual & (this.barcode.equals(productFormat.barcode)) & (this.numberScope == productFormat.numberScope)
                & (this.totalMoneyScope == productFormat.totalMoneyScope)
                & (this.saveMoneyScope == productFormat.saveMoneyScope);
        return isEqual;
    }

    @Override
    public String toString() {
        return this.barcode + " " + this.numberScope + " "  + " " + " " + this.totalMoneyScope + " " + this.saveMoneyScope;
    }
}
