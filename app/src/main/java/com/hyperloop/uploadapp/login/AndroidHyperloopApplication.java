package com.hyperloop.login;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by DaniRosas on 17/7/17.
 */

public class AndroidHyperloopApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
