package com.regal.spike.RestApi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contributor {
    @SerializedName("Login")
    private String name;

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
        return "Contributer [name=" + name + ", contributions=" + contributions + "]";
    }
}
