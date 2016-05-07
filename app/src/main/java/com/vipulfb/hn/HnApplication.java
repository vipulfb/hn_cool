package com.vipulfb.hn;

import android.app.Application;
import android.content.Context;

import com.vipulfb.hn.service.HttpService;

/**
 * Created by Vipul Sharma on 5/7/2016.
 */
public class HnApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        HttpService.getInstance().setup(getApplicationContext());
    }

}
