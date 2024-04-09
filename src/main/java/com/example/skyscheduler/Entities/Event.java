package com.example.skyscheduler.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    /*
    *
    * Name
    * Start Time
    * End Time
    *
    * */
}
