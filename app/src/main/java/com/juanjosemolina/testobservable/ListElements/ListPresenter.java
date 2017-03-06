package com.juanjosemolina.testobservable.ListElements;

import com.juanjosemolina.testobservable.ListElements.model.dto.AtributtesJson;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;

import java.util.List;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public interface ListPresenter {
    void sendInfo(MainDto result);
    void sendList(List<AtributtesJson> lista);
    void getDataApi();
    void shoewError(String error);
    void successProcess(Boolean flag);
}
