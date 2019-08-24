package org.rbaygildin.akkatraining.dao;

import org.rbaygildin.akkatraining.domain.KeywordMessage;
import org.springframework.data.repository.CrudRepository;

public interface ParsingDao extends CrudRepository<KeywordMessage, Long> {
}
