package com.juanjosemolina.testobservable.ListElements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;
import com.juanjosemolina.testobservable.R;

public class ListActivity extends AppCompatActivity implements ListView{

    private ListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        presenter = new ListPresenterImpl(this);
    }

    @Override
    public void showListInformation(MainDto data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
