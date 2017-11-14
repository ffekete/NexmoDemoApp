package com.home.nexmodemo.factory;

import org.springframework.stereotype.Component;

import com.nexmo.client.sms.messages.TextMessage;

/**
 * For creating {@link TextMessage} instances.
 */
@Component
public class TextMessageFactory {

    /**
     * Creates a {@link TextMessage}.
     * @param from from field.
     * @param target to field.
     * @param body body of the message.
     * @return created message.
     */
    public TextMessage getTextMessage(final String from, final String target, final String body) {
        return new TextMessage(from, target, body);
    }
}
