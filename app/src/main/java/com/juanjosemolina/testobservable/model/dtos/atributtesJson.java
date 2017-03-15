package com.juanjosemolina.testobservable.model.dtos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by juanjosemolina on 1/03/17.
 */

@SuppressWarnings("serial")
public class atributtesJson implements Serializable {

    private String id;

    @SerializedName("display_name")
    private String name;

    @SerializedName("public_description")
    private String description;

    @SerializedName("lang")
    private String language;

    @SerializedName("icon_img")
    private String image;

    public atributtesJson(String id, String name, String description, String language, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
        this.image = image;
    }

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

    public String getImage(){ return  image; }

    /**
     * Created by juanjosemolina on 1/03/17.
     */

    public static class MainDto {

        private Data data;

        public List<Children> getChildren() {
            return data.getChildren();
        }
    }
}
