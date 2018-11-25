package com.home.nexmodemo.configuration;

import com.home.nexmodemo.provider.NexmoCredentialsProvider;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.TokenAuthMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebApplicationConfiguration {

    @Bean
    public NexmoClient getNexmoClient(final NexmoCredentialsProvider nexmoCredentialsProvider) {
        return new NexmoClient(getTokenAuthMethod(nexmoCredentialsProvider));
    }

    @Bean
    public NexmoCredentialsProvider getNexmoCredentialsProvider(@Value("${api.key}") final String apiKey, @Value("${api.secret}") final String apiSecret) {
        return new NexmoCredentialsProvider(apiKey, apiSecret);
    }

    @Bean
    public TokenAuthMethod getTokenAuthMethod(final NexmoCredentialsProvider nexmoCredentialsProvider) {
        return new TokenAuthMethod(nexmoCredentialsProvider.getApiKey(), nexmoCredentialsProvider.getApiSecret());
    }
}
