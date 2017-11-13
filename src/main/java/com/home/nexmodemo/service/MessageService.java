package com.home.nexmodemo.service;

import com.nexmo.client.sms.SmsSubmissionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MessageService {

    private static final String ERROR_RESULT = "ERROR";
    private static final String OK_RESULT = "OK";

    private SmsMessageService nexmoSmsSenderService;

    @Autowired
    public MessageService(final SmsMessageService nexmoSmsSenderService) {
        this.nexmoSmsSenderService = nexmoSmsSenderService;
    }

    public String getMessageSendingResult() {
        return getMessageResult(nexmoSmsSenderService.getResults());
    }

    private String getMessageResult(final SmsSubmissionResult[] results) {
        return Arrays.asList(results).stream()
                .map(SmsSubmissionResult::getStatus)
                .anyMatch(result -> result != SmsSubmissionResult.STATUS_OK)
                 ? ERROR_RESULT : OK_RESULT;
    }
}
