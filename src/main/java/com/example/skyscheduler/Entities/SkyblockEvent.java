package com.example.skyscheduler.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SkyblockEvent {
    @Id
    @GeneratedValue
    private Long id;
    private String eventName;
    private int startMonth;
    private int startDay;
    private int endMonth;
    private int endDay;

    protected SkyblockEvent(){}

    public SkyblockEvent(Long id, String eventName, int startMonth, int startDay, int endMonth, int endDay){
        this.id = id;
        this.eventName = eventName;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endMonth = endMonth;
        this.endDay = endDay;
    }

    public Long getId(){
        return this.id;
    }

    public String getEventName(){
        return this.eventName;
    }

    public int getStartMonth(){
        return this.startMonth;
    }

    public int getStartDay(){
        return this.startDay;
    }

    public int getEndMonth(){
        return this.endMonth;
    }

    public int getEndDay(){
        return this.endDay;
    }
}
