package com.srinivart.prime;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.io.IOException;

public class Main {

    public static void main(String[] args){

        ActorSystem system = ActorSystem.create();
        final LoggingAdapter log = Logging.getLogger(system,system);

        log.info("creating Actor producer");
        ActorRef ProducerRef = system.actorOf(Props.create(Producer.class));
        ProducerRef.tell(new StartMessage("Heyy sree!"),ProducerRef);

        try{
            System.out.println("Press ENTER to end program");
            System.in.read();
        }catch(IOException ignored){}
        finally{
                system.terminate();
                log.info("Akka system Terminated");
            }
        }


}

