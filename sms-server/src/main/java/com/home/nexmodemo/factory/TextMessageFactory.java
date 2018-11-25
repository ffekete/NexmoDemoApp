package com.home.nexmodemo.factory;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.nexmo.client.sms.messages.TextMessage;
import org.springframework.stereotype.Component;

/**
 * For creating {@link TextMessage} instances.
 */
@Component
public class TextMessageFactory {

    /**
     * Creates a {@link TextMessage}.
     * @param textMessageDTO all info needed for text message.
     * @return created message.
     */
    public TextMessage getTextMessage(final TextMessageDTO textMessageDTO) {
        return new TextMessage(textMessageDTO.getFrom(), textMessageDTO.getTo(), textMessageDTO.getBody());
    }
}
