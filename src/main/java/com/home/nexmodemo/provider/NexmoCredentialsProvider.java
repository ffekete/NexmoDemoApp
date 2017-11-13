package com.home.nexmodemo.provider;

import org.springframework.stereotype.Component;

public final class NexmoCredentialsProvider {

    private final String apiKey;
    private final String apiSecret;

    public NexmoCredentialsProvider(final String apiKey, final String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }
}
