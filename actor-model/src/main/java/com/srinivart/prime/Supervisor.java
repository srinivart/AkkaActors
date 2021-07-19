package com.srinivart.prime;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

public class Supervisor extends AbstractActor {
    Router router;
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    ActorSystem system = ActorSystem.create();

    {
    List<Routee> routees = new ArrayList<Routee>();
    for(int i=0;i<10;i++){
        ActorRef worker = getContext().actorOf(Props.create(Worker.class));
        getContext().watch(worker);
        routees.add(new ActorRefRoutee(worker));
    }
    router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, this::onRandomNumber)
                .build();
    }

    private void onRandomNumber(int msg) {
        System.out.println("Supervisor received integer from Producer : "+msg);
        int number = msg;
        router.route(msg,getSender());
    }
}
