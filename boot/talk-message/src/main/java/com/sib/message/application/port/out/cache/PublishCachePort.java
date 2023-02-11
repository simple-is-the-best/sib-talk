package com.sib.message.application.port.out.cache;

public interface PublishCachePort {

    void publish(String topic, Object message);
}
