package com.home.nexmodemo.configuration.spring;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {

    @Bean
    public ServletRegistrationBean hystrixServletRegistrationBean(@Value("${hystrix.stream.url}") String streamUrl) {
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), streamUrl);
    }
}
