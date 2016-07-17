package com.tw.homework.JavaBean;

/**
 * Created by Yasic on 2016/7/17.
 */
public class Format {
    private String nameScope;
    private int numberScope;
    private float priceScope;
    private float totalMoneyScope;
    private float saveMoneyScope;

    public Format(String nameScope, int numberScope, float priceScope, float totalMoneyScope, float saveMoneyScope) {
        this.nameScope = nameScope;
        this.numberScope = numberScope;
        this.priceScope = priceScope;
        this.totalMoneyScope = totalMoneyScope;
        this.saveMoneyScope = saveMoneyScope;
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        Format format = (Format)obj;
        boolean isEqual = true;
        isEqual = isEqual & (this.nameScope.equals(format.nameScope)) & (this.numberScope == format.numberScope)
                & (this.priceScope == format.priceScope)
                & (this.totalMoneyScope == format.totalMoneyScope)
                & (this.saveMoneyScope == format.saveMoneyScope);
        return isEqual;
    }
}
