package org.rbaygildin.akkatraining.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.rbaygildin.akkatraining.domain.ContentMessage;
import org.rbaygildin.akkatraining.domain.UrlMessage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ParsingActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ContentMessage.class, contentMessage -> {
                    Elements links = Jsoup.parse(contentMessage.getContent(), contentMessage.getUrl()).select("a[href]");
                    links.stream()
                            .map(l -> UrlMessage.builder().url(l.attr("abs:href")).build())
                            .forEach(urlMessage -> getSender().tell(urlMessage, getSelf()));
                })
                .build();
    }
}
