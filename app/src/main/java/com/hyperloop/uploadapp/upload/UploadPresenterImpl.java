package com.hyperloop.uploadapp.upload;

import com.hyperloop.uploadapp.lib.EventBus;
import com.hyperloop.uploadapp.lib.GreenRobotEventBus;
import com.hyperloop.uploadapp.login.entities.User;
import com.hyperloop.uploadapp.upload.events.UploadEvents;
import com.hyperloop.uploadapp.upload.ui.UploadView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by DaniRosas on 31/7/17.
 */

public class UploadPresenterImpl implements UploadPresenter {
    EventBus eventBus;

    UploadInteractorImpl uploadInteractor;
    UploadView uploadView;


    public UploadPresenterImpl(UploadView uploadView) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.uploadInteractor = new UploadInteractorImpl();
        this.uploadView = uploadView;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void signOff() {
        uploadInteractor.signOff();
    }

    @Override
    @Subscribe
    public void onEventMainThread(UploadEvents event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case UploadEvents.onImageUploaded:
                break;

        }
    }


}
