package com.home.nexmodemo.controller;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.home.nexmodemo.listener.MyMetricsServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.nexmodemo.context.TextMessageContext;
import com.home.nexmodemo.factory.TextMessageContextFactory;
import com.home.nexmodemo.service.MessageService;

/**
 * Handles requests for landing page.
 */
@Controller
public class LandingController {

    private static final String MESSAGE_SENDING_PATH = "/message";
    private static final String HELP_MESSAGE = "This http method is not supported, please send a POST request instead.";

    private final MetricRegistry metricRegistry = MyMetricsServletContextListener.METRIC_REGISTRY;
    private final Meter requestsStarted = metricRegistry.meter("requestsStarted");

    @Autowired
    private MessageService messageService;

    /**
     * Request handling method for GET requests.
     * @return Result of the message sending.
     */
    @RequestMapping(path = MESSAGE_SENDING_PATH, method = RequestMethod.POST)
    @ResponseBody
    public String getResponse(@RequestParam final String to, @RequestParam final String from, @RequestParam final String body) {
        requestsStarted.mark();
        TextMessageContext textMessageContext = TextMessageContextFactory.createFrom(from, to, body);
        return messageService.getMessageSendingResult(textMessageContext);
    }

    @RequestMapping(path = MESSAGE_SENDING_PATH)
    public String getDefaultResponse() {
        return HELP_MESSAGE;
    }
}
