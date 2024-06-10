package com.example.skyscheduler.Controllers;

import com.example.skyscheduler.Services.SkyblockEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class SkyblockEventController {
    Logger logger = LoggerFactory.getLogger(SkyblockEventController.class);
    @Autowired
    private SkyblockEventService sbEventService;

    @GetMapping("")
    public String getEvents(){
        return sbEventService.getNextEventWindow(3);
    }
}
