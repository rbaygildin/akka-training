package org.rbaygildin.akkatraining.config;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

public class SpringActorProducer implements IndirectActorProducer {

    private final ApplicationContext applicationContext;
    private final Class<? extends Actor> actorType;

    public SpringActorProducer(ApplicationContext applicationContext,
                               Class<? extends Actor> actorType) {
        this.applicationContext = applicationContext;
        this.actorType = actorType;
    }

    @Override
    public Actor produce() {
        return applicationContext.getBean(actorType);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return actorType;
    }
}
