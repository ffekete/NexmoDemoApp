package com.home.nexmodemo.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NexmoSmsContentProvider {

    private final String target;
    private final String from;
    private final String body;

    public NexmoSmsContentProvider( @Value("${message.number}") final String target,
                                    @Value("${message.from}") final String from,
                                    @Value("${message.body}") final String body) {
        this.target = target;
        this.from = from;
        this.body = body;
    }

    public String getTarget() {
        return target;
    }

    public String getFrom() {
        return from;
    }

    public String getBody() {
        return body;
    }
}
