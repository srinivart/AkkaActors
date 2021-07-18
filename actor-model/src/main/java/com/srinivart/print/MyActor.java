package com.srinivart.print;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class MyActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("printit", p ->{
                    System.out.println("The address of the actor is : "+getSelf());
                    getSender().tell("Got Message", getSelf());
                })
                .build();
    }

    public void postStop(){
        log.info("Stopping Actor {}", this);
    }

    public static void main(String args[]){

        ActorSystem system = ActorSystem.create("MainActor");
        ActorRef myActorRef = system.actorOf(Props.create(MyActor.class),"ChildActor");
        myActorRef.tell("printit",myActorRef);

    }

}
