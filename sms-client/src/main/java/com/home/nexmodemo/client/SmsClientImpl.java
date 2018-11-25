package com.home.nexmodemo.client;

import com.google.gson.Gson;
import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.response.SmsResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class SmsClientImpl implements SmsClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmsClient.class);

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    @Override
    public SmsResponse sendMessage(String from, String to, String body) throws Exception {
        TextMessageDTO textMessageDTO = new TextMessageDTO(from, to, body);
        HttpPost httpPost = new HttpPost("http://localhost:8000/message");

        httpPost.setEntity(new StringEntity(new Gson().toJson(textMessageDTO)));
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

        HttpResponse response = httpClient.execute(httpPost);
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset());
        LOGGER.info(jsonResponse);
        return new Gson().fromJson(jsonResponse, SmsResponse.class);
    }
}
