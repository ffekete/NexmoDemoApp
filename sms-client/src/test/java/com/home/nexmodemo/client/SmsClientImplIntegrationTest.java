package com.home.nexmodemo.client;

import com.home.nexmodemo.NexmodemoApplication;
import com.home.nexmodemo.response.SmsResponse;
import com.home.nexmodemo.service.NexmoSmsSenderService;
import com.nexmo.client.sms.SmsSubmissionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NexmodemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application.yml")
public class SmsClientImplIntegrationTest {

    private static final String FROM = "123";
    private static final String TO = "456";
    private static final String TEXT_TO_SEND = "text to send";
    private static final String INTERNAL_ERROR = "Internal error";
    private static final String UNKNOWN_ERROR = "Unknown error happened...";

    private final SmsClient smsClient = new SmsClientImpl();

    @MockBean
    private NexmoSmsSenderService nexmoSmsSenderService; // mocking only this service as we don't want to send real messages to any numbers

    @Test
    public void testShouldExecuteRequest() throws Exception {
        // GIVEN
        SmsSubmissionResult smsSubmissionResult = mock(SmsSubmissionResult.class);
        when(smsSubmissionResult.getStatus()).thenReturn(SmsSubmissionResult.STATUS_OK);
        when(nexmoSmsSenderService.getResults(anyObject())).thenReturn(Optional.of(new SmsSubmissionResult[]{smsSubmissionResult}));

        // WHEN
        SmsResponse smsResponse = smsClient.sendMessage(FROM, TO, TEXT_TO_SEND);

        // THEN
        assertEquals(SmsResponse.StatusCode.OK, smsResponse.getStatus());
        assertEquals("Sms successfully sent", smsResponse.getMessage());

    }

    @Test
    public void testShouldFail_serviceReturnsFailure() throws Exception {
        // GIVEN
        SmsSubmissionResult smsSubmissionResult = mock(SmsSubmissionResult.class);
        when(smsSubmissionResult.getStatus()).thenReturn(SmsSubmissionResult.STATUS_INTERNAL_ERROR);
        when(smsSubmissionResult.getErrorText()).thenReturn(INTERNAL_ERROR);
        when(nexmoSmsSenderService.getResults(anyObject())).thenReturn(Optional.of(new SmsSubmissionResult[]{smsSubmissionResult}));

        // WHEN
        SmsResponse smsResponse = smsClient.sendMessage(FROM, TO, TEXT_TO_SEND);

        // THEN
        assertEquals(SmsResponse.StatusCode.ERROR, smsResponse.getStatus());
        assertEquals(INTERNAL_ERROR, smsResponse.getMessage());
    }

    @Test
    public void testShouldFail_clientReturnsNull() throws Exception {
        // GIVEN
        when(nexmoSmsSenderService.getResults(anyObject())).thenReturn(Optional.empty());

        // WHEN
        SmsResponse smsResponse = smsClient.sendMessage(FROM, TO, TEXT_TO_SEND);

        // THEN
        assertEquals(SmsResponse.StatusCode.ERROR, smsResponse.getStatus());
        assertEquals(UNKNOWN_ERROR, smsResponse.getMessage());
    }
}