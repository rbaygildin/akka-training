package org.rbaygildin.akkatraining.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.rbaygildin.akkatraining.service.JobSchedulerService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobSchedulerActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(this);

    private final JobSchedulerService service;

    public JobSchedulerActor(JobSchedulerService service) {
        this.service = service;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, service::log).build();
    }
}
