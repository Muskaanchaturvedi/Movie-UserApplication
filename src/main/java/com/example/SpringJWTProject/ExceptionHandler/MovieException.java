package com.example.SpringJWTProject.ExceptionHandler;

public class MovieException extends Exception{
    private static  final long serialVersionUID=1L;
    private String errorMsg;

    public String getErrorMsg() {
        return this.errorMsg;
    }



    public MovieException(){}
    public MovieException(String errorMsg){
        super(errorMsg);
        this.errorMsg=errorMsg;
    }
}
