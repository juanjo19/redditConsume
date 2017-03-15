package com.juanjosemolina.testobservable.ListElements;

import android.content.Context;

import com.juanjosemolina.testobservable.model.dtos.atributtesJson;

import java.util.List;

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
    public void sendInfo(atributtesJson.MainDto result) {
        if(listView != null){
            listView.testShowInfo(result);
        }

    }

    @Override
    public void sendList(List<atributtesJson> lista) {
        if(listView != null){
            listView.showList(lista);
        }
    }

    @Override
    public void getDataApi(Context context) {
        if(listView != null){
            listView.showProgress(true);
            listInteractor.getDataApiInteractor(context);
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
            //listView.showProgress(flag);
        }
    }
}
