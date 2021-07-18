package com.srinivart.print;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static akka.pattern.PatternsCS.ask;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MyActorTest {

    private static ActorSystem system = null;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("test-system");
    }
    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system, Duration.apply(1000, TimeUnit.MILLISECONDS), true);
        system = null;
    }

    @Test
    public void sendMessage(){
        final TestKit probe = new TestKit(system);
        ActorRef myActorRef = probe.childActorOf(Props.create(MyActor.class));
        myActorRef.tell("printit", probe.testActor());

        probe.expectMsg("Got Message");
    }

}
