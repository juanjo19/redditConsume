package com.juanjosemolina.testobservable.ListElements;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juanjosemolina.testobservable.ListElements.model.dto.Children;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;
import com.juanjosemolina.testobservable.ListElements.service.NetworkApi;

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
    ListPresenter listPresenter;
    NetworkApi service;

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
                        Log.i("Esto es....", "data..."+mainDto.getChildren().get(0).getTitle());
                        listPresenter.sendInfo(mainDto);
                    }
                });

    }

    /*
    //aqui va toda la logica
    @Override
    public void getDataApiInteractor() {
        getUsername().subscribe(
                //On Next
                new Action1<String>() {
                    @Override
                    public void call(String response) {
                        //realizar accion cuando este la info
                        Gson gson = new GsonBuilder().create();
                        mainDto = gson.fromJson(response, MainDto.class);
                    }
                },

                //On Error
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listPresenter.shoewError(throwable.getMessage());
                    }
                },

                //On complete
                new Action0() {
                    @Override
                    public void call() {
                        listPresenter.sendInfo(mainDto);
                    }
                }

        );
    }


    //metodo que permite la conexion al web service
    public Observable<String> getUsername(){
        return Observable.create(
                new Observable.OnSubscribe<String>(){
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            //peticion al web service por medio de okhttp
                            client = new OkHttpClient();
                            response = ApiCall.GET(client, path);
                            subscriber.onNext(response);//enviar dato
                            subscriber.onCompleted();
                        }catch (Exception e){
                            subscriber.onError(e);
                        }
                    }

                });
    }


    //metodo para ejecutar lo que se obtuvoo desde el observable
    public void executeObservable(){
        getUsername().subscribe(
                //On Next
                new Action1<String>() {
                    @Override
                    public void call(String response) {
                        //realizar accion cuando este la info
                        Gson gson = new GsonBuilder().create();
                        mainDto = gson.fromJson(response, MainDto.class);
                    }
                },

                //On Error
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                },

                //On complete
                new Action0() {
                    @Override
                    public void call() {
                         showInfo(mainDto);
                    }
                }

        );}*/

}
