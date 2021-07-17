package com.srinivart.begin;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class FirstActor extends AbstractActor {
    // get the reference of second Actor
    ActorRef secondActor = MainActor.system.actorOf(Props.create(SecondActor.class));

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    System.out.println("Received String message: {} "+s);
                    System.out.println(sender().path());
                    // giving command to second actor for performing some operations
                    secondActor.tell("Hey Second Actor !!!", ActorRef.noSender());

                })
                .matchAny(o-> System.out.println("Unknown message received"))
                .build();
    }
}


/*
This actor will be called from main method and it will invoke createReceive method.
 * here we have the check of message type , if type instance of String then do certain operation
 * else do some thing else.
 */