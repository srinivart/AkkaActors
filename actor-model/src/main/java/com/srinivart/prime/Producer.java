package com.srinivart.prime;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Producer extends AbstractActor {
    ActorSystem system = ActorSystem.create();
    private int count =0;


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StartMessage.class, this::onStartMessage)
                .match(String.class, this::onPrimeMessage)
                .build();
    }

    private void onStartMessage(StartMessage msg) {
        System.out.println("Producer received message from main: " + msg.text);
        ActorRef SuperVisorRef = system.actorOf(Props.create(Supervisor.class));

        for(int i =0; i<10;i++){
            int min = 1;
            int max = 100;
            int b = (int) (Math.random() * (max - min +1 )+ min);
            SuperVisorRef.tell(b, this.getSelf());
        }
    }

    private void onPrimeMessage(String  msg) {
        count++;
        System.out.println("Count is : "+count);
        System.out.println("Producer received prime result: "+msg+ "from worker"+getSender());
        checkCount();
    }

    private void checkCount() {
        if(this.count==1000){
            system.terminate();
        }
    }
}

class StartMessage{
    public final String text;

    StartMessage(String text){
        this.text = text;
    }
}

class RandomNumberMessage{
    public final int number;

    public RandomNumberMessage(int number){
      this.number = number;
    }
}
