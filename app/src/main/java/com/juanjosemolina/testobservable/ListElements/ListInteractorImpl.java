package com.juanjosemolina.testobservable.ListElements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juanjosemolina.testobservable.ListElements.model.dto.Children;
import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;

import java.util.List;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public class ListInteractorImpl implements ListInteractor {

    private String path = "http://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";
    OkHttpClient client;
    String response;
    MainDto mainDto;


    @Override
    public void sendInfo(List<Children> data, getInfoListener listener) {

    }

    //metodo que permite la conexion al web service
    public Observable<MainDto> getUsername(){
        return Observable.create(
                new Observable.OnSubscribe<MainDto>(){
                    @Override
                    public void call(Subscriber<? super MainDto> subscriber) {
                        try {
                            //peticion al web service por medio de okhttp
                            client = new OkHttpClient();
                            response = ApiCall.GET(client, path);
                            Gson gson = new GsonBuilder().create();
                            mainDto = gson.fromJson(response, MainDto.class);
                            subscriber.onNext(mainDto);//enviar dato
                            subscriber.onCompleted();
                        }catch (Exception e){
                            subscriber.onError(e);
                        }
                    }

                });
    }
}
