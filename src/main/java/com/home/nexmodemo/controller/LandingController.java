package com.home.nexmodemo.controller;


import com.home.nexmodemo.service.MessageService;
import com.home.nexmodemo.service.NexmoSmsSenderService;
import com.nexmo.client.NexmoClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Handles requests for landing page.
 */
@Controller
public class LandingController {

    @Autowired
    private MessageService messageService;

    /**
     * Request handling method for GET requests.
     * @return Result of the message sending.
     * @throws IOException IO errors.
     * @throws NexmoClientException Client errors.
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getResponse() throws IOException, NexmoClientException {
        return messageService.getMessageSendingResult();
    }
}
