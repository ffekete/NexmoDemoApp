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

import com.home.nexmodemo.context.TextMessageContext;
import com.home.nexmodemo.factory.TextMessageFactory;
import com.home.nexmodemo.provider.NexmoSmsContentProvider;
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
    private NexmoSmsContentProvider nexmoSmsContentProvider;
    @Mock
    private TextMessageFactory textMessageFactory;
    @InjectMocks
    private NexmoSmsSenderService underTest;

    @Before
    public void setUp() {
        initMocks(this);
        reset(nexmoClient, nexmoSmsContentProvider);
    }

    @Test
    public void testGetResultsShouldReturnNullWhenExceptionIsThrownByClient() throws IOException, NexmoClientException {
        // GIVEN
        TextMessage textMessage = mock(TextMessage.class);
        SmsClient smsClient = mock(SmsClient.class);
        TextMessageContext textMessageContext = new TextMessageContext(FROM, TARGET, BODY);
        when(nexmoClient.getSmsClient()).thenReturn(smsClient);
        when(textMessageFactory.getTextMessage(textMessageContext)).thenReturn(textMessage);
        when(smsClient.submitMessage(textMessage)).thenThrow(new NexmoClientException());
        when(nexmoSmsContentProvider.getFrom()).thenReturn(FROM);
        when(nexmoSmsContentProvider.getBody()).thenReturn(BODY);
        when(nexmoSmsContentProvider.getTarget()).thenReturn(TARGET);
        // WHEN
        Optional<SmsSubmissionResult[]> results = underTest.getResults(textMessageContext);
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
        TextMessageContext textMessageContext = new TextMessageContext(FROM, TARGET, BODY);
        when(nexmoClient.getSmsClient()).thenReturn(smsClient);
        when(textMessageFactory.getTextMessage(textMessageContext)).thenReturn(textMessage);
        when(smsClient.submitMessage(textMessage)).thenReturn(smsSubmissionResults);
        when(nexmoSmsContentProvider.getFrom()).thenReturn(FROM);
        when(nexmoSmsContentProvider.getBody()).thenReturn(BODY);
        when(nexmoSmsContentProvider.getTarget()).thenReturn(TARGET);
        // WHEN
        Optional<SmsSubmissionResult[]> results = underTest.getResults(textMessageContext);
        // THEN
        verify(nexmoClient, times(1)).getSmsClient();
        verify(smsClient, times(1)).submitMessage(textMessage);
        assertEquals(Optional.of(smsSubmissionResults), results);
    }

}