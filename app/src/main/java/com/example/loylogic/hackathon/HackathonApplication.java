package com.example.loylogic.hackathon;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.jacksonandroidnetworking.JacksonParserFactory;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by loylogic on 05/01/18.
 */

public class HackathonApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("hackathon.realm").build();
        Realm.setDefaultConfiguration(config);
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

    }
}
