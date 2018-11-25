package com.home.nexmodemo.client;

import com.home.nexmodemo.response.SmsResponse;

/**
 * Provides an easy access to the public endpoints.
 */
public interface SmsClient {
    SmsResponse sendMessage(String from, String to, String body) throws Exception;
}
