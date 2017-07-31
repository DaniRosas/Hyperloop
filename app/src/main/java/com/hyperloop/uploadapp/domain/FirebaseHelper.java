package com.hyperloop.uploadapp.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by DaniRosas on 17/7/17.
 */

//Centralized full logic of Firebase
public class FirebaseHelper {
    //Variable to work with the reference to the repositort
    private DatabaseReference dataReference;
    private final static String SEPARATOR = "___";

    //Route to use users by database
    private final static String USERS_PATH = "users";
    private final static String FIREBASE_URL = "https://hyperlooplogin.firebaseio.com";

    //Inicialization of Singleton to have just one instance in all app
    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //Constructer to inicialize Firebase
    public FirebaseHelper(){
        dataReference = FirebaseDatabase.getInstance().getReference();
    }

    //Getter for Data Reference
    public DatabaseReference getDataReference() {
        return dataReference;
    }

    //Get email authenticated for a user
    public String getAuthUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if (user != null) {
            email = user.getEmail();
        }
        return email;
    }

    //Get the referenfe of a user
    public DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    //Get the reference of MY user
    public DatabaseReference getMyUserReference() {
        return getUserReference(getAuthUserEmail());
    }

    public void signOff(){
        FirebaseAuth.getInstance().signOut();
    }
}
