package com.example.Insurance_Aggregator_Project.Advise;

public class ApiException extends RuntimeException{

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String errMessage){
        super(errMessage);
    }
}
