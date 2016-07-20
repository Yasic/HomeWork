package com.tw.homework.Exception;
/**
 * Created by Yasic on 2016/7/17.
 */
public class EmptyInputException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Input cannot be empty or null!";
    }
}
