package com.juanjosemolina.testobservable;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by juanjosemolina on 1/03/17.
 */

public class mBroadcastReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "llegue al broadcast", Toast.LENGTH_SHORT).show();
    }
}
