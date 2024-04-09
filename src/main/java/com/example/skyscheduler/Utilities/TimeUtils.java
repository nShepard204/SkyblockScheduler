package com.example.skyscheduler.Utilities;

import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class TimeUtils {
    private final long skyblockStartEpoch = 1560275700000L;
    private final long skyblockYearLen = 124 * 60 * 60;

    public Long getCurrentTime(){
        return Instant.now().toEpochMilli() / 1000;
    }

    public Long getSkyblockLifetime(){
        return getCurrentTime() - skyblockStartEpoch;
    }

    public Long currentSkyblockYear(){
        return (getSkyblockLifetime() + skyblockYearLen) / skyblockYearLen;
    }

    public long getSkyblockTime(){
        long skyblockYear = (getSkyblockLifetime() / skyblockYearLen) + 1;
        long skyblockMonth = skyblockYear / 12;
        long skyblockDay = skyblockMonth / 31;
        long skyblockHour = skyblockDay / 24;
        long skyblockMinute = skyblockHour / 60;
        long skyblockSecond = skyblockMinute / 60;

        return skyblockYear;
    }


}
