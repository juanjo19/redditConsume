package com.juanjosemolina.testobservable.ListElements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.juanjosemolina.testobservable.ListElements.model.dto.Children;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;
import com.juanjosemolina.testobservable.R;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ListView{

    private ListPresenter presenter;
    private ProgressBar progressBar;
    private TextView showInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //declaracion
        showInfo = (TextView) findViewById(R.id.showInfo);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        presenter = new ListPresenterImpl(this);
        presenter.getDataApi();
    }

    @Override
    public void showListInformation(List <Children> data) {

    }

    @Override
    public void showProgress(boolean option) {
        if(option == true){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void testShowInfo(MainDto data) {
        //presenter.sendInfo(data);
        String nombre = data.getChildren().get(0).getAtributtes().getName();
        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        Log.i("Esto es....", "data..."+nombre);
        showInfo.setText(nombre);
    }
}
