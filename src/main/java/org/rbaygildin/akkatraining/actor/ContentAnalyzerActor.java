package org.rbaygildin.akkatraining.actor;

import akka.actor.AbstractActor;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.rbaygildin.akkatraining.domain.ContentMessage;
import org.rbaygildin.akkatraining.domain.KeywordMessage;
import org.rbaygildin.akkatraining.service.ParsingService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ContentAnalyzerActor extends AbstractActor {

    private final ParsingService service;

    public ContentAnalyzerActor(ParsingService service) {
        this.service = service;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ContentMessage.class, contentMessage -> {
                    Elements elements = Jsoup.parse(contentMessage.getContent()).select("h2");
                    elements.forEach(h -> {
                        service.saveKeyword(KeywordMessage.builder().url(contentMessage.getUrl()).keyword(h.text()).build());
                    });
                })
                .build();
    }
}
