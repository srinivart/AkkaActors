package com.srinivart.prime;

import akka.actor.AbstractActor;

public class Worker extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, this::onStartMessage)
                .build();
    }

    private void onStartMessage(int msg) {
    System.out.println("Worker "+this.getSelf() +"received random number from "+getSender() +" "+msg );
    int num = msg;
    boolean flag = false;
    for (int i = 2; i<= num/2; ++i){
        if(num % i ==0 ){
            flag = true;
            break;
        }
    }
    if(!flag) // this calls Producer -> onPrimeMessage
        getSender().tell(new String("The Number: "+String.valueOf(num)+" is a Prime Number "), getSelf() );
    else
        getSender().tell(new String("The Number: "+String.valueOf(num)+" is not a Prime Number "), getSelf() );

    }


}
