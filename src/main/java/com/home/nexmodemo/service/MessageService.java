package com.home.nexmodemo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexmo.client.sms.SmsSubmissionResult;

/**
 * Gets message sending result and processes its content.
 */
@Service
public class MessageService {

    private static final String ERROR_RESULT = "ERROR";
    private static final String OK_RESULT = "OK";

    private SmsMessageService nexmoSmsSenderService;

    @Autowired
    public MessageService(final SmsMessageService nexmoSmsSenderService) {
        this.nexmoSmsSenderService = nexmoSmsSenderService;
    }

    /**
     * Translates message sending result to simple String response.
     * @return result as a String.
     */
    public String getMessageSendingResult() {
        return nexmoSmsSenderService.getResults().map(this::getSmsSubmissionResult).orElse(ERROR_RESULT);
    }

    private String getSmsSubmissionResult(final SmsSubmissionResult[] result) {
        return Arrays.stream(result)
                .map(SmsSubmissionResult::getStatus)
                .anyMatch(submissionResult -> submissionResult != SmsSubmissionResult.STATUS_OK) ? ERROR_RESULT : OK_RESULT;
    }
}
