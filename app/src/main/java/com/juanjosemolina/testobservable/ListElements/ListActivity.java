package com.juanjosemolina.testobservable.ListElements;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.juanjosemolina.testobservable.ListElements.adapter.AdapterInfo;
import com.juanjosemolina.testobservable.mBroadcastReceiver;
import com.juanjosemolina.testobservable.model.dtos.atributtesJson;
import com.juanjosemolina.testobservable.model.dtos.Children;
import com.juanjosemolina.testobservable.model.dtos.DataJsonSQLiteHelper;
import com.juanjosemolina.testobservable.R;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ListView{

    private ListPresenter presenter;
    private BroadcastReceiver broadcastReceiver;
    //private ProgressBar progressBar;
    //private TextView showInfo;
    private RecyclerView recyclerView;
    private DataJsonSQLiteHelper jsonHelper;
    private IntentFilter intentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        broadcastReceiver = new mBroadcastReceiver();
        intentFilter = new IntentFilter("com.juanjosemolina.testobservable.SOME_ACTION");

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        presenter = new ListPresenterImpl(this);
        presenter.getDataApi(getApplicationContext());
    }

    @Override
    public void showListInformation(List <Children> data) {

    }

    @Override
    public void showProgress(boolean option) {
        if(option == true){
            //progressBar.setVisibility(View.VISIBLE);
        }else{
            //progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String error) {
        Log.i("error", "este es el error..."+error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void testShowInfo(atributtesJson.MainDto data) {
        //presenter.sendInfo(data);
        String nombre = data.getChildren().get(0).getAtributtes().getName();
        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        Log.i("Esto es....", "data..."+nombre);
        //showInfo.setText(nombre);
        jsonHelper = new DataJsonSQLiteHelper(this, "DBdatajson", null, 1);

        SQLiteDatabase db = jsonHelper.getWritableDatabase();
        if(db != null){
            ContentValues newRegister = new ContentValues();
            for(Children datainf: data.getChildren()){
                newRegister.put("idJson", datainf.getAtributtes().getId());
                newRegister.put("name", datainf.getAtributtes().getName());
                newRegister.put("description", datainf.getAtributtes().getDescription());
                newRegister.put("language", datainf.getAtributtes().getLanguage());
                newRegister.put("urlImage", datainf.getAtributtes().getImage());
                db.insert("dataJson", null, newRegister);
            }
            db.close();
            Toast.makeText(this, "guardado correctamente", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showList(List<atributtesJson> listdata) {

        Toast.makeText(this, listdata.get(1).getDescription(), Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.reciclador);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        AdapterInfo adapterInfo = new AdapterInfo(listdata, R.layout.list_data, ListActivity.this);
        recyclerView.setAdapter(adapterInfo);
    }

    protected  void onStart(){
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
    }
}
