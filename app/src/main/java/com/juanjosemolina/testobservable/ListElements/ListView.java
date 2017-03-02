package com.juanjosemolina.testobservable.ListElements;

import com.juanjosemolina.testobservable.ListElements.model.dto.Children;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;

import java.util.List;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public interface ListView {

    void showListInformation(List <Children> data);
    void showProgress();
    void hideProgress();
}
