package com.home.nexmodemo.controller;

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

/**
 * Handles requests for landing page.
 */
@RestController
public class LandingController {

    private static final String MESSAGE_SENDING_PATH = "/message/to/{number}";
    private static final String HELP_MESSAGE = "This http method is not supported, please send a POST request instead.";

    @Autowired
    private MessageService messageService;
    @Autowired
    private NexmoSmsContentProvider contentProvider;

    /**
     * Request handling method for GET requests.
     * @return Result of the message sending.
     */
    @RequestMapping(path = MESSAGE_SENDING_PATH, method = RequestMethod.POST)
    @ResponseBody
    public String getResponse(@PathVariable final String number) {
        TextMessageContext textMessageContext = TextMessageContextFactory.createFrom(contentProvider.getFrom(), number, contentProvider.getBody());
        return messageService.getMessageSendingResult(textMessageContext);
    }

    @RequestMapping(path = MESSAGE_SENDING_PATH)
    public String getDefaultResponse() {
        return HELP_MESSAGE;
    }
}
