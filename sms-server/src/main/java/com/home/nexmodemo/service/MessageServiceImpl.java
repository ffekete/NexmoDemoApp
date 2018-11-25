package com.home.nexmodemo.service;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.response.SmsResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nexmo.client.sms.SmsSubmissionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

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
    @HystrixCommand(fallbackMethod = "getDefaultErrorMessage")
    public SmsResponse getMessageSendingResult(final TextMessageDTO textMessageDTO) {
        return smsMessageService.getResults(textMessageDTO).map(this::getSmsSubmissionResult).orElse(SmsResponse.BUILDER.withStatus(SmsResponse.StatusCode.ERROR).withMessage("Unknown error happened...").build());
    }

    private SmsResponse getDefaultErrorMessage(final TextMessageDTO textMessageDTO) {
        return SmsResponse.BUILDER
                .withStatus(SmsResponse.StatusCode.ERROR)
                .withMessage("Sms sending service is not available")
                .build();
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
