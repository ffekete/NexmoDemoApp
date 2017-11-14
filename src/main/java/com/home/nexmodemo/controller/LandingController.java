package com.home.nexmodemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.home.nexmodemo.context.TextMessageContext;
import com.home.nexmodemo.factory.TextMessageContextFactory;
import com.home.nexmodemo.provider.NexmoSmsContentProvider;
import com.home.nexmodemo.service.MessageService;
import com.nexmo.client.NexmoClientException;

/**
 * Handles requests for landing page.
 */
@RestController
public class LandingController {

    private static final String MESSAGE_SENDING_PATH = "/message/to/{number}";

    @Autowired
    private MessageService messageService;
    @Autowired
    private NexmoSmsContentProvider contentProvider;

    /**
     * Request handling method for GET requests.
     * @return Result of the message sending.
     * @throws IOException IO errors.
     * @throws NexmoClientException Client errors.
     */
    @RequestMapping(path = MESSAGE_SENDING_PATH, method = RequestMethod.POST)
    @ResponseBody
    public String getResponse(@PathVariable final String number) throws IOException, NexmoClientException {
        TextMessageContext textMessageContext = TextMessageContextFactory.createFrom(contentProvider.getFrom(), number,
                contentProvider.getBody());
        return messageService.getMessageSendingResult(textMessageContext);
    }

    @RequestMapping(path = MESSAGE_SENDING_PATH)
    public String getDefaultResponse() {
        return "This http method is not supported, please send a POST request instead.";
    }
}
