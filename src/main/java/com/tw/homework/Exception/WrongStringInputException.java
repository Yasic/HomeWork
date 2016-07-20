package com.tw.homework.Exception;

/**
 * Created by Yasic on 2016/7/17.
 */
public class WrongStringInputException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Wrong format or data! Please Check yor input.";
    }
}
