package com.home.nexmodemo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Optional;

import com.home.nexmodemo.response.SmsResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.nexmo.client.sms.SmsSubmissionResult;

public class MessageServiceTest {

    private static final String OK_RESULT = "OK";
    private static final String ERROR_RESULT = "ERROR";
    private static final String FROM = "From";
    private static final String BODY = "Body";
    private static final String TARGET = "Target";

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
        TextMessageDTO textMessageDTO = new TextMessageDTO(FROM, TARGET, BODY);
        when(smsSubmissionResult.getStatus()).thenReturn(SmsSubmissionResult.STATUS_OK);
        when(smsMessageService.getResults(textMessageDTO)).thenReturn(Optional.of(new SmsSubmissionResult[]{smsSubmissionResult}));
        // WHEN
        SmsResponse result = underTest.getMessageSendingResult(textMessageDTO);
        // THEN
        assertEquals(result.getStatus(), OK_RESULT);
        assertEquals(result.getMessage(), "Sms successfully sent");
        verify(smsSubmissionResult, times(1)).getStatus();
        verify(smsMessageService, times(1)).getResults(textMessageDTO);
    }

    @Test
    public void testGetMessageSendingResultShouldReturnErrorWhenSubmissionResultsAreError() {
        // GIVEN
        SmsSubmissionResult smsSubmissionResult = mock(SmsSubmissionResult.class);
        TextMessageDTO textMessageDTO = new TextMessageDTO(FROM, TARGET, BODY);
        when(smsSubmissionResult.getStatus()).thenReturn(SmsSubmissionResult.STATUS_INTERNAL_ERROR);
        when(smsMessageService.getResults(textMessageDTO)).thenReturn(Optional.of(new SmsSubmissionResult[]{smsSubmissionResult}));
        // WHEN
        SmsResponse result = underTest.getMessageSendingResult(textMessageDTO);
        // THEN
        assertEquals(result.getStatus(), ERROR_RESULT);
        verify(smsSubmissionResult, times(1)).getStatus();
        verify(smsMessageService, times(1)).getResults(textMessageDTO);
    }

    @Test
    public void testGetMessageSendingResultShouldReturnErrorWhenSubmissionResultIsNull() {
        // GIVEN
        TextMessageDTO textMessageDTO = new TextMessageDTO(FROM, TARGET, BODY);
        when(smsMessageService.getResults(textMessageDTO)).thenReturn(Optional.empty());
        // WHEN
        SmsResponse result = underTest.getMessageSendingResult(textMessageDTO);
        // THEN
        assertEquals(result.getStatus(), ERROR_RESULT);
        verify(smsMessageService, times(1)).getResults(textMessageDTO);
    }
}