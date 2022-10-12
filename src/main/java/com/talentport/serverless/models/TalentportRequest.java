package com.talentport.serverless.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TalentportRequest{

    @JsonProperty("data")
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
