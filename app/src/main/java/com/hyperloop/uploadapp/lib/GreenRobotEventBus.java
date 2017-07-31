package com.hyperloop.uploadapp.lib;

/**
 * Created by DaniRosas on 17/7/17.
 */

/**
 * I use this because it doesnt mattter if I want to use antoher library, or change the implementaion,
 * I'll have this environment which implement the app and I minimze the bugs when the library changes.
 *
 * It will define all the variables and methods through Eventbus
 */


public class GreenRobotEventBus implements EventBus{
    org.greenrobot.eventbus.EventBus eventBus;

    //I'll have uniquely one instance of this object in full app
    private static class SingletonHolder {
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public GreenRobotEventBus(){
        eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }


    public void register(Object subscriber){
        eventBus.register(subscriber);
    }

    public void unregister(Object subscriber){
        eventBus.unregister(subscriber);
    }

    public void post(Object event){
        eventBus.post(event);
    }
}
