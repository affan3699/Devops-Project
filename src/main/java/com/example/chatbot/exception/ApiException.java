package com.example.chatbot.exception;

public class ApiException extends RuntimeException {

    private final String respcode;
    private final String respname;
    private final String respdesc;

    public ApiException(String respcode, String respname, String respdesc) {
        this.respcode = respcode;
        this.respname = respname;
        this.respdesc = respdesc;
    }

    public String getRespcode() {
        return respcode;
    }

    public String getRespname() {
        return respname;
    }

    public String getRespdesc() {
        return respdesc;
    }
}