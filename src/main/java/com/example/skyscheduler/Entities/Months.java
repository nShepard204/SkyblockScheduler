package com.example.skyscheduler.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Months {
    @Id
    @GeneratedValue
    private Long id;
    private String monthName;

    protected Months(){}

    public Months(Long id, String monthName){
        this.id = id;
        this.monthName = monthName;
    }

    public Long getId(){
        return this.id;
    }

    public String getMonthName(){
        return this.monthName;
    }
}
