package com.home.nexmodemo.service;

import java.util.Optional;

import com.home.nexmodemo.context.TextMessageContext;
import com.nexmo.client.sms.SmsSubmissionResult;

interface SmsMessageService {

    Optional<SmsSubmissionResult[]> getResults(TextMessageContext textMessageContext);
}
