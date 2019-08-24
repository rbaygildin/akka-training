package org.rbaygildin.akkatraining.service;

import org.rbaygildin.akkatraining.actor.JobSchedulerActor;
import org.rbaygildin.akkatraining.dao.ParsingDao;
import org.rbaygildin.akkatraining.domain.KeywordMessage;
import org.rbaygildin.akkatraining.domain.UrlMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.rbaygildin.akkatraining.utils.ActorUtils.tell;

@Service
public class ParsingService {

    private final ParsingDao dao;

    public ParsingService(ParsingDao dao) {
        this.dao = dao;
    }

    public void startParsing(String url) {
        tell(JobSchedulerActor.class, UrlMessage.builder().url(url).build());
    }

    public Map<String, List<String>> getKeywords() {
        Map<String, List<String>> keywords = new HashMap<>();
        for (KeywordMessage keywordMessage : dao.findAll()) {
            keywords.computeIfAbsent(keywordMessage.getUrl(), (kwUrl) -> new ArrayList<>());
            keywords.get(keywordMessage.getUrl()).add(keywordMessage.getKeyword());
        }
        return keywords;
    }

    public void saveKeyword(KeywordMessage keywordMessage) {
        dao.save(keywordMessage);
    }
}
