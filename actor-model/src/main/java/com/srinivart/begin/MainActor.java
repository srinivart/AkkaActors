package com.srinivart.begin;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainActor {
    public static ActorSystem system = ActorSystem.create("mainActor");
    public static void main(String args[]){
        System.out.println( "Hello World!" );
        ActorRef firstActor = system.actorOf(Props.create(FirstActor.class));
        firstActor.tell("Hey First Actor !!!", ActorRef.noSender());
        System.out.println("Sent message to First Actor !!");

    }

}




/*
 * Main class to demonstrate , how to create actor system and then
 * send a message to an actor actor.tell method. this method is basically
 * for tell and forgot.
 *
 */