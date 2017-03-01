package com.juanjosemolina.testobservable.ListElements;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public class ListPresenterImpl implements ListPresenter{

    private ListView listView;
    private ListInteractor listInteractor;

    public ListPresenterImpl(ListView listView) {
        this.listView = listView;
    }
}
