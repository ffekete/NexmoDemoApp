package com.home.nexmodemo.provider;

/**
 * Simple pojo to provide credentials.
 */
public final class NexmoCredentialsProvider {

    private final String apiKey;
    private final String apiSecret;

    /**
     * Provides credentials.
     * @param apiKey key.
     * @param apiSecret secret.
     */
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
