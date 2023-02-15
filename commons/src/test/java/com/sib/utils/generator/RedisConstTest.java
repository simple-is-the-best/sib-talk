package com.sib.utils.generator;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class RedisConstTest {

    long ANY_CHANNEL_ID = 1L;

    @Test
    void redisKeyGenTest() {
        String actual = RedisConst.ChannelKey(ANY_CHANNEL_ID);

        assertEquals(RedisConst.PREFIX_CHANNEL + ANY_CHANNEL_ID, actual);
    }

    @Test
    void redisScoreOfTimeTest() {
        double nowPlus1Sec = getNowPlus1Sec();
        double sut = RedisConst.ScoreOfTime();

        boolean actual = sut < nowPlus1Sec;

        assertTrue(actual);
    }

    double getNowPlus1Sec() {
        return LocalDateTime.now().plusSeconds(1L).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}