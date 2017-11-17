package com.home.nexmodemo.configuration.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.servlets.AdminServlet;
import com.home.nexmodemo.listener.HealthCheckServletContextListener;
import com.home.nexmodemo.listener.MyMetricsServletContextListener;

/**
 * Stores basic beans and property configuration values.
 */
@Configuration
public class MetricsConfig {

    @Bean
    public HealthCheckServletContextListener getHealthCheckServletContextListener() {
        return new HealthCheckServletContextListener();
    }

    @Bean
    public MyMetricsServletContextListener getMetricRegistryListener() {
        return new MyMetricsServletContextListener();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(@Value("${metrics.adminUrlMapping}") final String metricsUrlMapping) {
        return new ServletRegistrationBean(new AdminServlet(), metricsUrlMapping);
    }
}
