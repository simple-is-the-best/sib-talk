package com.sib.chat.application.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class EndpointConfig {

    @Value("${message.server.end-point}")
    private String messageUri;
}
