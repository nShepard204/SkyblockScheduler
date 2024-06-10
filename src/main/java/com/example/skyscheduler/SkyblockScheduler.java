package com.example.skyscheduler;


import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@SpringBootApplication
@RestController
@EnableScheduling
public class SkyblockScheduler {
	Logger logger = LoggerFactory.getLogger(SkyblockScheduler.class);

	@Value("${mailgun.api.key}")
	private String mailgunApiKey;
	@Value("${hypixel.api.key}")
	private String hypixelApiKey;
	@Value("${mailgun.domain.sandbox}")
	private String mailgunDevDomain;
	@Value("${hypixel.priv.uuid}")
	private String shepUuid;




	public static void main(String[] args) {
		SpringApplication.run(SkyblockScheduler.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE").allowedOrigins("http://localhost:3000");
			}
		};
	}


	@Scheduled(cron = "0 57 13 * * *")
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

	public String hypixelTest(){
		HttpResponse<JsonNode> playerData = Unirest.get("https://api.hypixel.net/v2/resources/games")
				.header("API-Key", this.hypixelApiKey)
				.queryString("uuid", this.shepUuid)
				.asJson();
		return playerData.getBody().toPrettyString();
	}
}
