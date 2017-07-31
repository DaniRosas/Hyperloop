package com.hyperloop.uploadapp.upload.events;

import com.hyperloop.uploadapp.login.entities.User;

/**
 * Created by DaniRosas on 31/7/17.
 */

public class UploadEvents {
    private User user;
    private int eventType;

    public final static int onImageUploaded= 0;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
