package com.juanjosemolina.testobservable.ListElements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juanjosemolina.testobservable.ListElements.model.dto.AtributtesJson;
import com.juanjosemolina.testobservable.ListElements.model.dto.Children;
import com.juanjosemolina.testobservable.ListElements.model.dto.DataJsonSQLiteHelper;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;
import com.juanjosemolina.testobservable.ListElements.service.NetworkApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
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
    Context context;
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
    public void getDataApiInteractor() {

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
                        listPresenter.sendInfo(mainDto);
                        //saveData(mainDto);
                        //listPresenter.sendList(getAllData());
                    }
                });

    }

    //metodo para almacenar la informacion en base de datos
    public void saveData(MainDto dataJson){
        jsonHelper = new DataJsonSQLiteHelper(context, "DBdatajson", null, 1);

        db = jsonHelper.getWritableDatabase();
        if(db != null){
            ContentValues newRegister = new ContentValues();
            for(Children data: dataJson.getChildren()){
                newRegister.put("ID", data.getAtributtes().getId());
                newRegister.put("name", data.getAtributtes().getName());
                newRegister.put("description", data.getAtributtes().getDescription());
                newRegister.put("language", data.getAtributtes().getLanguage());
                newRegister.put("url_image", data.getAtributtes().getImage());
            }
            db.insert("dataJson", null, newRegister);
            db.close();
        }
    }

    private List<AtributtesJson> getAllData(){
        Cursor cursor = db.rawQuery("select * from dataJson", null);
        List<AtributtesJson> list = new ArrayList<AtributtesJson>();

        if(cursor.moveToFirst()){
            while(cursor.isAfterLast() == false){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String language = cursor.getString(cursor.getColumnIndex("language"));
                String image = cursor.getString(cursor.getColumnIndex("url_image"));

                list.add(new AtributtesJson(id, name, description, language, image));
                cursor.moveToNext();
            }
        }
        return  list;
    }
}
