package com.home.nexmodemo.factory;

import com.home.nexmodemo.context.TextMessageContext;

/**
 * Creates {@link TextMessageContext} instances.
 */
public class TextMessageContextFactory {

    /**
     * Creates the context.
     * @param from from field.
     * @param target to field.
     * @param body body of the message.
     * @return created context.
     */
    public static TextMessageContext createFrom(final String from, final String target, final String body) {
        return new TextMessageContext(from, target, body);
    }
}
