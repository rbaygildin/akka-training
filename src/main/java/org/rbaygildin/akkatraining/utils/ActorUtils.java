package org.rbaygildin.akkatraining.utils;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.experimental.UtilityClass;
import org.rbaygildin.akkatraining.config.SpringContextProvider;

import static org.rbaygildin.akkatraining.config.SpringExtensionProvider.PROVIDER;

@UtilityClass
public class ActorUtils {

    public static ActorRef actorOf(Class<? extends Actor> actorType){
        ActorSystem actorSystem = SpringContextProvider.getApplicationContext().getBean(ActorSystem.class);
        return actorSystem.actorOf(PROVIDER.get(actorSystem).props(actorType), actorType.getSimpleName());
    }
}
