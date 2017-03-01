package com.juanjosemolina.testobservable.ListElements;

import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public interface ListView {

    void showListInformation(MainDto data);
    void showProgress();
    void hideProgress();
}
