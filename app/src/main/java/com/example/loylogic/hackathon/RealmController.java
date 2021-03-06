package com.example.loylogic.hackathon;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.loylogic.hackathon.Model.User;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.delete(User.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<User> getBooks() {

        return realm.where(User.class).findAll();
    }

    //query a single item with the given id
    public User getBook(String id) {

        return realm.where(User.class).equalTo("id", id).findFirst();
    }

    //check if User.class is empty
    public boolean hasUsers() {
        return !realm.where(User.class).findAll().isEmpty();
    }

    //query example
    public RealmResults<User> queryedUsers() {

        return realm.where(User.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();

    }
}
