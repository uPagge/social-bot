package org.sadtech.social.bot.domain;

import lombok.Builder;
import lombok.Getter;
import org.sadtech.social.core.domain.BoxAnswer;

@Builder
@Getter
public class Clarification {

    private BoxAnswer question;
    private String value;

}
