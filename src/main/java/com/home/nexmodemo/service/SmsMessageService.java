package com.home.nexmodemo.service;

import com.nexmo.client.sms.SmsSubmissionResult;

interface SmsMessageService {

    SmsSubmissionResult[] getResults();
}
