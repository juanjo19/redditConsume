package com.juanjosemolina.testobservable.ListElements;

import android.content.Context;

import com.juanjosemolina.testobservable.ListElements.model.dto.atributtesJson;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;

import java.util.List;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public interface ListPresenter {
    void sendInfo(MainDto result);
    void sendList(List<atributtesJson> lista);
    void getDataApi(Context context);
    void shoewError(String error);
    void successProcess(Boolean flag);
}
