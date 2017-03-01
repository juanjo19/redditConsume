package com.juanjosemolina.testobservable;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public class ObservableExample {

/*
    public Observable<Integer> getUsername(){
        return Observable.create(
                new Observable.OnSubscribe<Integer>(){

                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            subscriber.onNext(4);//enviar dato
                            subscriber.onCompleted();
                        }catch (Exception e){
                            subscriber.onError(e);
                        }
                    }

                });
    }

    public void test(){
        getUsername().subscribe(
                //On Next
                new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        //realizar accion cuando este la info
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

                    }
                }

        );}*/

    private static Observable<Integer[]> getNumbers(){
        System.out.println("Function getNumbers Thread = " + Thread.currentThread().getName());
        return Observable.create(
                subscriber -> {
                    System.out.println("Observable.create Thread = " + Thread.currentThread().getName());
                    Integer[] numbers = {3,6,2};
                    subscriber.onNext(numbers);
                    subscriber.onCompleted();
                }
        );
    }
}
