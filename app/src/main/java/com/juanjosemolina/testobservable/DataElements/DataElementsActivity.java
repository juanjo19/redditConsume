package com.juanjosemolina.testobservable.DataElements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.juanjosemolina.testobservable.ListElements.model.dto.atributtesJson;
import com.juanjosemolina.testobservable.R;

public class DataElementsActivity extends AppCompatActivity {

    private TextView textView;
    private atributtesJson json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_elements);

        textView = (TextView) findViewById(R.id.showInfo);
        json = (atributtesJson) getIntent().getExtras().getSerializable("data");
        textView.setText(json.getDescription());
    }


}
