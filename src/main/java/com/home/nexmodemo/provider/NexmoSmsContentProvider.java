package com.home.nexmodemo.provider;

/**
 * Simple pojo to provide content for text messages.
 */
public class NexmoSmsContentProvider {

    private final String target;
    private final String from;
    private final String body;

    /**
     * Provides message content.
     * @param target to field.
     * @param from from field.
     * @param body body of the message.
     */
    public NexmoSmsContentProvider(final String target, final String from, final String body) {
        this.target = target;
        this.from = from;
        this.body = body;
    }

    public String getTarget() {
        return target;
    }

    public String getFrom() {
        return from;
    }

    public String getBody() {
        return body;
    }
}
