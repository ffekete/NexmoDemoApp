package com.home.nexmodemo.service;

import com.home.nexmodemo.provider.NexmoSmsContentProvider;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.Message;
import com.nexmo.client.sms.messages.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NexmoSmsSenderService implements SmsMessageService {

    private static final String ERROR_MESSAGE_PATTERN = "An error occurred during sending sms {} {} {}.";
    private static final Logger LOGGER = LoggerFactory.getLogger(NexmoSmsSenderService.class);

    private final NexmoClient nexmoClient;
    private final NexmoSmsContentProvider contentProvider;

    @Autowired
    public NexmoSmsSenderService(final NexmoClient nexmoClient, final NexmoSmsContentProvider nexmoSmsContentProvider) {
        this.nexmoClient = nexmoClient;
        this.contentProvider = nexmoSmsContentProvider;
    }

    public SmsSubmissionResult[] getResults() {
        Message message = new TextMessage(contentProvider.getFrom(), contentProvider.getTarget(), contentProvider.getBody());
        try {
            return nexmoClient.getSmsClient().submitMessage(message);
        } catch (IOException | NexmoClientException exception) {
            LOGGER.error(ERROR_MESSAGE_PATTERN, contentProvider.getFrom(), contentProvider.getTarget(), contentProvider.getBody(), exception);
            return null;
        }

    }
}
