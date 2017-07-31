package com.hyperloop.uploadapp.lib;

/**
 * Created by DaniRosas on 17/7/17.
 */

public interface EventBus {
    //Communicate events to a data bus

    //Register to the bus through an object
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
