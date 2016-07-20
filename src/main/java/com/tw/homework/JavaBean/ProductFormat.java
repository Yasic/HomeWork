package com.tw.homework.JavaBean;

/**
 * Created by Yasic on 2016/7/17.
 */
public class ProductFormat{
    //private String nameScope;
    private String barcode;
    private int numberScope;
    //private float priceScope;
    //private String unitScope;
    private float totalMoneyScope;
    private float saveMoneyScope;
    private int promotionFlag;

    private ProductFormat(){}

    public static class Builder{
        private ProductFormat productFormat = new ProductFormat();
        /*public Builder setNameScope(String nameScope) {
            productFormat.nameScope = nameScope;
            return this;
        }*/

        public Builder setBarcode(String barcode) {
            productFormat.barcode = barcode;
            return this;
        }

        public Builder setNumberScope(int numberScope) {
            productFormat.numberScope = numberScope;
            return this;
        }

        /*public Builder setPriceScope(float priceScope) {
            productFormat.priceScope = priceScope;
            return this;
        }*/

        /*public Builder setUnitScope(String unitScope) {
            productFormat.unitScope = unitScope;
            return this;
        }*/

        public Builder setTotalMoneyScope(float totalMoneyScope) {
            productFormat.totalMoneyScope = totalMoneyScope;
            return this;
        }

        public Builder setSaveMoneyScope(float saveMoneyScope) {
            productFormat.saveMoneyScope = saveMoneyScope;
            return this;
        }

        public ProductFormat build(){
            return productFormat;
        }
    }

    /*public String getNameScope() {
        return nameScope;
    }*/

    public int getNumberScope() {
        return numberScope;
    }

    /*public float getPriceScope() {
        return priceScope;
    }*/

    /*public String getUnitScope() {
        return unitScope;
    }*/

    public String getBarcode() {
        return barcode;
    }

    public float getTotalMoneyScope() {
        return totalMoneyScope;
    }

    public float getSaveMoneyScope() {
        return saveMoneyScope;
    }

    public int getPromotionFlag() {
        return promotionFlag;
    }

    public void setTotalMoneyScope(float totalMoneyScope) {
        this.totalMoneyScope = totalMoneyScope;
    }

    public void setSaveMoneyScope(float saveMoneyScope) {
        this.saveMoneyScope = saveMoneyScope;
    }

    public void setNumberScope(int numberScope) {
        this.numberScope = numberScope;
    }

    public void setPromotionFlag(int promotionFlag) {
        this.promotionFlag = promotionFlag;
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
