package com.sib.chat.application.services;

import com.sib.chat.application.config.EndpointConfig;
import com.sib.chat.application.usecase.ConnectChatUseCase;
import com.sib.utils.CustomHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConnectService implements ConnectChatUseCase {

    private final EndpointConfig endpoint;
    private final String CONNECT_URI = this.endpoint.getMessageUri() + this.CONNECT_PATH;
    private final String CONNECT_PATH = "/";

    @Override
    public HttpHeaders upgradeSocketHeaders(Long channelId)  {
        URI redirectUri;
        try {
            redirectUri = new URI(CONNECT_URI);
        } catch (Exception e) {
            throw new RuntimeException("Message Server Connection Fail::ChannelId::"+channelId);
        }
        return generateWebSocketHeaders(channelId, redirectUri);
    }

    @Override
    public void disConnect() {

    }

    private HttpHeaders generateWebSocketHeaders(Long channelId, URI redirectUri) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        httpHeaders.setConnection("Upgrade");
        httpHeaders.setUpgrade("websocket");
        httpHeaders.add(CustomHeader.CHANNEL_CONNECTION.get(), channelId.toString());
        return httpHeaders;
    }
}
