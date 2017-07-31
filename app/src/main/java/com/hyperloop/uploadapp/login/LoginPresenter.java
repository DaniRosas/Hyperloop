package com.hyperloop.uploadapp.login;

import com.hyperloop.uploadapp.login.events.LoginEvent;

/**
 * Created by DaniRosas on 17/7/17.
 */

public interface LoginPresenter {
    void onCreate();

    //To avoid a memory leak, when is destroyed the view is destroyed the variable assigned to the presenter
    void onDestroy();

    //Check if the user have been authenticated
    void checkForAuthenticatedUser();

    //It's associated with our library, so I agreeing in the interface so when it is not associated
    //with the library, I don't have any problem and if I have to change it, I do it from the interface
    void onEventMainThread(LoginEvent event);

    //Check if the login was correct
    void validateLogin(String email, String password);

    //Check if the registration was correct
    void registerNewUser(String email, String password);
}
