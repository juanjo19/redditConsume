package com.juanjosemolina.testobservable.model.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by juanjosemolina on 1/03/17.
 */
public class Children {

    @SerializedName("kind")
    private String title;

    @SerializedName("data")
    private atributtesJson atributtes;

    public String getTitle() {
        return title;
    }

    public atributtesJson getAtributtes() {
        return atributtes;
    }
}


