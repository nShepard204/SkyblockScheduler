package com.example.demo;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class DemoApplication {
	@Value("${mailgun.api.key}")
	private String mailgunApiKey;
	@Value("${hypixel.api.key}")
	private String hypixelApiKey;
	@Value("${mailgun.domain.sandbox}")
	private String mailgunDevDomain;
	@Value("${hypixel.priv.uuid}")
	private String shepUuid;

	Logger logger = Logger.getGlobal();
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
		return String.format("Hello %s!", name);
	}


	@GetMapping("/emailTest")
	public String emailTest(){
		JsonNode jsonData = sendEmail();
		return jsonData.toPrettyString();
	}

	public JsonNode sendEmail() throws UnirestException {
		HttpResponse<JsonNode> request = Unirest.post(String.format("https://api.mailgun.net/v3/%s/messages", this.mailgunDevDomain))
				.basicAuth("api", this.mailgunApiKey)
				.queryString("from", "Skyblock Scheduler <skyblock@scheduler.com>")
				.queryString("to", "nShepard204@gmail.com")
				.queryString("subject", "Hello!")
				.queryString("text", "The hard part's over. You got this.")
				.asJson();
		return request.getBody();
	}

	@GetMapping("/hypixelTest")
	public String hypixelTest(){
//		HttpResponse<JsonNode> playerData = Unirest.get("https://api.hypixel.net/v2/player")
//				.header("API-Key", this.hypixelApiKey)
//				.queryString("uuid", this.shepUuid)
//				.asJson();
//		return playerData.getBody().toPrettyString();
		return "";
	}
}
