package com.example.skyscheduler.Entities;
import jakarta.persistence.*;

@Entity
public class SkyblockEvent {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String eventName;
    @Column
    private int startMonth;
    @Column
    private int startDay;
    @Column
    private int endMonth;
    @Column
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
