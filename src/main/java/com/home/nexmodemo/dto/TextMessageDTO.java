package com.home.nexmodemo.dto;

/**
 * Text message content.
 */
public final class TextMessageDTO {

    private String from;
    private String to;
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
