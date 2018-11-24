package com.home.nexmodemo.service;

import java.util.Optional;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.nexmo.client.sms.SmsSubmissionResult;

interface SmsMessageService {

    Optional<SmsSubmissionResult[]> getResults(TextMessageDTO textMessageDTO);
}
