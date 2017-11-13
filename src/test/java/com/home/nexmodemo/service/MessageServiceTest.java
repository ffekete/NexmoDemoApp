package com.home.nexmodemo.service;

import com.nexmo.client.sms.SmsSubmissionResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    private static final int OK_STATUS = 0;
    private static final String OK_RESULT = "OK";
    private static final String ERROR_RESULT = "ERROR";
    private static final int ERROR_STATUS = 1;

    @Mock
    SmsMessageService smsMessageService;

    @InjectMocks
    MessageService underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(smsMessageService);
    }

    @Test
    public void testGetMessageSendingResultShouldReturnOkWhenSubmissionResultsAreOk() {
        // GIVEN
        SmsSubmissionResult smsSubmissionResult = mock(SmsSubmissionResult.class);
        when(smsSubmissionResult.getStatus()).thenReturn(SmsSubmissionResult.STATUS_OK);
        when(smsMessageService.getResults()).thenReturn(new SmsSubmissionResult[]{smsSubmissionResult});
        // WHEN
        String result = underTest.getMessageSendingResult();
        // THEN
        assertEquals(result, OK_RESULT);
    }

    @Test
    public void testGetMessageSendingResultShouldReturnErrorWhenSubmissionResultsAreError() {
        // GIVEN
        SmsSubmissionResult smsSubmissionResult = mock(SmsSubmissionResult.class);
        when(smsSubmissionResult.getStatus()).thenReturn(SmsSubmissionResult.STATUS_INTERNAL_ERROR);
        when(smsMessageService.getResults()).thenReturn(new SmsSubmissionResult[]{smsSubmissionResult});
        // WHEN
        String result = underTest.getMessageSendingResult();
        // THEN
        assertEquals(result, ERROR_RESULT);
    }
}