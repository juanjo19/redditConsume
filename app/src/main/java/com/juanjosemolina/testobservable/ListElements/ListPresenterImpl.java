package com.juanjosemolina.testobservable.ListElements;

import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public class ListPresenterImpl implements ListPresenter{

    private ListView listView;
    private ListInteractor listInteractor;

    public ListPresenterImpl(ListView listView) {
        this.listView = listView;
        listInteractor = new ListInteractorImpl(this);
    }

    @Override
    public void sendInfo(MainDto result) {
        if(listView != null){
            listView.testShowInfo(result);
        }

    }

    @Override
    public void getDataApi() {
        if(listView != null){
            listView.showProgress(true);
            listInteractor.getDataApiInteractor();
        }
    }

    @Override
    public void shoewError(String error) {
        if(listView != null)
        {
            listView.showProgress(false);
            listView.showError(error);
        }
    }

    @Override
    public void successProcess(Boolean flag) {
        if(listView != null){
            listView.showProgress(flag);
        }
    }
}
