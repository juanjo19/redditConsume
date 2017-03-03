package com.juanjosemolina.testobservable.ListElements.service;

import com.juanjosemolina.testobservable.ListElements.model.dto.MainDto;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by juanjosemolina on 3/03/17.
 */

public interface NetworkApi {

    // url complete https://www.reddit.com/reddits.json
    @GET("reddits.json")
    Observable<MainDto> getData();

}
