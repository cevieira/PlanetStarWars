package com.apistarwars.exceptions;

public class ObjectRequired extends RuntimeException {

    public ObjectRequired(String msg){
        super(msg);
    }
}
