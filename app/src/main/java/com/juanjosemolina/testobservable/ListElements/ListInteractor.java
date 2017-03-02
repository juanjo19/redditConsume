package com.juanjosemolina.testobservable.ListElements;

import com.juanjosemolina.testobservable.ListElements.model.dto.Children;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;

import java.util.List;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public interface ListInteractor {

    interface getInfoListener {

        void errorInformation();

        void sendInformation();
    }

    void sendInfo(List<Children> data, getInfoListener listener);


}
