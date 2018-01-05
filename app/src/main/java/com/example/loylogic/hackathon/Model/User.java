package com.example.loylogic.hackathon.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by loylogic on 05/01/18.
 */

public class User extends RealmObject {

//    @PrimaryKey
//    @Required
//    private String id;
    @Required
    private String email;
    @Required
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
