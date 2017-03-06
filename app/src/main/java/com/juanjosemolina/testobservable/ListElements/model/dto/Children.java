package com.juanjosemolina.testobservable.ListElements.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by juanjosemolina on 1/03/17.
 */
public class Children {

    @SerializedName("kind")
    private String title;

    @SerializedName("data")
    private AtributtesJson atributtes;

    public String getTitle() {
        return title;
    }

    public AtributtesJson getAtributtes() {
        return atributtes;
    }
}


