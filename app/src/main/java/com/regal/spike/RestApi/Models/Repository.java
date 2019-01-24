package com.regal.spike.RestApi.Models;

public class Repository {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Repository\n\tname=" + name + "\n\tdescription=" + description;
    }
}
