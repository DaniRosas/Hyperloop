package com.hyperloop.uploadapp.upload;

import com.hyperloop.uploadapp.domain.FirebaseHelper;

/**
 * Created by DaniRosas on 31/7/17.
 */

public class UploadRepositoryImpl implements UploadRepository {

    private FirebaseHelper helper;

    public UploadRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }
}
