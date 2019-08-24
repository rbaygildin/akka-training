package org.rbaygildin.akkatraining.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String url;
    private String keyword;
}
