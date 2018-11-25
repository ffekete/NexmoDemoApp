package com.home.nexmodemo.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Text message content.
 */
public final class TextMessageDTO {

    @NotEmpty
    @Length(min = 1)
    private String from;

    @NotEmpty
    @Length(min = 1)
    private String to;

    @NotEmpty
    @Length(max = 300)
    private String body;

    public TextMessageDTO() {
    }

    public TextMessageDTO(final String from, final String to, final String body) {
        this.from = from;
        this.to = to;
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }
}
