package com.tw.homework.JavaBean;

/**
 * Created by yasic on 16-7-16.
 */
public class Product {
    private String barcode;
    private String name;
    private float price;
    private String unitType;

    private Product(){}

    public static class Builder{
        private Product product = new Product();
        public Builder setBarcode(String barcode) {
            product.barcode = barcode;
            return this;
        }

        public Builder setName(String name) {
            product.name = name;
            return this;
        }

        public Builder setPrice(float price) {
            product.price = price;
            return this;
        }

        public Builder setUnitType(String unitType) {
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
}

