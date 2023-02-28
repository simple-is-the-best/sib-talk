package com.sib.utils.generator;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class RedisConst {

    public final static String PREFIX_CHANNEL = "channel:";
    public final static String WELCOME_MESSAGE = "welcome";

    public static double ScoreOfTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String ChannelKey(long id) {
        return PREFIX_CHANNEL + id;
    }
}
