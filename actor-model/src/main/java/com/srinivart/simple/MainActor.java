package com.srinivart.simple;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainActor {

    public static void main(String[] args) {
        startup();
    }

    private static void startup() {
        // Create an Akka system
        ActorSystem system = ActorSystem.create("MainActor");
        //ActorRef mySystem = system.actorOf(Props.create(MainActor.class),"MainActor2");
        //ActorRef childActor = system.actorOf(Props.create(ChildActor.class));
        ActorRef childActor = system.actorOf(Props.create(ChildActor.class), "ChildActor");

        //childActor.tell("Hey !!",ActorRef.noSender());
        childActor.tell("Hey !!",childActor);

    }


}
