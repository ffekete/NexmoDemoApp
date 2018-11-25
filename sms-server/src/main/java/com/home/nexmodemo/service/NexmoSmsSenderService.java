package com.home.nexmodemo.service;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.factory.TextMessageFactory;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.Message;
import com.nexmo.client.sms.messages.TextMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Obtains {@link TextMessage} and sends it via {@link NexmoClient}.
 */
@Service
public class NexmoSmsSenderService implements SmsMessageService {

    private static final String ERROR_MESSAGE_PATTERN = "An error occurred during sending sms {} {} {}.";
    private static final Logger LOGGER = LoggerFactory.getLogger(NexmoSmsSenderService.class);

    private final NexmoClient nexmoClient;
    private final TextMessageFactory textMessageFactory;

    @Autowired
    public NexmoSmsSenderService(final NexmoClient nexmoClient, final TextMessageFactory textMessageFactory) {
        this.nexmoClient = nexmoClient;
        this.textMessageFactory = textMessageFactory;
    }

    @Override
    public Optional<SmsSubmissionResult[]> getResults(final TextMessageDTO textMessageDTO) {
        Message message = getTextMessage(textMessageDTO);
        try {
            SmsSubmissionResult[] result = nexmoClient.getSmsClient().submitMessage(message);
            LOGGER.info(StringUtils.join("Received response: ", ReflectionToStringBuilder.toString(result)));
            return Optional.of(result);
        } catch (IOException | NexmoClientException exception) {
            LOGGER.error(ERROR_MESSAGE_PATTERN, textMessageDTO.getFrom(), textMessageDTO.getTo(), textMessageDTO.getBody(), exception);
            return Optional.empty();
        }
    }

    private TextMessage getTextMessage(final TextMessageDTO textMessageDTO) {
        return textMessageFactory.getTextMessage(textMessageDTO);
    }
}
