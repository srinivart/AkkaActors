package com.srinivart.begin;

import akka.actor.AbstractActor;

import java.time.Duration;

public class SecondActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    System.out.println("Received String message: {} "+s);
                    System.out.println(sender().path());
                    MainActor.system.scheduler().schedule(Duration.ZERO,Duration.ofSeconds(3), () -> {
                        System.out.println("Working on task");}, MainActor.system.dispatcher());
                })
                .matchAny(o-> System.out.println("Unknown message received"))
                .build();
    }

}
