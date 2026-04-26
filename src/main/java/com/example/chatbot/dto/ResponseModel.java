package com.example.chatbot.dto;

public class ResponseModel<T> {

    private String respcode;   // "00" = approved
    private String respname;   // "APPROVED", "ERROR"
    private String respdesc;   // message
    private T data;            // actual response data (optional)

    public ResponseModel() {
    }

    public ResponseModel(String respcode, String respname, String respdesc, T data) {
        this.respcode = respcode;
        this.respname = respname;
        this.respdesc = respdesc;
        this.data = data;
    }

    public static <T> ResponseModel<T> approved(String desc, T data) {
        return new ResponseModel<>("00", "APPROVED", desc, data);
    }

    public static <T> ResponseModel<T> error(String code, String name, String desc) {
        return new ResponseModel<>(code, name, desc, null);
    }

    // getters + setters

    public String getRespcode() {
        return respcode;
    }

    public void setRespcode(String respcode) {
        this.respcode = respcode;
    }

    public String getRespname() {
        return respname;
    }

    public void setRespname(String respname) {
        this.respname = respname;
    }

    public String getRespdesc() {
        return respdesc;
    }

    public void setRespdesc(String respdesc) {
        this.respdesc = respdesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "respcode='" + respcode + '\'' +
                ", respname='" + respname + '\'' +
                ", respdesc='" + respdesc + '\'' +
                ", data=" + data +
                '}';
    }
}