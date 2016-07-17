package com.tw.homework.JavaBean;

/**
 * Created by Yasic on 2016/7/17.
 */
public class Format {
    private String nameScope;
    private String numberScope;
    private float priceScope;
    private float totalMoneyScope;
    private float saveMoneyScope;

    public Format(String nameScope, String numberScope, float priceScope, float totalMoneyScope, float saveMoneyScope) {
        this.nameScope = nameScope;
        this.numberScope = numberScope;
        this.priceScope = priceScope;
        this.totalMoneyScope = totalMoneyScope;
        this.saveMoneyScope = saveMoneyScope;
    }
}
