package com.home.nexmodemo.service;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.response.SmsResponse;

public interface MessageService {

    /**
     * Translates message sending result to simple String response.
     * @return result as an {{@link SmsResponse}}.
     */
    SmsResponse getMessageSendingResult(TextMessageDTO textMessageDTO);
}
