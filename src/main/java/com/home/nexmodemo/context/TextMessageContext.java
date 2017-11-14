package com.home.nexmodemo.context;

/**
 * Text message content.
 */
public final class TextMessageContext {

    private final String from;
    private final String to;
    private final String body;

    public TextMessageContext(final String from, final String to, final String body) {
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
