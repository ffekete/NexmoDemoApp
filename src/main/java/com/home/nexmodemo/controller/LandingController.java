package com.home.nexmodemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handle requests to / path.
 */
@RestController
public class LandingController {

    private static final String ROOT_PATH = "/";
    private static final String HELP_MESSAGE = "For documentation of this service please visit https://github.com/ffekete/NexmoDemoApp";

    /**
     * Handles all request going to / path.
     * @return help message.
     */
    @RequestMapping(path = ROOT_PATH)
    public String handleRequest() {
        return HELP_MESSAGE;
    }
}
