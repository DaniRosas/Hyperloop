package com.hyperloop.uploadapp.upload;

/**
 * Created by DaniRosas on 31/7/17.
 */

public class UploadInteractorImpl implements UploadInteractor{

    UploadRepositoryImpl uploadRepository;

    public UploadInteractorImpl(UploadRepositoryImpl uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    public UploadInteractorImpl() {
        this.uploadRepository = new UploadRepositoryImpl();
    }

    @Override
    public void signOff() {
        uploadRepository.signOff();
    }
}
