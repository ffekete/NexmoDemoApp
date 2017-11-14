package com.home.nexmodemo.service;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.nexmodemo.context.TextMessageContext;
import com.home.nexmodemo.factory.TextMessageFactory;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.Message;
import com.nexmo.client.sms.messages.TextMessage;

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

    /**
     * Sends message and gets the result.
     * @return result of the transaction.
     */
    public Optional<SmsSubmissionResult[]> getResults(final TextMessageContext textMessageContext) {
        Message message = getTextMessage(textMessageContext);
        try {
            return Optional.ofNullable(nexmoClient.getSmsClient().submitMessage(message));
        } catch (IOException | NexmoClientException exception) {
            LOGGER.error(ERROR_MESSAGE_PATTERN, textMessageContext.getFrom(), textMessageContext.getTo(), textMessageContext.getBody(), exception);
            return Optional.empty();
        }
    }

    private TextMessage getTextMessage(final TextMessageContext textMessageContext) {
        return textMessageFactory.getTextMessage(textMessageContext);
    }
}
