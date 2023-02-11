package com.sib.message.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.*;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub");
        registry.enableSimpleBroker("/sub/channel");
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        messageConverters.add(new StringMessageConverter());
        messageConverters.add(new MappingJackson2MessageConverter());
        messageConverters.add(new SimpleMessageConverter());
        messageConverters.add(new ByteArrayMessageConverter());
        return true;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connect")
                .setAllowedOriginPatterns("*");
//                .withSockJS();
//                .setClientLibraryUrl("<https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js>");
    }
}
