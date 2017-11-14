package com.home.nexmodemo.factory;

import org.springframework.stereotype.Component;

import com.home.nexmodemo.context.TextMessageContext;
import com.nexmo.client.sms.messages.TextMessage;

/**
 * For creating {@link TextMessage} instances.
 */
@Component
public class TextMessageFactory {

    /**
     * Creates a {@link TextMessage}.
     * @param textMessageContext all info needed for text message.
     * @return created message.
     */
    public TextMessage getTextMessage(final TextMessageContext textMessageContext) {
        return new TextMessage(textMessageContext.getFrom(), textMessageContext.getTo(), textMessageContext.getBody());
    }
}
