package com.home.nexmodemo.client;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.response.SmsResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class SmsClientImpl implements SmsClient {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public SmsResponse sendMessage(String from, String to, String body) {
        Map<String, String> inputparameters = new HashMap<>();
        inputparameters.put("from", from);
        inputparameters.put("to", to);
        inputparameters.put("body", body);

        try {
            ResponseEntity<SmsResponse> response = restTemplate.postForEntity("http://localhost:8000/message", inputparameters, SmsResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return new SmsResponse(e.getResponseBodyAsString(), SmsResponse.StatusCode.ERROR);
        }
    }
}
