package org.rbaygildin.akkatraining.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlMessage {
    private String url;
}
