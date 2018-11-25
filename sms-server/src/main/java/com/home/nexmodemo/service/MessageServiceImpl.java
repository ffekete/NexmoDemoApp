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
public class MessageServiceImpl implements MessageService {

    private SmsMessageService smsMessageService;

    @Autowired
    public MessageServiceImpl(final SmsMessageService smsMessageService) {
        this.smsMessageService = smsMessageService;
    }

    @Override
    public SmsResponse getMessageSendingResult(final TextMessageDTO textMessageDTO) {
        return smsMessageService.getResults(textMessageDTO).map(this::getSmsSubmissionResult).orElse(SmsResponse.BUILDER.withStatus(SmsResponse.StatusCode.ERROR).withMessage("Unknown error happened...").build());
    }

    private SmsResponse getSmsSubmissionResult(final SmsSubmissionResult[] result) {
        Optional<SmsSubmissionResult> failedResult = Arrays.stream(result)
                .filter(smsSubmissionResult -> smsSubmissionResult.getStatus() != SmsSubmissionResult.STATUS_OK).findAny();
        if(failedResult.isPresent()) {
            return SmsResponse.BUILDER.withStatus(SmsResponse.StatusCode.ERROR).withMessage(failedResult.get().getErrorText()).build();
        }
        return SmsResponse.BUILDER.withStatus(SmsResponse.StatusCode.OK).withMessage("Sms successfully sent").build();
    }
}
