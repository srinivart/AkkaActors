package com.srinivart.simple;

import akka.actor.UntypedAbstractActor;

import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ChildActor extends UntypedAbstractActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        if (message instanceof String) {
            System.out.println("Sender : "+ getSender());
            log.info("Message From Main Actor: " + message);
        }
    }
}
