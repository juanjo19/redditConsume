package com.juanjosemolina.testobservable.ListElements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.juanjosemolina.testobservable.ListElements.model.dto.atributtesJson;
import com.juanjosemolina.testobservable.ListElements.model.dto.Children;
import com.juanjosemolina.testobservable.ListElements.model.dto.DataJsonSQLiteHelper;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;
import com.juanjosemolina.testobservable.ListElements.service.NetworkApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public class ListInteractorImpl implements ListInteractor {

    static final String URL = "https://www.reddit.com/";
    //OkHttpClient client;
    //String response;
    //MainDto mainDto;
    private Retrofit retrofit;
    DataJsonSQLiteHelper jsonHelper;
    ListPresenter listPresenter;
    NetworkApi service;
    private SQLiteDatabase db;

    public ListInteractorImpl(ListPresenter listPresenter) {
        this.listPresenter = listPresenter;

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();
        service = retrofit.create(NetworkApi.class);
    }

    @Override
    public void getDataApiInteractor(Context context) {

        Observable<MainDto> dataJson = service.getData();

        dataJson.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MainDto>() {
                    @Override
                    public void onCompleted() {
                        listPresenter.successProcess(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listPresenter.shoewError(e.getMessage());
                    }

                    @Override
                    public void onNext(MainDto mainDto) {
                        //Log.i("Esto es....", "data..."+mainDto.getChildren().get(0).getTitle());
                        //listPresenter.sendInfo(mainDto);
                        saveData(mainDto, context);
                        listPresenter.sendList(getAllData());
                    }
                });

    }

    //metodo para almacenar la informacion en base de datos
    public void saveData(MainDto dataJson, Context context){
        jsonHelper = new DataJsonSQLiteHelper(context, "DBdatajson", null, 1);

        db = jsonHelper.getWritableDatabase();
        if(db != null){
            ContentValues newRegister = new ContentValues();
            for(Children datainf: dataJson.getChildren()){
                newRegister.put("idJson", datainf.getAtributtes().getId());
                newRegister.put("name", datainf.getAtributtes().getName());
                newRegister.put("description", datainf.getAtributtes().getDescription());
                newRegister.put("language", datainf.getAtributtes().getLanguage());
                newRegister.put("urlImage", datainf.getAtributtes().getImage());
                db.insert("dataJson", null, newRegister);
            }
            db.close();
        }
    }

    private List<atributtesJson> getAllData(){
        Cursor cursor = db.rawQuery("select * from dataJson", null);
        List<atributtesJson> list = new ArrayList<atributtesJson>();

        if(cursor.moveToFirst()){
            while(cursor.isAfterLast() == false){
                String id = cursor.getString(cursor.getColumnIndex("idJson"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String language = cursor.getString(cursor.getColumnIndex("language"));
                String image = cursor.getString(cursor.getColumnIndex("urlImage"));

                list.add(new atributtesJson(id, name, description, language, image));
                cursor.moveToNext();
            }
        }
        return  list;
    }
}
