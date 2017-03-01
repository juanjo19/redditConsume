package com.juanjosemolina.testobservable.ListElements.model.dto;

import java.util.List;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public class MainDto {

    private Data data;

    public List<Children> getChildren() {
        return data.getChildren();
    }
}
