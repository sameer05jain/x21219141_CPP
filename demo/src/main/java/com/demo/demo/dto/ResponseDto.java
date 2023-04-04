package com.demo.demo.dto;

public class ResponseDto {
    
    String messageCode;
    String message;

    public void setMessageCode(String messageCode){
        this.messageCode = messageCode;
    }

    public String getMessageCode(){
        return this.messageCode;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public ResponseDto(String messageCode, String message){
        this.messageCode = messageCode;
        this.message = message;
    }

}
