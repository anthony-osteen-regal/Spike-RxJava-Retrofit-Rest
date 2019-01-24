package com.regal.spike.RestApi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contributor {
    @SerializedName("login")
    private String name;

    @SerializedName("contributions")
    private Integer contributions;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getContributions()  {
        return contributions;
    }

    public void setContributions(Integer contributions ){
        this.contributions = contributions;
    }

    @Override
    public String toString(){
        return "Contributer\n\tname:\t" + name + "\n\tcontributions:\t" + contributions;
    }
}
