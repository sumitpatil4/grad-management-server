package com.example.gradmanagementserver.Exception;

public class InvalidJwtException extends Exception{
    public InvalidJwtException(String str){
        super(str);
    }
}
