package com.tw.homework.Exception;

/**
 * Created by Yasic on 2016/7/21.
 */
public class IllegalParameter extends RuntimeException {
    @Override
    public String toString() {
        return "Fail with illegal parameter";
    }
}
