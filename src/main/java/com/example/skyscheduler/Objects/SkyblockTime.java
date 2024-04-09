package com.example.skyscheduler.Objects;

import java.time.Instant;

public class SkyblockTime {
    // Skyblock time values in milliseconds.
    private final long skyBlockYearLen = 124 * 60 * 60 * 1000; // 5 Days, 4 Hours.
    private final long skyBlockMonthLen = skyBlockYearLen / 12;
    private final long skyBlockDayLen = skyBlockMonthLen / 31;
    private final long skyBlockHourLen = skyBlockDayLen / 24;
    private final long skyBlockMinuteLen = skyBlockHourLen / 60;
    private final long skyblockSecondLen = skyBlockMinuteLen / 60;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public SkyblockTime(int year, int month, int day, int hour, int minute, int second){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public SkyblockTime(){
        this.year = 1;
        this.month = 1;
        this.day = 1;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public SkyblockTime fromInstant(Instant instant){
        long skyBlockEpochMs = 1560275700000L; // Day 0, Year 0
        long skyBlockLifeTime = instant.toEpochMilli() - skyBlockEpochMs;

        // I absolutely hate this way of doing this and I want to change it but I'm not sure how yet.
        int year = this.getUnit(skyBlockLifeTime, skyBlockYearLen) + 1;
        skyBlockLifeTime %= skyBlockYearLen;
        int month = this.getUnit(skyBlockLifeTime, skyBlockMonthLen) + 1;
        skyBlockLifeTime %= skyBlockMonthLen;
        int day = this.getUnit(skyBlockLifeTime, skyBlockDayLen) + 1;
        skyBlockLifeTime %= skyBlockDayLen;
        int hour = this.getUnit(skyBlockLifeTime, skyBlockHourLen);
        skyBlockLifeTime %= skyBlockHourLen;
        int minute = this.getUnit(skyBlockLifeTime, skyBlockMinuteLen);
        skyBlockLifeTime %= skyBlockMinuteLen;
        int second = this.getUnit(skyBlockLifeTime, skyblockSecondLen);

        return new SkyblockTime(year, month, day, hour, minute, second);
    }

    private int getUnit(long skyTime, long timeUnit){
        long retVal = skyTime / timeUnit;
        skyTime = skyTime % timeUnit;
        return (int) retVal;
    }

    @Override
    public String toString(){
        return String.format("\nYear: %s\nMonth: %s\nDay: %s\nHour: %s\nMinute: %s\nSecond: %s\n", this.year, this.month, this.day, this.hour, this.minute, this.second);
    }
}
