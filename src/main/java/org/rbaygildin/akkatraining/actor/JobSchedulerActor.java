package org.rbaygildin.akkatraining.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.rbaygildin.akkatraining.domain.ContentMessage;
import org.rbaygildin.akkatraining.domain.UrlMessage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static org.rbaygildin.akkatraining.utils.ActorUtils.tell;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobSchedulerActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(this);
    private final Set<String> scheduledUrls = new HashSet<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(UrlMessage.class, urlMessage -> {
                    if (!scheduledUrls.contains(urlMessage.getUrl()) && urlMessage.getUrl().endsWith(".html")) {
                        log.info("Schedule download for url " + urlMessage.getUrl());
                        tell(HtmlDownloaderActor.class, urlMessage, getSelf());
                        scheduledUrls.add(urlMessage.getUrl());
                    }
                })
                .match(ContentMessage.class, contentMessage -> {
                    log.info("Schedule parsing url " + contentMessage.getUrl());
                    tell(ParsingActor.class, contentMessage, getSelf());
                    tell(ContentAnalyzerActor.class, contentMessage, getSelf());
                })
                .build();
    }
}
