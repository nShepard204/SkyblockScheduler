package com.example.skyscheduler.Controllers;

import com.example.skyscheduler.Services.HypixelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/hypixel")
public class HypixelController {
    Logger logger = LoggerFactory.getLogger(HypixelController.class);
    @Autowired
    private HypixelService hypixelService;
    @GetMapping("/election")
    public String fetchElectionData(){
        logger.info(hypixelService.fetchElectionData());
        return "fuk";
    }
}
