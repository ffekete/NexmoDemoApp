package com.home.nexmodemo.factory;

import com.home.nexmodemo.dto.TextMessageDTO;

/**
 * Creates {@link TextMessageDTO} instances.
 */
public class TextMessageContextFactory {

    /**
     * Creates the dto.
     * @param from from field.
     * @param target to field.
     * @param body body of the message.
     * @return created dto.
     */
    public static TextMessageDTO createFrom(final String from, final String target, final String body) {
        return new TextMessageDTO(from, target, body);
    }
}
