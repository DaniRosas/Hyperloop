package com.hyperloop.uploadapp.login;

/**
 * Created by DaniRosas on 17/7/17.
 */

public interface LoginRepository {
    //Unique class who know we're connected to Firebase
    void signUp(final String email, final String password);
    void signIn(String email, String password);
    void checkAlreadyAuthenticated();
}
