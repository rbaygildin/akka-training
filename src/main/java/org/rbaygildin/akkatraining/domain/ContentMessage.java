package org.rbaygildin.akkatraining.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentMessage {
    private String url;
    private String content;
}
