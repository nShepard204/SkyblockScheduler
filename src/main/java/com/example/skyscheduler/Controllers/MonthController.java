package com.example.skyscheduler.Controllers;

import com.example.skyscheduler.Entities.Months;
import com.example.skyscheduler.Repositories.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MonthController {
    @Autowired
    private MonthRepository monthRepository;
    public Iterable<Months> getAllMonths(){
        return monthRepository.findAll();
    }
}
