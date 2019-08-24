package org.rbaygildin.akkatraining.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.rbaygildin.akkatraining.domain.ContentMessage;
import org.rbaygildin.akkatraining.domain.UrlMessage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.jsoup.Jsoup.connect;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HtmlDownloaderActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(UrlMessage.class, urlMessage -> {
                    String url = urlMessage.getUrl();
                    log.info("Got url " + url + " for download");
                    String content = connect(url).validateTLSCertificates(false).get().toString();
                    getSender().tell(
                            ContentMessage.
                                    builder()
                                    .url(url)
                                    .content(content)
                                    .build(),
                            getSelf());
                })
                .build();
    }
}
