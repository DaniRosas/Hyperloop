package com.hyperloop.uploadapp.login;

/**
 * Created by DaniRosas on 17/7/17.
 */

public interface LoginInteractor {
    void checkAlreadyAuthenticated();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
