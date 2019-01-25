package com.regal.spike.RestApi.Models;

import com.google.gson.annotations.SerializedName;

public class EchoResponse {
    @SerializedName("echoString")
    private String echoString;

    public String getEchoString() {
        return echoString;
    }

    public void setEchoString(String echoString) {
        this.echoString = echoString;
    }

}
