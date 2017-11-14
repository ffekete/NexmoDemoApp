package com.home.nexmodemo.service;

import com.nexmo.client.sms.SmsSubmissionResult;

import java.util.Optional;

interface SmsMessageService {

    Optional<SmsSubmissionResult[]> getResults();
}
