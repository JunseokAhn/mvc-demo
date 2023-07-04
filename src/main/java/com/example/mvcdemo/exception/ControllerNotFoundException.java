package com.example.mvcdemo.exception;

public class ControllerNotFoundException extends Exception {
    public ControllerNotFoundException(String str){
        super(str);
    }
}
