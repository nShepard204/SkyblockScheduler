package com.example.skyscheduler.Controllers;

import com.example.skyscheduler.Entities.SkyblockEvent;
import com.example.skyscheduler.Objects.SkyblockTime;
import com.example.skyscheduler.Repositories.SkyblockEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Optional;

@Controller
public class SkyblockEventController {
    @Autowired
    private SkyblockEventRepository skyBlockEventRepository;

    public Iterable<SkyblockEvent> getAllEvents(){
        return skyBlockEventRepository.findAll();
    }

    public String getNextEventWindow(long eventId){
        String eventDuration = "";
        Optional<SkyblockEvent> eventReq = skyBlockEventRepository.findById(eventId);
        if(eventReq.isPresent()){
            SkyblockEvent event = eventReq.get();
            int sbYear = new SkyblockTime(Instant.now()).getYear();
            Instant eventStart = new SkyblockTime(sbYear, event.getStartMonth(), event.getStartDay()).toInstant();
            Instant eventEnd = new SkyblockTime(eventId == 9 ? sbYear + 1 : sbYear, event.getEndMonth(), event.getEndDay()).toInstant();

            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.getDefault()).withZone(ZoneId.of("Z"));
            eventDuration = formatter.format(eventStart) + " - " + formatter.format(eventEnd);
        }
        return eventDuration;
    }
}
