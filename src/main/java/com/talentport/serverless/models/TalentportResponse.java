package com.talentport.serverless.models;

public class TalentportResponse {


    private boolean success;
    private String data;
    private String message;


    public TalentportResponse(boolean succes, String data, String message) {

        this.setSuccess(succes);
        this.setData(data);
        this.setMessage(message);
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
