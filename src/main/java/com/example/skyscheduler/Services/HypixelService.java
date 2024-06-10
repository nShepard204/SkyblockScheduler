package com.example.skyscheduler.Services;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

@Service
public class HypixelService {
    public String fetchElectionData(){
        HttpResponse<JsonNode> electionResp = Unirest.get("https://api.hypixel.net/v2/resources/skyblock/election").asJson();
        String currentMayor = electionResp.getBody().getObject().getJSONObject("mayor").getJSONObject("election").toString();
        return currentMayor;
    }
}
