package com.tw.homework.JavaBean;

/**
 * Created by Yasic on 2016/7/17.
 */
public class Format {
    private String nameScope;
    private int numberScope;
    private float priceScope;
    private String unitScope;
    private float totalMoneyScope;
    private float saveMoneyScope;
    private int promotionFlag;

    private Format(){}

    public static class Builder{
        private Format format = new Format();
        public Builder setNameScope(String nameScope) {
            format.nameScope = nameScope;
            return this;
        }

        public Builder setNumberScope(int numberScope) {
            format.numberScope = numberScope;
            return this;
        }

        public Builder setPriceScope(float priceScope) {
            format.priceScope = priceScope;
            return this;
        }

        public Builder setUnitScope(String unitScope) {
            format.unitScope = unitScope;
            return this;
        }

        public Builder setTotalMoneyScope(float totalMoneyScope) {
            format.totalMoneyScope = totalMoneyScope;
            return this;
        }

        public Builder setSaveMoneyScope(float saveMoneyScope) {
            format.saveMoneyScope = saveMoneyScope;
            return this;
        }

        public Format build(){
            return format;
        }
    }

    public String getNameScope() {
        return nameScope;
    }

    public int getNumberScope() {
        return numberScope;
    }

    public float getPriceScope() {
        return priceScope;
    }

    public String getUnitScope() {
        return unitScope;
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
        Format format = (Format)obj;
        boolean isEqual = true;
        isEqual = isEqual & (this.nameScope.equals(format.nameScope)) & (this.numberScope == format.numberScope)
                & (this.priceScope == format.priceScope)
                & (this.totalMoneyScope == format.totalMoneyScope)
                & (this.unitScope.equals(format.unitScope))
                & (this.saveMoneyScope == format.saveMoneyScope);
        return isEqual;
    }

    @Override
    public String toString() {
        return this.nameScope + " " + this.numberScope + " " + this.priceScope + " " + this.unitScope + " " + this.totalMoneyScope + " " + this.saveMoneyScope;
    }
}
