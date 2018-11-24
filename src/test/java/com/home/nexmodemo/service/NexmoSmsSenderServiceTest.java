package com.home.nexmodemo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.factory.TextMessageFactory;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsClient;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;

public class NexmoSmsSenderServiceTest {

    private static final String FROM = "From";
    private static final String BODY = "Body";
    private static final String TARGET = "Target";

    @Mock
    private NexmoClient nexmoClient;
    @Mock
    private TextMessageFactory textMessageFactory;
    @InjectMocks
    private NexmoSmsSenderService underTest;

    @Before
    public void setUp() {
        initMocks(this);
        reset(nexmoClient);
    }

    @Test
    public void testGetResultsShouldReturnNullWhenExceptionIsThrownByClient() throws IOException, NexmoClientException {
        // GIVEN
        TextMessage textMessage = mock(TextMessage.class);
        SmsClient smsClient = mock(SmsClient.class);
        TextMessageDTO textMessageDTO = new TextMessageDTO(FROM, TARGET, BODY);
        when(nexmoClient.getSmsClient()).thenReturn(smsClient);
        when(textMessageFactory.getTextMessage(textMessageDTO)).thenReturn(textMessage);
        when(smsClient.submitMessage(textMessage)).thenThrow(new NexmoClientException());
        // WHEN
        Optional<SmsSubmissionResult[]> results = underTest.getResults(textMessageDTO);
        // THEN
        verify(nexmoClient, times(1)).getSmsClient();
        verify(smsClient, times(1)).submitMessage(anyObject());
        assertEquals(Optional.empty(), results);
    }

    @Test
    public void testGetResultsShouldReturnResultsWhenClientIsSuccessful() throws IOException, NexmoClientException {
        // GIVEN
        TextMessage textMessage = mock(TextMessage.class);
        SmsSubmissionResult[] smsSubmissionResults = new SmsSubmissionResult[]{};
        SmsClient smsClient = mock(SmsClient.class);
        TextMessageDTO textMessageDTO = new TextMessageDTO(FROM, TARGET, BODY);
        when(nexmoClient.getSmsClient()).thenReturn(smsClient);
        when(textMessageFactory.getTextMessage(textMessageDTO)).thenReturn(textMessage);
        when(smsClient.submitMessage(textMessage)).thenReturn(smsSubmissionResults);
        // WHEN
        Optional<SmsSubmissionResult[]> results = underTest.getResults(textMessageDTO);
        // THEN
        verify(nexmoClient, times(1)).getSmsClient();
        verify(smsClient, times(1)).submitMessage(textMessage);
        assertEquals(Optional.of(smsSubmissionResults), results);
    }

}