package com.home.nexmodemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.home.nexmodemo.provider.NexmoCredentialsProvider;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.TokenAuthMethod;

@Configuration
public class WebApplicationConfiguration {

    @Bean
    public NexmoClient getNexmoClient(@Value("${api.key}") final String apiKey, @Value("${api.secret}") final String apiSecret) {
        NexmoCredentialsProvider nexmoCredentialsProvider = getNexmoCredentialsProvider(apiKey, apiSecret);
        return new NexmoClient(getTokenAuthMethod(nexmoCredentialsProvider));
    }

    private NexmoCredentialsProvider getNexmoCredentialsProvider(final String apiKey, final String apiSecret) {
        return new NexmoCredentialsProvider(apiKey, apiSecret);
    }

    private TokenAuthMethod getTokenAuthMethod(final NexmoCredentialsProvider nexmoCredentialsProvider) {
        return new TokenAuthMethod(nexmoCredentialsProvider.getApiKey(), nexmoCredentialsProvider.getApiSecret());
    }
}
