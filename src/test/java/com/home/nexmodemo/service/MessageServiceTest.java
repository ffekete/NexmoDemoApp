package com.home.nexmodemo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.reset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.nexmo.client.sms.SmsSubmissionResult;

import java.util.Optional;

public class MessageServiceTest {

    private static final String OK_RESULT = "OK";
    private static final String ERROR_RESULT = "ERROR";

    @Mock
    private SmsMessageService smsMessageService;

    @InjectMocks
    private MessageService underTest;

    @Before
    public void setUp() {
        initMocks(this);
        reset(smsMessageService);
    }

    @Test
    public void testGetMessageSendingResultShouldReturnOkWhenSubmissionResultsAreOk() {
        // GIVEN
        SmsSubmissionResult smsSubmissionResult = mock(SmsSubmissionResult.class);
        when(smsSubmissionResult.getStatus()).thenReturn(SmsSubmissionResult.STATUS_OK);
        when(smsMessageService.getResults()).thenReturn(Optional.of(new SmsSubmissionResult[]{smsSubmissionResult}));
        // WHEN
        String result = underTest.getMessageSendingResult();
        // THEN
        assertEquals(result, OK_RESULT);
        verify(smsSubmissionResult, times(1)).getStatus();
        verify(smsMessageService, times(1)).getResults();
    }

    @Test
    public void testGetMessageSendingResultShouldReturnErrorWhenSubmissionResultsAreError() {
        // GIVEN
        SmsSubmissionResult smsSubmissionResult = mock(SmsSubmissionResult.class);
        when(smsSubmissionResult.getStatus()).thenReturn(SmsSubmissionResult.STATUS_INTERNAL_ERROR);
        when(smsMessageService.getResults()).thenReturn(Optional.of(new SmsSubmissionResult[]{smsSubmissionResult}));
        // WHEN
        String result = underTest.getMessageSendingResult();
        // THEN
        assertEquals(result, ERROR_RESULT);
        verify(smsSubmissionResult, times(1)).getStatus();
        verify(smsMessageService, times(1)).getResults();
    }

    @Test
    public void testGetMessageSendingResultShouldReturnErrorWhenSubmissionResultIsNull() {
        // GIVEN
        when(smsMessageService.getResults()).thenReturn(Optional.empty());
        // WHEN
        String result = underTest.getMessageSendingResult();
        // THEN
        assertEquals(result, ERROR_RESULT);
        verify(smsMessageService, times(1)).getResults();
    }
}