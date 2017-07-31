package com.hyperloop.uploadapp.upload;

import com.hyperloop.uploadapp.upload.events.UploadEvents;

/**
 * Created by DaniRosas on 31/7/17.
 */

public interface UploadPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    void onEventMainThread(UploadEvents event);
}
