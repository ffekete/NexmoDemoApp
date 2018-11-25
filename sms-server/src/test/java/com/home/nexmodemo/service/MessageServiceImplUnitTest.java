package com.home.nexmodemo.service;

import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.response.SmsResponse;
import com.nexmo.client.sms.SmsSubmissionResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MessageServiceImplUnitTest {

    private static final String FROM = "From";
    private static final String BODY = "Body";
    private static final String TARGET = "Target";

    @Mock
    private SmsMessageService smsMessageService;

    @InjectMocks
    private MessageService underTest = new MessageServiceImpl(smsMessageService);

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
        assertEquals(result.getStatus(), SmsResponse.StatusCode.OK);
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
        assertEquals(result.getStatus(), SmsResponse.StatusCode.ERROR);
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
        assertEquals(result.getStatus(), SmsResponse.StatusCode.ERROR);
        verify(smsMessageService, times(1)).getResults(textMessageDTO);
    }
}