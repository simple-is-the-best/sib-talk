package com.sib.utils;

public enum CustomHeader {
    CHANNEL_CONNECTION("X-Connect-Channel");

    private final String value;

    CustomHeader(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
