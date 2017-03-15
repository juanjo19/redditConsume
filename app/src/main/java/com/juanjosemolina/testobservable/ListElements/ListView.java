package com.juanjosemolina.testobservable.ListElements;

import com.juanjosemolina.testobservable.model.dtos.atributtesJson;
import com.juanjosemolina.testobservable.model.dtos.Children;

import java.util.List;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public interface ListView {

    void showListInformation(List <Children> data);
    void showProgress(boolean option);
    void showError(String error);
    void testShowInfo(atributtesJson.MainDto data);
    void showList(List<atributtesJson> listdata);
}
