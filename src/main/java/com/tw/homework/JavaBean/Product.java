package com.tw.homework.JavaBean;

import com.tw.homework.Exception.EmptyInputException;
import com.tw.homework.Exception.IllegalParameter;

/**
 * Created by yasic on 16-7-16.
 */
public class Product {
    private String barcode;
    private String name;
    private float price;
    private String unitType;
    private volatile int hashCode;

    private Product(){}

    public static class Builder{
        private Product product = new Product();
        public Builder setBarcode(String barcode) {
            if (barcode == null || barcode.equals("")){
                throw new EmptyInputException();
            }
            product.barcode = barcode;
            return this;
        }

        public Builder setName(String name) {
            if (name == null || name.equals("")){
                throw new EmptyInputException();
            }
            product.name = name;
            return this;
        }

        public Builder setPrice(float price) {
            if (price < 0){
                throw new IllegalParameter();
            }
            product.price = price;
            return this;
        }

        public Builder setUnitType(String unitType) {
            if (unitType == null || unitType.equals("")){
                throw new EmptyInputException();
            }
            product.unitType = unitType;
            return this;
        }

        public Product build(){
            return product;
        }
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getUnitType() {
        return unitType;
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (hashCode == 0){
            result = 17;
            result = 31 * result + barcode.hashCode();
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product){
            Product product = (Product) obj;
            boolean isEqual = true;
            isEqual = isEqual & (this.barcode.equals(product.barcode)) & (this.name.equals(product.name))
                    & (this.price == product.price)
                    & (this.unitType.equals(product.unitType));
            return isEqual;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.barcode + " " + this.name + " " + this.price + " " + this.unitType;
    }
}

