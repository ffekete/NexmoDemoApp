package com.home.nexmodemo.service;

import java.util.Arrays;
import java.util.Optional;

import com.home.nexmodemo.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.nexmodemo.dto.TextMessageDTO;
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
    public SmsResponse getMessageSendingResult(final TextMessageDTO textMessageDTO) {
        return nexmoSmsSenderService.getResults(textMessageDTO).map(this::getSmsSubmissionResult).orElse(SmsResponse.BUILDER.withStatus(ERROR_RESULT).withMessage("Unknown error happened...").build());
    }

    private SmsResponse getSmsSubmissionResult(final SmsSubmissionResult[] result) {
        Optional<SmsSubmissionResult> failedResult = Arrays.stream(result)
                .filter(smsSubmissionResult -> smsSubmissionResult.getStatus() != SmsSubmissionResult.STATUS_OK).findAny();
        if(failedResult.isPresent()) {
            return SmsResponse.BUILDER.withStatus(ERROR_RESULT).withMessage(failedResult.get().getErrorText()).build();
        }
        return SmsResponse.BUILDER.withStatus(OK_RESULT).withMessage("Sms successfully sent").build();
    }
}
