package com.home.nexmodemo.controller;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.home.nexmodemo.dto.TextMessageDTO;
import com.home.nexmodemo.listener.MyMetricsServletContextListener;
import com.home.nexmodemo.response.SmsResponse;
import com.home.nexmodemo.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for message path.
 */
@RestController
@Api(description = "Send sms messages by posting a json to this controller.")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private static final String MESSAGE_SENDING_PATH = "/message";
    private static final String HELP_MESSAGE = "This http method is not supported, please send a POST request instead.";
    private static final String REQUESTS_STARTED = "requestsStarted";
    private static final String LATENCY = "latency";
    private static final String APPLICATION_JSON = "application/json";

    private final MetricRegistry metricRegistry = MyMetricsServletContextListener.METRIC_REGISTRY;
    private final Meter requestsStarted = metricRegistry.meter(REQUESTS_STARTED);
    private final Timer requestLatencyTimer = metricRegistry.timer(LATENCY);

    @Autowired
    private MessageService messageService;

    /**
     * Request handling method for POST requests.
     * @return Result of the message sending.
     */
    @RequestMapping(path = MESSAGE_SENDING_PATH, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    @ApiOperation(value = "Sends sms messages", httpMethod = "POST", consumes = "application/json", response = SmsResponse.class)
    public SmsResponse getResponse(@RequestBody TextMessageDTO json) {
        final Timer.Context context = startMetrics();
        LOGGER.info(StringUtils.join("Request body to be processed: ", ReflectionToStringBuilder.toString(json)));
        SmsResponse result = messageService.getMessageSendingResult(json);
        stopMetrics(context);
        return result;
    }

    @RequestMapping(path = MESSAGE_SENDING_PATH)
    public String getDefaultResponse() {
        return HELP_MESSAGE;
    }

    private Timer.Context startMetrics() {
        requestsStarted.mark();
        return requestLatencyTimer.time();
    }

    private void stopMetrics(final Timer.Context context) {
        context.stop();
    }
}
