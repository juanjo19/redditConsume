package com.juanjosemolina.testobservable.ListElements.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public class atributtesJson {

    private String id;

    @SerializedName("display_name")
    private String name;

    @SerializedName("public_description")
    private String description;

    @SerializedName("lang")
    private String language;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }
}
