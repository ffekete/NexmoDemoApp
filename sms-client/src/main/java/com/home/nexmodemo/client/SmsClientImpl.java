package com.home.nexmodemo.client;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.response.SmsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SmsClientImpl implements SmsClient {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public SmsResponse sendMessage(String from, String to, String body) throws Exception {
        TextMessageDTO textMessageDTO = new TextMessageDTO(from, to, body);

        ResponseEntity<SmsResponse> response = restTemplate.postForEntity("http://localhost:8000/message", textMessageDTO, SmsResponse.class);
        return response.getBody();
    }
}
